package book.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import book.model.Book;
import book.model.RequestHistoryProgress;
import book.model.Stock;
import book.model.StockDao;
import book.model.Warehouse;
import book.service.GoodsService;

@Controller
public class GoodsController {

	@Autowired
	GoodsService service;

	@Autowired
	public void setService(GoodsService service) {
		this.service = service;
	}

	/*
	 * @Autowired RestTemplate resTemplate;
	 * 
	 * @Autowired public void setResTemplate(RestTemplate resTemplate) {
	 * this.resTemplate = resTemplate; }
	 */

	@RequestMapping(value = "/booksInfo.do", method = RequestMethod.GET)
	public ModelAndView getBooksInfo() {
		ModelAndView mav = new ModelAndView("booksList");
		mav.addObject("books", service.getBookList());
		return mav;
	}

	// 사용안함
	/*
	 * @RequestMapping(value = "/addGoods.do", method = RequestMethod.GET) public
	 * ModelAndView addGoodsForm() { ModelAndView mav = new
	 * ModelAndView("booksInfo/addGoodsForm"); mav.addObject("category",
	 * service.getCategoryList()); return mav; }
	 */

	// 국중api검색
	/*
	 * @RequestMapping(value = "/searchBook.do", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String searchBook(String keyword) { // 국중open api주소
	 * String url = "https://www.nl.go.kr/NL/search/openApi/search.do?"; // 인증키
	 * String key =
	 * "key=140af29a41cf7f8643b8a0675525268d1aa8e0cef32ffc324903755c30e63f26"; //
	 * 상세검색 boolean String detailSearch = "detailSearch=true"; // 검색키워드 and조건 String
	 * and = "and1=and"; // 상세검색조건1 제목 String searchTargetTitle = "f1=title"; //
	 * 상세조건검색2 출판사 String searchTargetPublisher = "f2=publisher"; // 상세조건검색2 입력값 -
	 * 민음사고정 String publisherKwd = "v2=민음사"; // 온.오프라인자료 구분 String systemType =
	 * "systemType=오프라인자료"; // 자료형태 구분 String category = "category=도서"; // 쪽당출력건수
	 * -default : 10 String pageSize = "pageSize=10"; // 쪽 String pageNum =
	 * "pageNum=1"; // 데이터타입 String apiType = "apiType=json";
	 * 
	 * String searchUrl = url + key + "&" + detailSearch + "&" + and + "&" +
	 * searchTargetPublisher + "&" + publisherKwd + "&" + systemType + "&" +
	 * category + "&" + pageSize + "&" + pageNum + "&" + searchTargetTitle + "&" +
	 * apiType + "&v1=" + keyword;
	 * 
	 * System.out.println(keyword);
	 * 
	 * MarshallingHttpMessageConverter marshallingConverter = new
	 * MarshallingHttpMessageConverter();
	 * marshallingConverter.setSupportedMediaTypes(Arrays.asList(MediaType.XML_UTF_8
	 * )); resTemplate.getMessageConverters().add(marshallingConverter);
	 * 
	 * Object obj = resTemplate.getForObject(searchUrl, Object.class); Gson json =
	 * new Gson(); System.out.println(obj); return json.toJson((BookData) obj); }
	 */

	// 이미 등록된 도서인지 확인 후, 없는 경우 추가
	@RequestMapping(value = "/insertBook.do", method = RequestMethod.POST)
	@ResponseBody
	public int insertBook(Book book) {
		int count = 0;
		System.out.println(book.getIsbn());
		if (service.checkBook(book.getIsbn()) > 0) {
			// 이미등록된 제품
			return -1;
		} else {
			count = service.addBook(book);
		}
		return count;
	}

	// 책제목검색
	@RequestMapping(value = "/searchBook.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String searchBook(String title) {
		List<Book> goodsList = service.searchBook(title);
		Gson gson = new Gson();
		return gson.toJson(goodsList);
	}

