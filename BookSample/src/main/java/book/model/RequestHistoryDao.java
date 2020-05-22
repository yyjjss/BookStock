package book.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;


public class RequestHistoryDao extends SqlSessionDaoSupport{
	
	//입고 리스트 
	public List<v_RequestHistory> in_warehouseList(String date){
		System.out.println("입고리스트: "+date);
		List<v_RequestHistory> list = getSqlSession().selectList("bookSql.in_warehouseList", date);
		System.out.println("넘어오나");
		return list;
	}
	
	//창고이름 리스트
	public List<Warehouse> warehouseName(){
		List<Warehouse> list = getSqlSession().selectList("bookSql.warehouseName");
		return list;
	}
	
	//타입이름 리스트
	public List<Type> typeName(){
		return getSqlSession().selectList("bookSql.typeName");
	}
	
	//입고신청검색리스트
	public List<Book> in_warehouseSearch(String title){
		System.out.println("입고검색: "+title);
		List<Book> list = getSqlSession().selectList("bookSql.in_warehouseSearch", title); 
		System.out.println("bookSearchDAO");
		return list;
	}
	
	//입고 날짜검색 리스트 
	public List<v_RequestHistory> in_warehouseDateList(HashMap<String, String> map){
		List<v_RequestHistory> list = getSqlSession().selectList("bookSql.in_warehouseDateList", map);
		return list;
	}
	
	//입고신청삭제
	public void in_warehouseDelete(HashMap<String, String> m) {
		getSqlSession().delete("bookSql.in_warehouseDelete", m);
	}
	
	//입고신청 추가
	public int in_warehouseInsert(HashMap<String, Object> m) {
		return getSqlSession().insert("bookSql.in_warehouseInsert", m);
	}
	
	//입고신청 번호 중복체크
	public int in_warehouseRandom(String request_number) {
		return getSqlSession().selectOne("bookSql.in_warehouseRandom", request_number);
	}
	
	//입고신청 완료 UPDATE 
	public int in_warehouseUpdate(HashMap<String, Object> m) {
		return getSqlSession().update("bookSql.in_warehouseUpdate", m);
	}
	
	//입고완료시 stocks에 해당 isbn의 데이터가 있는지
	public int in_warehouseStocks(HashMap<String, Object> m) {
		return getSqlSession().selectOne("bookSql.in_warehouseStocks", m);
	}
	//완전 그 해당 창고에 isbn의 재고확인
	public int in_warehouseStocksFirst(HashMap<String, Object> m) {
		return getSqlSession().selectOne("bookSql.in_warehouseStocksFirst", m);
	}
	
	//입고완료시 stocks 첫 insert
	public int in_warehouseStocksInFirst(HashMap<String, Object> m) {
		System.out.println("in_warehouseStocksInFirst");
		int num =  getSqlSession().insert("bookSql.in_warehouseStocksInFirst", m);
		System.out.println("storckFnum:" +num);
		return num;
	}
	//입고완료시 stocks insert
	public int in_warehouseStocksIn(HashMap<String, Object> m) {
		return getSqlSession().insert("bookSql.in_warehouseStocksIn", m);
	}
	
	//입고완료시 stocks update
	public int in_warehouseStocksUp(HashMap<String, Object> m) {
		return getSqlSession().update("bookSql.in_warehouseStocksUp", m);
	}
	
	//-----------------------출고
	
	//출고 리스트 
	public List<v_RequestHistory> out_warehouseList(String date){
		System.out.println("출고리스트: "+date);
		List<v_RequestHistory> list = getSqlSession().selectList("bookSql.out_warehouseList", date);
		return list;
	}
	//출고 날짜검색 리스트 
	public List<v_RequestHistory> out_warehouseDateList(HashMap<String, String> map){
		List<v_RequestHistory> list = getSqlSession().selectList("bookSql.out_warehouseDateList", map);
		return list;
	}
	//출고신청 추가
	public int out_warehouseInsert(HashMap<String, Object> m) {
		return getSqlSession().insert("bookSql.out_warehouseInsert", m);
	}
	//출고신청삭제
	public void out_warehouseDelete(HashMap<String, Object> m) {
		getSqlSession().delete("bookSql.out_warehouseDelete", m);
	}
	//출고신청 완료 UPDATE 
	public int out_warehouseUpdate(HashMap<String, Object> m) {
		return getSqlSession().update("bookSql.out_warehouseUpdate", m);
	}
	//출고완료시 stocks update
	public int out_warehouseStocksUp(HashMap<String, Object> m) {
		return getSqlSession().update("bookSql.out_warehouseStocksUp", m);
	}
	//출고완료시 stocks insert
	public int out_warehouseStocksIn(HashMap<String, Object> m) {
		return getSqlSession().insert("bookSql.out_warehouseStocksIn", m);
	}
	
	//stock에 해당 출고일 재고가 존재할시 
	public int out_stockOk(HashMap<String, Object> m) {
		return getSqlSession().selectOne("bookSql.out_stockOk", m);
	}
	//stock에 해당 출고일 재고가 존재 안할시 
	public int out_stockNo(HashMap<String, Object> m) {
		return getSqlSession().selectOne("bookSql.out_stockNo", m);
	}
	//출고일까지의 입고 예정수량
	public int out_stockInNum(HashMap<String, Object> m) {
		return getSqlSession().selectOne("bookSql.out_stockInNum", m);
	}
	//출고일까지의 출고 예정 수량
	public int out_stockOutNum(HashMap<String, Object> m) {
		return getSqlSession().selectOne("bookSql.out_stockOutNum", m);
	}
		
}
