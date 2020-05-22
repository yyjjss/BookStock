package book.controller;


import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import book.model.Book;
import book.model.Type;
import book.model.UserDto;
import book.model.Warehouse;
import book.model.v_RequestHistory;
import book.service.UserService;


@Controller
public class BookController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/loginForm.do")
	public String loginForm() {
		return "bookMain/loginForm";
	}
	
	@RequestMapping("/logout.do")
	public String logoutForm(HttpSession session) {
		session.invalidate();
		return "redirect:/loginForm.do";
	}
	
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public void loginCheck(String id, String password, HttpSession session, HttpServletResponse response) throws Exception {
		
		HashMap<String, String> m = new HashMap<String, String>();
		m.put("id", id);
		m.put("password", password);
		try {
			UserDto userDto = userService.loginCheck(m);
			
			session.setAttribute("user_idx", userDto.getUser_idx());
			session.setAttribute("user_type_id", userDto.getUser_type_id());	
			session.setAttribute("id", userDto.getId());	
			session.setAttribute("user_name", userDto.getUser_name());	
			session.setAttribute("warehouse_id", userDto.getWarehouse_id());
			
		}catch (NullPointerException e) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('ID 또는 비밀번호가 일치하지 않습니다.'); location.href='loginForm.do'; </script>");
			out.flush();
		} finally {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='goodsStockInfo.do'; </script>");
			out.flush();
		}
		
	}
	
	@RequestMapping("/main.do")
	public String mainForm() {
		return "main";
	}
	
	//입고 첫 페이지 
	@RequestMapping(value ="/in_warehouse.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public ModelAndView inWarehouseForm() {
		ModelAndView m = new ModelAndView("in_warehouse");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date(System.currentTimeMillis()));
		List<v_RequestHistory> list1 = userService.in_warehouseList(date);
		System.out.println("돌아옴");
		System.out.println("list: "+list1.toString());
		m.addObject("requestHistory", list1);
		List<Warehouse> list2 = userService.warehouseName();
		System.out.println("warehouseName: "+list2.toString());
		m.addObject("warehouseName", list2);
		m.addObject("date01", date);
		m.addObject("date02", date);
		return m;
	}

	//모달 입고신청 타이틀 검색
	@RequestMapping(value="/in_warehouse_search.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String inWareHouseSearch(String title) {
		
		System.out.println("search: "+title);
		Gson gson = new Gson();
		List<Book> list = userService.in_warehouseSearch(title);
		System.out.println("searchList: "+list.toString());
		return gson.toJson(list);
	}
	
	//입고리스트 날짜검색
	@RequestMapping(value ="/in_warehouseDateSearch.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public ModelAndView inWareHouseDateSearch(Date date01, Date date02) {
		ModelAndView m = new ModelAndView("in_warehouse");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date_1 = dateFormat.format(date01);
		String date_2 = dateFormat.format(date02);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date01", date_1);
		map.put("date02", date_2);
		
		List<v_RequestHistory> list1 = userService.in_warehouseDateList(map);
		System.out.println("date돌아옴");
		System.out.println("list: "+list1.toString());
		m.addObject("requestHistory", list1);
		List<Warehouse> list2 = userService.warehouseName();
		System.out.println("warehouseName: "+list2.toString());
		m.addObject("warehouseName", list2);
		m.addObject("date01", date_1);
		m.addObject("date02", date_2);
		return m;
	}
	
	//입고신청 리스트 추가
	@RequestMapping(value ="/in_warehouse_insert.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String in_warehouseInsert(String isbn, Date due_date, int scheduled_amount, int warehouse_id) {
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(due_date);
		
		//입고신청번호 만들기 
		boolean randomB = true;
		String request_number = "";
		while (randomB) {
			Random ran = new Random();
			int randome = ran.nextInt(9000000)+1000000;
			request_number = "A"+randome;
			int ranNum = userService.in_warehouseRandom(request_number);
			if(ranNum == 0) {
				randomB = false;
			}
			System.out.println("randNum: "+request_number);
		}
		
		HashMap<String, Object> m = new HashMap<String, Object>();
		m.put("request_number", request_number);
		m.put("isbn", isbn);
		m.put("due_date", date);
		m.put("scheduled_amount", scheduled_amount);
		m.put("warehouse_id", warehouse_id);
		
		int count = 0;
		if(userService.in_warehouseInsert(m) > 0) {
			count++;
			System.out.println("count: "+count);
		}
		json.addProperty("cnt", count);
		return gson.toJson(json);
	}
	
	//입고신청 리스트 삭제
	@RequestMapping(value ="/in_warehouseDelectRequest.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String in_warehouseDelectRequest(String request_number, Date date01, Date date02, String isbn) {
		System.out.println("request_number: "+request_number);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date_1 = dateFormat.format(date01);
		String date_2 = dateFormat.format(date02);
		
		HashMap<String, String> m = new HashMap<String, String>();
		m.put("request_number", request_number);
		m.put("isbn", isbn);
		
		userService.in_warehouseDelete(m);
		return "redirect:/in_warehouseDateSearch.do?date01="+date_1+"&date02="+date_2+"";
	}
	
	//입고신청 완료 
	@RequestMapping(value ="/in_warehouse_update.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String in_warehouseUpdate(String request_number, String isbn, Date due_date, int scheduled_amount, int warehouse_id) {
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(due_date);
		
		HashMap<String, Object> m = new HashMap<String, Object>();
		m.put("request_number", request_number);
		m.put("isbn", isbn);
		m.put("completion_date", date);
		m.put("amount", scheduled_amount);
		m.put("complete_warehouse_id", warehouse_id);
		
		int count = 0;
		if(userService.in_warehouseUpdate(m) > 0) {
			count++;
			
			if(userService.in_warehouseStocks(m) > 0) {
				int numUp = userService.in_warehouseStocksUp(m);
				System.out.println("up: "+numUp);
			}else {
				System.out.println("없다"); 
				if(userService.in_warehouseStocksFirst(m)> 0) {
					int numIn = userService.in_warehouseStocksIn(m);
					System.out.println("in: "+numIn);
				}else {
					System.out.println("없다2");
					int numIn = userService.in_warehouseStocksInFirst(m);
					System.out.println("inF: "+numIn);
				}
			}
			
			System.out.println("count: "+count);
		}
		json.addProperty("cnt", count);
		return gson.toJson(json);
	}
	
	
	
	//-----------------------------------출고
	
		//출고 첫 페이지 
		@RequestMapping(value ="/out_warehouse.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		@ResponseBody
		public ModelAndView outWarehouseForm() {
			ModelAndView m = new ModelAndView("out_warehouse");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(new Date(System.currentTimeMillis()));
			List<v_RequestHistory> list1 = userService.out_warehouseList(date);
			System.out.println("돌아옴");
			System.out.println("list: "+list1.toString());
			m.addObject("requestHistory", list1);
			List<Warehouse> list2 = userService.warehouseName();
			List<Type> list3 = userService.typeName();
			System.out.println("warehouseName: "+list2.toString());
			m.addObject("warehouseName", list2);
			m.addObject("type", list3);
			m.addObject("date01", date);
			m.addObject("date02", date);
			return m;
		}
	
		
		//출고리스트 날짜검색
		@RequestMapping(value ="/out_warehouseDateSearch.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		@ResponseBody
		public ModelAndView outWareHouseDateSearch(Date date01, Date date02) {
			ModelAndView m = new ModelAndView("out_warehouse");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date_1 = dateFormat.format(date01);
			String date_2 = dateFormat.format(date02);
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("date01", date_1);
			map.put("date02", date_2);
			
			List<v_RequestHistory> list1 = userService.out_warehouseDateList(map);
			System.out.println("date돌아옴");
			System.out.println("list: "+list1.toString());
			m.addObject("requestHistory", list1);
			List<Warehouse> list2 = userService.warehouseName();
			System.out.println("warehouseName: "+list2.toString());
			List<Type> list3 = userService.typeName();
			m.addObject("warehouseName", list2);
			m.addObject("type", list3);
			m.addObject("date01", date_1);
			m.addObject("date02", date_2);
			return m;
		}
		
		
		//출고신청 리스트 추가
		@RequestMapping(value ="/out_warehouse_insert.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		@ResponseBody
		public String out_warehouseInsert(String isbn, Date due_date, int scheduled_amount, int warehouse_id, int type_id, String destination) {
			Gson gson = new Gson();
			JsonObject json = new JsonObject();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(due_date);
			
			//입고신청번호 만들기 
			boolean randomB = true;
			String request_number = "";
			while (randomB) {
				Random ran = new Random();
				int randome = ran.nextInt(9000000)+1000000;
				if(type_id == 2) {
					request_number = "B"+randome;
				}else {
					request_number = "S"+randome;
				}
				
				int ranNum = userService.in_warehouseRandom(request_number);
				if(ranNum == 0) {
					randomB = false;
				}
				System.out.println("randNum: "+request_number);
			}
			
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("request_number", request_number);
			m.put("isbn", isbn);
			m.put("due_date", date);
			m.put("scheduled_amount", scheduled_amount);
			m.put("warehouse_id", warehouse_id);
			m.put("type_id", type_id);
			m.put("destination", destination);
			
			int count = 0;
			if(userService.out_warehouseInsert(m) > 0) {
				count++;
				System.out.println("count: "+count);
			}
			json.addProperty("cnt", count);
			return gson.toJson(json);
		}
		
		//출고신청 리스트 삭제
		@RequestMapping(value ="/out_warehouseDelectRequest.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public String out_warehouseDelectRequest(String request_number, Date date01, Date date02, String isbn, int type_id) {
			System.out.println("request_number: "+request_number);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date_1 = dateFormat.format(date01);
			String date_2 = dateFormat.format(date02);
			
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("request_number", request_number);
			m.put("isbn", isbn);
			m.put("type_id", type_id);
			
			userService.out_warehouseDelete(m);
			return "redirect:/out_warehouseDateSearch.do?date01="+date_1+"&date02="+date_2+"";
		}
		
		
		//출고신청 완료 
		@RequestMapping(value ="/out_warehouse_update.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		@ResponseBody
		public String out_warehouseUpdate(String request_number, String isbn, Date due_date, int scheduled_amount, int warehouse_id) {
			Gson gson = new Gson();
			JsonObject json = new JsonObject();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(due_date);
			
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("request_number", request_number);
			m.put("isbn", isbn);
			m.put("completion_date", date);
			m.put("amount", scheduled_amount);
			m.put("complete_warehouse_id", warehouse_id);
			
			int count = 0;
			if(userService.out_warehouseUpdate(m) > 0) {
				count++;
				
				if(userService.in_warehouseStocks(m) > 0) {
					int numUp = userService.out_warehouseStocksUp(m);
					System.out.println("up: "+numUp);
				}else {
					System.out.println("없다"); 
					int numIn = userService.out_warehouseStocksIn(m);
					System.out.println("in: "+numIn);
				}
				
				System.out.println("count: "+count);
			}
			json.addProperty("cnt", count);
			return gson.toJson(json);
		}
		
		
		
		//출고 재고 확인
		@RequestMapping(value ="/out_warehouse_stockCheck.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		@ResponseBody
		public String out_warehouseStockCheck(String isbn, Date due_date, int warehouse_id) {
			Gson gson = new Gson();
			JsonObject json = new JsonObject();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(due_date);
			System.out.println("date: "+date);
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("isbn", isbn);
			m.put("completion_date", date);
			m.put("complete_warehouse_id", warehouse_id);
			System.out.println(m.toString());
			int stock = 0;
			int ch = userService.in_warehouseStocks(m);
			System.out.println("ch: "+ch);
			if(ch > 0) {
				System.out.println("있음");
				//출고일까지의 재고 
				int stockOkNum = userService.out_stockOk(m);
				System.out.println("stockOkNum: "+stockOkNum);
				int stockInNum = userService.out_stockInNum(m);
				System.out.println("storckIn: "+stockInNum);
				int stockOutNum = userService.out_stockOutNum(m);
				System.out.println("storckOut: "+stockOutNum);
				stock = (stockOkNum+stockInNum)-stockOutNum;
				System.out.println("storck: "+stock);
			}else {
				System.out.println("없다");
				//출고일의 가장 근접한 날까지의 재고 
				int stockNoNum = userService.out_stockNo(m);
				System.out.println("stockNoNum: "+stockNoNum);
				int stockInNum = userService.out_stockInNum(m);
				System.out.println("storckIn: "+stockInNum);
				int stockOutNum = userService.out_stockOutNum(m);
				System.out.println("storckOut: "+stockOutNum);
				stock = (stockNoNum+stockInNum)-stockOutNum;
				System.out.println("storck: "+stock);
			}
			
			json.addProperty("stock", stock);
			return gson.toJson(json);
		}
		
	
		
		
	//-----------------------------창고
	
	//창고 첫 페이지 
	@RequestMapping(value ="/warehouse.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public ModelAndView warehouseForm() {
		ModelAndView m = new ModelAndView("warehouse");
		List<Warehouse> list1 = userService.warehouseList();
		m.addObject("warehouseList", list1);
		return m;
	}
	
	//창고등록
	@RequestMapping(value ="/warehouseInsert.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String warehouseInsert(String warehouse_name, int zip, String address, String address_detail, int employee_count, int maximum_quantity) {
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		
		HashMap<String, Object> m = new HashMap<String, Object>();
		m.put("warehouse_name", warehouse_name);
		m.put("zip", zip);
		m.put("address", address);
		m.put("address_detail", address_detail);
		m.put("employee_count", employee_count);
		m.put("maximum_quantity", maximum_quantity);
				
		int count = 0;
		
		if(userService.warehouseInsert(m) > 0) {
			count++;
			System.out.println("count: "+count);
		}
			json.addProperty("cnt", count);
			return gson.toJson(json);
	}
	
	//창고수정
		@RequestMapping(value ="/warehouseUpdate.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		@ResponseBody
		public String warehouseUpdate(int warehouse_id, String warehouse_name, int zip, String address, String address_detail, int employee_count, int maximum_quantity) {
			Gson gson = new Gson();
			JsonObject json = new JsonObject();
			
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("warehouse_id", warehouse_id);
			m.put("warehouse_name", warehouse_name);
			m.put("zip", zip);
			m.put("address", address);
			m.put("address_detail", address_detail);
			m.put("employee_count", employee_count);
			m.put("maximum_quantity", maximum_quantity);
					
			int count = 0;
			
			if(userService.warehouseUpdate(m) > 0) {
				count++;
				System.out.println("count: "+count);
			}
				json.addProperty("cnt", count);
				return gson.toJson(json);
		}
		//출고신청 리스트 삭제
		@RequestMapping(value ="/warehouseDelete.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public String warehouseDelete(int warehouse_id) {
			userService.warehouseDelete(warehouse_id);
			return "redirect:/warehouse.do";
		}
		
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//자동으로 원하는 형식으로 포멧해줌 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		//true null값 허용, false null값 불허
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