	/*
	 * //제품별재고목록
	 * 
	 * @RequestMapping(value = "/goodsStockInfo.do", method = RequestMethod.GET)
	 * public ModelAndView getGoodsStockInfo(@RequestParam(value = "date",
	 * defaultValue = "0", required = false)int date, @RequestParam(value = "isbn",
	 * defaultValue = "0", required = false)String isbn) { ModelAndView mav = new
	 * ModelAndView("goodsStockInfo"); SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyyMM"); //현재달 String month = sdf.format(new Date());
	 * //첫화면 OR 현재를 골랐을 경우 if(date == 0 || Integer.parseInt(month) == date) {
	 * if(isbn.equals("0")) {//isbn선택x -> 전체품목재고 String }else {//특정품목선택
	 * 
	 * }
	 * 
	 * }else {//과거달선택 if(isbn.equals("0")) {//isbn선택x -> 전체품목재고
	 * mav.addObject("stockHistory",
	 * service.getStockHistory(Integer.toString(date),isbn));
	 * 
	 * }else {//특정품목선택
	 * 
	 * } } mav.addObject("stockHistory", service.getAllStockHistory(month));
	 * mav.addObject("todayStock", service.getAllTodayStock()); SimpleDateFormat
	 * sdf2 = new SimpleDateFormat("yyyy-MM-dd"); Calendar cal =
	 * Calendar.getInstance(); //그 달의 마지막날 int max =
	 * cal.getActualMaximum(Calendar.DAY_OF_MONTH); //현재 날짜 int nowDay =
	 * cal.DAY_OF_MONTH;
	 * 
	 * List<MonthlyHistory> stockHistoryArr = new ArrayList<MonthlyHistory>();
	 * for(int i = max; i <= nowDay; i++) { stockHistoryArr.add(service.get) }
	 * //현재재고 if(date == 0|| date == Integer.parseInt(month)) {
	 * 
	 * } else {//과거재고 이력조회 if(Integer.parseInt(month) > date) {
	 * mav.addObject("stockHistory", service.getAllStockHistory(date));
	 * 
	 * } }
	 * 
	 * return mav; }
	 */

