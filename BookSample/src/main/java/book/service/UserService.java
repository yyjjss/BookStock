package book.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.model.Book;
import book.model.RequestHistoryDao;
import book.model.Type;
import book.model.UserDao;
import book.model.UserDto;
import book.model.Warehouse;
import book.model.v_RequestHistory;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	RequestHistoryDao requestHistoryDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setRequestHistoryDao(RequestHistoryDao requestHistoryDao) {
		this.requestHistoryDao = requestHistoryDao;
	}

	public UserDto loginCheck(HashMap<String, String> m) {
		return userDao.loginCheck(m);
	}
	
	//입고 리스트 
	public List<v_RequestHistory> in_warehouseList(String date) {
		return requestHistoryDao.in_warehouseList(date);
	}
	
	//창고이름
	public List<Warehouse> warehouseName(){
		return requestHistoryDao.warehouseName();
	}
	//type list
	public List<Type> typeName(){
		return requestHistoryDao.typeName();
	}
	
	//입고신청검색
	public List<Book> in_warehouseSearch(String title){
		return requestHistoryDao.in_warehouseSearch(title);
	}
	
	//입고 날짜검색 리스트 
	public List<v_RequestHistory> in_warehouseDateList(HashMap<String, String> map){
		return requestHistoryDao.in_warehouseDateList(map);
	}
	
	//입고신청 삭제
	public void in_warehouseDelete(HashMap<String, String> m) {
		requestHistoryDao.in_warehouseDelete(m);
	}
	
	//입고신청 추가
	public int in_warehouseInsert(HashMap<String, Object> m) {
		return requestHistoryDao.in_warehouseInsert(m);
	}
	
	//입고신청 번호 중복체크
	public int in_warehouseRandom(String request_number) {
		return requestHistoryDao.in_warehouseRandom(request_number);
	}
	
	//입고신청 완료 UPDATE 
	public int in_warehouseUpdate(HashMap<String, Object> m) {
		return requestHistoryDao.in_warehouseUpdate(m);
	}
	
	//입고완료시 stocks에 해당 isbn의 데이터가 있는지
	public int in_warehouseStocks(HashMap<String, Object> m) {
		return requestHistoryDao.in_warehouseStocks(m);
	}
	//완전 그 해당 창고에 isbn의 재고확인
	public int in_warehouseStocksFirst(HashMap<String, Object> m) {
		return requestHistoryDao.in_warehouseStocksFirst(m);
	}
	
	//입고완료시 stocks insert
	public int in_warehouseStocksIn(HashMap<String, Object> m) {
		return requestHistoryDao.in_warehouseStocksIn(m);
	}
	//입고완료시 stocks 첫 insert
	public int in_warehouseStocksInFirst(HashMap<String, Object> m) {
		return requestHistoryDao.in_warehouseStocksInFirst(m);
	}
		
	//입고완료시 stocks update
	public int in_warehouseStocksUp(HashMap<String, Object> m) {
		return requestHistoryDao.in_warehouseStocksUp(m);
	}
	
	//-----------------------------출고
	//출고 리스트 
	public List<v_RequestHistory> out_warehouseList(String date) {
		return requestHistoryDao.out_warehouseList(date);
	}
	//출고 날짜검색 리스트 
	public List<v_RequestHistory> out_warehouseDateList(HashMap<String, String> map){
		return requestHistoryDao.out_warehouseDateList(map);
	}
	//출고신청 추가
	public int out_warehouseInsert(HashMap<String, Object> m) {
		return requestHistoryDao.out_warehouseInsert(m);
	}
	//출고신청 삭제
	public void out_warehouseDelete(HashMap<String, Object> m) {
		requestHistoryDao.out_warehouseDelete(m);
	}
	//출고신청 완료 UPDATE 
	public int out_warehouseUpdate(HashMap<String, Object> m) {
		return requestHistoryDao.out_warehouseUpdate(m);
	}
	//출고완료시 stocks update
	public int out_warehouseStocksUp(HashMap<String, Object> m) {
		return requestHistoryDao.out_warehouseStocksUp(m);
	}
	//출고완료시 stocks insert
	public int out_warehouseStocksIn(HashMap<String, Object> m) {
		return requestHistoryDao.out_warehouseStocksIn(m);
	}
	
	
	//stock에 해당 출고일 재고가 존재할시 
	public int out_stockOk(HashMap<String, Object> m) {
		return requestHistoryDao.out_stockOk(m);
	}
	//stock에 해당 출고일 재고가 존재 안할시 
	public int out_stockNo(HashMap<String, Object> m) {
		return requestHistoryDao.out_stockNo(m);
	}
	//출고일까지의 입고 예정수량
	public int out_stockInNum(HashMap<String, Object> m) {
		return requestHistoryDao.out_stockInNum(m);
	}
	//출고일까지의 출고 예정 수량
	public int out_stockOutNum(HashMap<String, Object> m) {
		return requestHistoryDao.out_stockOutNum(m);
	}
	
	
	//창고리스트 
	public List<Warehouse> warehouseList(){
		return userDao.warehouseList();
	}
	//창고 insert
	public int warehouseInsert(HashMap<String, Object> m) {
		return userDao.warehouseInsert(m);
	}
	//창고 update
	public int warehouseUpdate(HashMap<String, Object> m) {
		return userDao.warehouseUpdate(m);
	}
	//창고 delete
	public void warehouseDelete(int warehouse_id) {
		userDao.warehouseDelete(warehouse_id);
	}
	
}