	// 현재, 제품별 재고조회
	public List<Stock> getNowAllStocks(List<Book> goodsList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String start = "01";
		String end = Integer.toString(Calendar.getInstance().getActualMaximum(Calendar.getInstance().DAY_OF_MONTH));
		String startDate = sdf.format(new Date()) + "-" + start;
		// System.out.println("startDate : " + startDate);
		//String endDate = sdf.format(new Date()) + "-" + end;
		// 과거 + 현재까지의 재고를 쌓을 리스트
		List<Stock> stockArr = new ArrayList<Stock>();
		// List<RequestHistoryProgress> progressArr = new
		// ArrayList<RequestHistoryProgress>();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf2.format(new Date());
		int day = Integer.parseInt(today.split("-")[2]);
		// 기간내의 재고이력
		for (int j = 0; j <= goodsList.size() - 1; j++) {
			int stockAmount = service.getRecentStock(goodsList.get(j).getIsbn(), startDate);

			// 1일부터 어제까지
			for (int i = Integer.parseInt(start); i < day - 1; i++) {
				String date = sdf.format(new Date());
				if (i < 10) {
					date = date + "-0" + i;
				} else {
					date = date + "-" + i;
				}
				// date = sdf.format(new Date() + "-" + i);
				String isbn = goodsList.get(j).getIsbn();
				int count = service.checkStockCount(isbn, date);
				if (count == 0) {
					Stock stockDump = new Stock();
					Date d = null;
					try {
						d = sdf2.parse(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stockDump.setDumpDate(date);
					stockDump.setDate(d);
					stockDump.setIncoming_amount(0);
					stockDump.setRelease_amount(0);
					stockDump.setTitle(goodsList.get(j).getTitle());
					stockDump.setStock(stockAmount);
					stockDump.setIsbn(goodsList.get(j).getIsbn());
					stockDump.setStock(stockAmount);
					stockArr.add(stockDump);
				} else {
					Stock stockDump = service.getGoodsStock(goodsList.get(j).getIsbn(), date);
					stockDump.setDumpDate(date);
					stockAmount = stockDump.getStock();
					stockArr.add(stockDump);
				}
			}

			// 오늘재고->재고&&예정같이
			int todayStockCount = service.checkStockCount(goodsList.get(j).getIsbn(), today);
			int incomingDump = 0;
			int releaseDump = 0;

			// 오늘재고가 존재
			if (todayStockCount != 0) {
				stockAmount = service.getGoodsStock(goodsList.get(j).getIsbn(), today).getStock();
				/*
				 * incomingDump = incomingDump + stockDump.getIncoming_amount(); releaseDump =
				 * releaseDump + stockDump.getRelease_amount();
				 */
			}
			// 오늘 입출고예정이 존재
			RequestHistoryProgress todayProgressDump = null;
			if (service.checkProgressCount(goodsList.get(j).getIsbn(), today) != 0) {
				todayProgressDump = service.getGoodsProgress(goodsList.get(j).getIsbn(), today);
				incomingDump = incomingDump + todayProgressDump.getIncoming_amount();
				releaseDump = releaseDump + todayProgressDump.getRelease_amount();
			}
			// 과거 + 오늘까지
			Stock stockDump = new Stock();
			stockDump.setDate(new Date());
			stockDump.setDumpDate(today);
			stockDump.setIncoming_amount(incomingDump);
			stockDump.setRelease_amount(releaseDump);
			stockDump.setTitle(goodsList.get(j).getTitle());
			//추이 반영 되지 않은 현재 재고set
			stockDump.setStock(stockAmount);
			stockDump.setIsbn(goodsList.get(j).getIsbn());
			stockArr.add(stockDump);
			//다음날 재고예상 -> 아직 반영되지 않은 예정 반영 시킨 재고에서 시작
			stockAmount = stockAmount + incomingDump - releaseDump;
			// 미래재고추이-내일부터 말일까지
			for (int i = day + 1; i <= Integer.parseInt(end); i++) {
				String date = sdf.format(new Date());
				if (i < 10) {
					date = date + "-0" + i;
				} else {
					date = date + "-" + i;
				}
				int progressCount = service.checkProgressCount(goodsList.get(j).getIsbn(), date);
				Date d = null;
				try {
					d = sdf2.parse(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 신청이 없는 경우 -> 원래 재고를 가지고 내려온다.
				if (progressCount == 0) {
					Stock progressStockDump = new Stock();
					progressStockDump.setDumpDate(date);
					progressStockDump.setDate(d);
					progressStockDump.setIncoming_amount(0);
					progressStockDump.setRelease_amount(0);
					progressStockDump.setStock(stockAmount);
					progressStockDump.setTitle(goodsList.get(j).getTitle());
					progressStockDump.setIsbn(goodsList.get(j).getIsbn());
					stockArr.add(progressStockDump);
				} else {
					RequestHistoryProgress progressDump = service.getGoodsProgress(goodsList.get(j).getIsbn(), date);
					stockAmount = stockAmount + progressDump.getIncoming_amount() - progressDump.getRelease_amount();
					Stock progressStockDump = new Stock();
					progressStockDump.setDate(d);
					progressStockDump.setDumpDate(date);
					progressStockDump.setIncoming_amount(progressDump.getIncoming_amount());
					progressStockDump.setRelease_amount(progressDump.getRelease_amount());
					progressStockDump.setStock(stockAmount);
					progressStockDump.setTitle(goodsList.get(j).getTitle());
					progressStockDump.setIsbn(goodsList.get(j).getIsbn());
					stockArr.add(progressStockDump);
				}
			}

		}
		return stockArr;
	}

	// 첫재고화면
	@RequestMapping(value = "/goodsStockInfo.do", method = RequestMethod.GET)
	public ModelAndView getGoodsStock() {
		ModelAndView mav = new ModelAndView("goodsStockInfo");

		// 재고가 있는 품목리스트
		List<Book> goodsList = service.getGoodsName();
		mav.addObject("goodsList", goodsList);
		//제품별재고
		mav.addObject("stockHistory", getNowAllStocks(goodsList));
		//모든창고리스트
		mav.addObject("warehouseList", service.getWarehouseList());
		//창고재고
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date nowDate = new Date();
		String month = sdf.format(nowDate);
		mav.addObject("warehouseStockHistory", getNowWarehouseStock(0, "0", month));
		return mav;
	}

	@RequestMapping(value = "/calcMonthStock.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String calcStock(String isbn, String month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		//SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		String now = sdf.format(nowDate);
		//지정한 달의 date형
		Date monthDate = null;
		// 과거 + 현재까지의 재고를 쌓을 리스트
		List<Stock> stockArr = new ArrayList<Stock>();
		try {
			monthDate = sdf.parse(month);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Book> goodsList = new ArrayList<Book>();
		if(isbn.equals("0")) {//전제품
			goodsList = service.getGoodsName();
			if(month.equals(now)) {//현재시점
				//전제품 && 현재 -> 첫화면 동일처리
				stockArr = getNowAllStocks(goodsList);
			}else{//과거시점
				stockArr = getPastAllStocks(monthDate, month, goodsList);
			}
		}else{//특정제품한정
			Book goodsDump = new Book();
			goodsDump.setIsbn(isbn);
			goodsDump.setTitle(service.getTitle(isbn));
			goodsList.add(goodsDump);
			if(month.equals(now)) {//현재시점
				//한 제품만 list에 넣어서 getNowAllStocks같이 사용
				stockArr = getNowAllStocks(goodsList);
			}else{//과거시점
				//특정제품하나 && 과거시점
				stockArr = getPastAllStocks(monthDate, month, goodsList);
				
			}
		}

		Gson gson = new Gson();
		return gson.toJson(stockArr);
	}
	
	//과거시점재고 -> stock만 보면 되는 것.
	public List<Stock> getPastAllStocks(Date monthDate, String month, List<Book> goodsList){
		List<Stock> stockArr = new ArrayList<Stock>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(monthDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String start = "01";
		String end = Integer.toString(cal.getActualMaximum(Calendar.getInstance().DAY_OF_MONTH));
		String startDate = sdf.format(monthDate) + "-" + start;
		// System.out.println("startDate : " + startDate);
		//String endDate = sdf.format(monthDate) + "-" + end;
		
		for(int j = 0; j <= goodsList.size() - 1; j++) {
			int stockAmount = service.getRecentStock(goodsList.get(j).getIsbn(), startDate);
			// 1일부터 어제까지
			for (int i = Integer.parseInt(start); i <= Integer.parseInt(end); i++) {
				String date = month;
				if (i < 10) {
					date = date + "-0" + i;
				} else {
					date = date + "-" + i;
				}
				// date = sdf.format(new Date() + "-" + i);
				int count = service.checkStockCount(goodsList.get(j).getIsbn(), date);
				if (count == 0) {
					Stock stockDump = new Stock();
					Date d2 = null;
					try {
						d2 = sdf2.parse(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stockDump.setDumpDate(date);
					stockDump.setDate(d2);
					stockDump.setIncoming_amount(0);
					stockDump.setRelease_amount(0);
					stockDump.setIsbn(goodsList.get(j).getIsbn());
					stockDump.setTitle(goodsList.get(j).getTitle());
					stockDump.setStock(stockAmount);
					stockArr.add(stockDump);
				} else {
					Stock stockDump = service.getGoodsStock(goodsList.get(j).getIsbn(), date);
					stockDump.setDumpDate(date);
					stockAmount = stockDump.getStock();
					stockArr.add(stockDump);
				}
			}
		}
		return stockArr;
	}
	
	
	
	
	
	//전체창고재고-과거
	public List<Stock> getPastWarehouseAllStock(List<Warehouse> warehouseList, String isbn, String month){
		List<Stock> stockArr = new ArrayList<Stock>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date monthDate = null;
		try {
			monthDate = sdf.parse(month);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.setTime(monthDate);
		String start = "01";
		String end = Integer.toString(cal.getActualMaximum(Calendar.getInstance().DAY_OF_MONTH));
		String startDate = sdf.format(monthDate) + "-" + start;
		
		for (Warehouse warehouse : warehouseList) {
			if(isbn.equals("0")) {//전제품 -> 창고별로만 집계한다
				//1일전의 마지막 재고합계
				int stockAmount = service.getRecentWarehouseAllStock(startDate, warehouse.getWarehouse_id());
				for(int i = 1 ; i <= Integer.parseInt(end); i++) {
					String date = month;
					if (i < 10) {
						date = date + "-0" + i;
					} else {
						date = date + "-" + i;
					}
					int count = service.checkAllWarehouseStock(date, warehouse.getWarehouse_id());
					if(count == 0) {//재고가 없다.
						Stock stockDump = new Stock();
						stockDump.setDumpDate(date);
						stockDump.setStock(stockAmount);
						stockDump.setIncoming_amount(0);
						stockDump.setRelease_amount(0);
						stockDump.setWarehouse_name(service.getWarehouseName(warehouse.getWarehouse_id()));
						stockArr.add(stockDump);
					}else {//재고가있다.
						Stock stockDump = service.getAllWarehouseStock(date, warehouse.getWarehouse_id());
						stockDump.setDumpDate(date);
						stockAmount = stockDump.getStock();
						stockArr.add(stockDump);
					}
					
				}
				
			}else {//특정제품 ->선택한 창고
				int stockAmount = service.getRecentWarehouseGoodsStock(isbn, startDate, warehouse.getWarehouse_id());
				for(int i = 1 ; i <= Integer.parseInt(end); i++) {
					String date = month;
					if (i < 10) {
						date = date + "-0" + i;
					} else {
						date = date + "-" + i;
					}
					int count = service.checkWarehouseGoodsStock(isbn, date, warehouse.getWarehouse_id());
					if(count == 0) {//재고가 없다.
						Stock stockDump = new Stock();
						stockDump.setDumpDate(date);
						stockDump.setStock(stockAmount);
						stockDump.setIncoming_amount(0);
						stockDump.setRelease_amount(0);
						stockDump.setWarehouse_name(service.getWarehouseName(warehouse.getWarehouse_id()));
						stockArr.add(stockDump);
					}else {//재고가있다.
						Stock stockDump = service.getAllWarehouseStock(date, warehouse.getWarehouse_id());
						stockDump.setDumpDate(date);
						stockAmount = stockDump.getStock();
						stockArr.add(stockDump);
					}
					
				}
			}
				
		}
		
		return stockArr;
	}
	
	
	//현재 창고재고 조회
	public List<Stock> getNowWarehouseStock(int warehouse_id, String isbn, String month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String start = "01";
		String end = Integer.toString(Calendar.getInstance().getActualMaximum(Calendar.getInstance().DAY_OF_MONTH));
		String startDate = sdf.format(new Date()) + "-" + start;
		// System.out.println("startDate : " + startDate);
		//String endDate = sdf.format(new Date()) + "-" + end;
		// 과거 + 현재 + 미래까지의 재고를 쌓을 리스트
		List<Stock> stockArr = new ArrayList<Stock>();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf2.format(new Date());
		int day = Integer.parseInt(today.split("-")[2]);
		List<Warehouse> warehouseList = new ArrayList<Warehouse>();
		if(warehouse_id == 0) {//전체창고
			//전체창고리스트생성
			warehouseList = service.getWarehouseList();
		}else {
			//임의로 사용할 창고생성
			Warehouse warehouseDump = new Warehouse();
			warehouseDump.setWarehouse_id(warehouse_id);
			warehouseDump.setWarehouse_name(service.getWarehouseName(warehouse_id));
			//창고리스트생성
			warehouseList.add(warehouseDump);
		}
		
		//과거까지의 재고 모음(warehouse는 만들어서 넘겨줌 && isbn에 따라서 goodsList는 메서드에서 작성)-전체 OR 특정 공통사용
		
		//오늘 재고+예측 추이 계산
		//어제까지만 재고구하기
		
		if(isbn.equals("0")) {//전체품목
			//전제품 -> 창고별로만 집계한다
			for(Warehouse warehouse : warehouseList) {
				int stockAmount = service.getRecentWarehouseAllStock(startDate, warehouse.getWarehouse_id());
				for(int i = 1 ; i < day - 1; i++) {
					String date = sdf.format(new Date());
					if (i < 10) {
						date = date + "-0" + i;
					} else {
						date = date + "-" + i;
					}
					Date d2 = null;
					try {
						d2 = sdf2.parse(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Stock stockDump = new Stock();
					int count = service.checkAllWarehouseStock(date, warehouse.getWarehouse_id());
					if(count == 0) {
						stockDump.setDate(d2);
						stockDump.setDumpDate(date);
						stockDump.setStock(stockAmount);
						//전체품목의 경우 id, name전부 보관하고 있다.
						stockDump.setWarehouse_name(warehouse.getWarehouse_name());
						stockDump.setIncoming_amount(0);
						stockDump.setRelease_amount(0);
						stockDump.setStock(stockAmount);
					}else {
						stockDump = service.getAllWarehouseStock(date, warehouse.getWarehouse_id());
						stockAmount = stockDump.getStock();
						stockDump.setDumpDate(date);
					}
					stockArr.add(stockDump);
				}
				Date d2 = null;
				try {
					d2 = sdf2.parse(today);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int checkTodayStock = service.checkAllWarehouseStock(today, warehouse.getWarehouse_id());
				int incoming_amount = 0;
				int release_amount = 0;
				//오늘 재고 존재한다.->재고양만 반영시킨다.
				if(checkTodayStock != 0) {
					stockAmount = service.getAllWarehouseStock(today, warehouse.getWarehouse_id()).getStock();
				}
				//오늘 입출고신청이 있다. -> 입출고만 반영시킨다.
				if(service.checkAllWarehouseProgress(today, warehouse.getWarehouse_id()) != 0) {
					RequestHistoryProgress ProgressTodayDump = service.getAllWarehouseProgress(today, warehouse.getWarehouse_id());
					incoming_amount += ProgressTodayDump.getIncoming_amount();
					release_amount += ProgressTodayDump.getRelease_amount();
				}
				Stock stockTodayDump = new Stock();
				stockTodayDump.setDate(d2);
				stockTodayDump.setDumpDate(today);
				stockTodayDump.setIncoming_amount(incoming_amount);
				stockTodayDump.setRelease_amount(release_amount);
				stockTodayDump.setStock(stockAmount);
				stockTodayDump.setWarehouse_name(warehouse.getWarehouse_name());
				stockArr.add(stockTodayDump);
				//오늘 재고 화면에 표시 후, 아직 반영되지 않은 예정 반영하여 새로운 stockAmount계산
				stockAmount = stockAmount + incoming_amount - release_amount;
				//내일부터 말일까지의 추이
				for(int x = day + 1; x <= Integer.parseInt(end); x++) {
					String date = sdf.format(new Date());
					if (x < 10) {
						date = date + "-0" + x;
					} else {
						date = date + "-" + x;
					}
					Date d = null;
					try {
						d = sdf2.parse(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//예측이 존재한다.
					Stock stockProgressDump = new Stock();
					if(service.checkAllWarehouseProgress(date, warehouse.getWarehouse_id()) != 0) {
						RequestHistoryProgress progressDump = service.getAllWarehouseProgress(date, warehouse.getWarehouse_id());
						stockProgressDump.setDate(d);
						stockProgressDump.setDumpDate(date);
						stockProgressDump.setIncoming_amount(progressDump.getIncoming_amount());
						stockProgressDump.setRelease_amount(progressDump.getRelease_amount());
						stockProgressDump.setWarehouse_name(progressDump.getWarehouse_name());
						stockAmount = stockAmount + stockProgressDump.getIncoming_amount() - stockProgressDump.getRelease_amount();
						stockProgressDump.setStock(stockAmount);
					}else {
						stockProgressDump.setDate(d);
						stockProgressDump.setDumpDate(date);
						stockProgressDump.setIncoming_amount(0);
						stockProgressDump.setRelease_amount(0);
						stockProgressDump.setWarehouse_name(service.getWarehouseName(warehouse.getWarehouse_id()));
						stockProgressDump.setStock(stockAmount);
					}
					stockArr.add(stockProgressDump);
					
				}
			}
			
		}else {//특정품목
			
			for(Warehouse warehouse : warehouseList) {
				int dumpId = warehouse.getWarehouse_id();
				int stockAmount = service.getRecentWarehouseGoodsStock(isbn, startDate, dumpId);
				for(int i = 1 ; i < day - 1; i++) {
					String date = sdf.format(new Date());
					if (i < 10) {
						date = date + "-0" + i;
					} else {
						date = date + "-" + i;
					}
					Date d = null;
					try {
						d = sdf2.parse(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Stock stockDump = new Stock();
					int count = service.checkWarehouseGoodsStock(isbn, date, warehouse.getWarehouse_id());
					if(count == 0) {
						stockDump.setDate(d);
						stockDump.setDumpDate(date);
						stockDump.setStock(stockAmount);
						//전체품목의 경우 id, name전부 보관하고 있다.
						stockDump.setWarehouse_name(warehouse.getWarehouse_name());
						stockDump.setIncoming_amount(0);
						stockDump.setRelease_amount(0);
						stockDump.setStock(stockAmount);
					}else {
						stockDump.setDate(d);
						stockDump = service.getWarehouseStock(isbn, date, warehouse.getWarehouse_id());
						stockAmount = stockDump.getStock();
						stockDump.setDumpDate(date);
					}
					stockArr.add(stockDump);
				}
				Date d = null;
				try {
					d = sdf2.parse(today);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int checkTodayStock = service.checkWarehouseGoodsStock(isbn, today, warehouse.getWarehouse_id());
				int incoming_amount = 0;
				int release_amount = 0;
				//오늘 재고 존재한다.->재고양만 반영시킨다.
				if(checkTodayStock != 0) {
					stockAmount = service.getWarehouseStock(isbn, today, warehouse.getWarehouse_id()).getStock();
				}
				//오늘 입출고신청이 있다. -> 입출고만 반영시킨다.
				if(service.checkWarehouseProgress(isbn, today, warehouse.getWarehouse_id()) != 0) {
					RequestHistoryProgress ProgressTodayDump = service.getWarehouseProgress(isbn, today, warehouse.getWarehouse_id());
					incoming_amount += ProgressTodayDump.getIncoming_amount();
					release_amount += ProgressTodayDump.getRelease_amount();
				}
				Stock stockTodayDump = new Stock();
				stockTodayDump.setDate(d);
				stockTodayDump.setDumpDate(today);
				stockTodayDump.setIncoming_amount(incoming_amount);
				stockTodayDump.setRelease_amount(release_amount);
				stockTodayDump.setStock(stockAmount);
				stockTodayDump.setWarehouse_name(warehouse.getWarehouse_name());
				stockArr.add(stockTodayDump);
				//오늘 재고 화면에 표시 후, 아직 반영되지 않은 예정 반영하여 새로운 stockAmount계산
				stockAmount = stockAmount + incoming_amount - release_amount;
				
				
				
				//내일부터 말일까지의 추이
				for(int x = day + 1; x <= Integer.parseInt(end); x++) {
					String date = sdf.format(new Date());
					if (x < 10) {
						date = date + "-0" + x;
					} else {
						date = date + "-" + x;
					}
					Date d2 = null;
					try {
						d2 = sdf2.parse(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//예측이 존재한다.
					Stock stockProgressDump = new Stock();
					if(service.checkWarehouseGoodsStock(isbn, date, warehouse.getWarehouse_id()) != 0) {
						RequestHistoryProgress progressDump = service.getWarehouseProgress(isbn, date, warehouse.getWarehouse_id());
						stockProgressDump.setDate(d2);
						stockProgressDump.setDumpDate(date);
						stockProgressDump.setIncoming_amount(progressDump.getIncoming_amount());
						stockProgressDump.setRelease_amount(progressDump.getRelease_amount());
						stockProgressDump.setWarehouse_name(progressDump.getWarehouse_name());
						stockAmount = stockAmount + stockProgressDump.getIncoming_amount() - stockProgressDump.getRelease_amount();
						stockProgressDump.setStock(stockAmount);
					}else {
						stockProgressDump.setDate(d2);
						stockProgressDump.setDumpDate(date);
						stockProgressDump.setIncoming_amount(0);
						stockProgressDump.setRelease_amount(0);
						stockProgressDump.setWarehouse_name(service.getWarehouseName(warehouse.getWarehouse_id()));
						stockProgressDump.setStock(stockAmount);
					}
					stockArr.add(stockProgressDump);
				}
			}
			
		}
		
		return stockArr;
		
	}
	
	@RequestMapping(value = "/calcWarehouseStock", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String calcWarehouseStock(String isbn, String month, int warehouse_id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		//SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		String now = sdf.format(nowDate);
		//지정한 달의 date형
		Date monthDate = null;
		// 과거 + 현재까지의 재고를 쌓을 리스트
		List<Stock> stockArr = new ArrayList<Stock>();
		try {
			monthDate = sdf.parse(month);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(month.contentEquals(now)) {//현재
			stockArr = getNowWarehouseStock(warehouse_id, isbn, month);
		}else {//과거
			List<Warehouse> warehouseList = new ArrayList<Warehouse>();
			if(warehouse_id != 0) {
				Warehouse warehouseDump = new Warehouse();
				warehouseDump.setWarehouse_id(warehouse_id);
				warehouseList.add(warehouseDump);
			}else {
				warehouseList = service.getWarehouseList();
			}
			stockArr = getPastWarehouseAllStock(warehouseList, isbn, month);
		}
		Gson gson = new Gson();
		return gson.toJson(stockArr);
	}
	
	
	@RequestMapping(value = "/downExcelGoodsStock.do", method = RequestMethod.POST)
	public void downExcelGoodsStock(String isbn, String month, HttpServletResponse response) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		//SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		String now = sdf.format(nowDate);
		//지정한 달의 date형
		Date monthDate = null;
		// 과거 + 현재까지의 재고를 쌓을 리스트 ->출력데이터
		List<Stock> stockArr = new ArrayList<Stock>();
		try {
			monthDate = sdf.parse(month);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Book> goodsList = new ArrayList<Book>();
		if(isbn.equals("0")) {//전제품
			goodsList = service.getGoodsName();
			if(month.equals(now)) {//현재시점
				//전제품 && 현재 -> 첫화면 동일처리
				stockArr = getNowAllStocks(goodsList);
			}else{//과거시점
				stockArr = getPastAllStocks(monthDate, month, goodsList);
			}
		}else{//특정제품한정
			Book goodsDump = new Book();
			goodsDump.setIsbn(isbn);
			goodsDump.setTitle(service.getTitle(isbn));
			goodsList.add(goodsDump);
			if(month.equals(now)) {//현재시점
				//한 제품만 list에 넣어서 getNowAllStocks같이 사용
				stockArr = getNowAllStocks(goodsList);
			}else{//과거시점
				//특정제품하나 && 과거시점
				stockArr = getPastAllStocks(monthDate, month, goodsList);
				
			}
		}
		
		int excel = service.createExcelStock(response, stockArr, month);
		System.out.println(excel);
		
	}
	
	@RequestMapping(value = "/deleteBook.do", method = RequestMethod.POST)
	@ResponseBody
	public int deleteBook(String isbn) {
		int stock = 0;
		stock = service.checkDeleteBook(isbn);
		//재고가 0 -> 삭제가능
		if(stock == 0) {
			service.deleteBook(isbn);
			return 1;
		}else {//재고존재 -> 삭제불가능
			return 0;
		}
	}
}
