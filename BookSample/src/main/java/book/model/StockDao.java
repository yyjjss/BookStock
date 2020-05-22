package book.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class StockDao extends SqlSessionDaoSupport{

	/*
	 * //오늘 전제품의 재고습득 public List<TodayStock> getAllTodayStock(){ return
	 * getSqlSession().selectList("stocks.getAllTodayStocks"); }
	 * 
	 * //과거 전제품의 재고습득 public List<MonthlyHistory> getAllStockHistory(String month){
	 * return getSqlSession().selectList("stocks.getAllStockHistory"); }
	 * 
	 * public List<MonthlyHistory> getStockHistory(Map<String, String> map){ return
	 * getSqlSession().selectList("stocks.getStockHistory", map); }
	 */
	
	//전체제품 재고
	public List<Stock> getAllGoodsStock(Map<String, String> map){
		return getSqlSession().selectList("stocks.getAllGoodsStock", map);
	}
	
	//특정제품 재고
	public Stock getGoodsStock(String isbn, String date){
		Map<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		map.put("isbn", isbn);
		return getSqlSession().selectOne("stocks.getGoodsStock", map);
	}
	
	//전체제품 재고추이
	public List<RequestHistoryProgress> getAllGoodsProgress(Map<String, String> map){
		return getSqlSession().selectList("stocks.getAllGoodsProgress", map);
	}
	
	//특정제품 재고추이
	public RequestHistoryProgress getGoodsProgress(String isbn, String due_date){
		Map<String, String> map = new HashMap<String, String>();
		map.put("due_date", due_date);
		map.put("isbn", isbn);
		return getSqlSession().selectOne("stocks.getGoodsProgress", map);
	}
	
	//제품재고존재하는지확인
	public int checkStockCount(String isbn, String date) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		map.put("isbn", isbn);
		return getSqlSession().selectOne("stocks.checkStockCount",map);
	}
	
	//날짜기준 가장 최근의 재고합
	public int getRecentStock(String isbn, String date) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		map.put("isbn", isbn);
		return  getSqlSession().selectOne("stocks.getRecentStock", map); 
	}
	
	//제품의 입출고신청이 존재하는지 확인
	public int checkProgressCount(String isbn, String due_date) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("due_date", due_date);
		map.put("isbn", isbn);
		return getSqlSession().selectOne("stocks.checkProgressCount", map);
	}
	
	//창고의 특정날짜의 모든제품재고의 합
	public Stock getAllWarehouseStock(String date, int warehouse_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("warehouse_id", warehouse_id);
		return getSqlSession().selectOne("stocks.getAllWarehouseStock", map);
	}
	
	//특정창고의 전체제품재고가 존재하는지 확인
	public int checkAllWarehouseStock(String date, int warehouse_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("warehouse_id", warehouse_id);
		return getSqlSession().selectOne("stocks.checkAllWarehouseStock", map);
	}
	
	//특정창고의 특정제품재고가 존재하는지 확인
	public int checkWarehouseGoodsStock(String isbn, String date, int warehouse_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("warehouse_id", warehouse_id);
		map.put("isbn", isbn);
		return getSqlSession().selectOne("stocks.checkWarehouseGoodsStock", map);
	}
	
	//날짜기준 가장 최근의 특정제품의 창고재고합
	public int getRecentWarehouseGoodsStock(String isbn, String date, int warehouse_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("warehouse_id", warehouse_id);
		map.put("isbn", isbn);
		return getSqlSession().selectOne("stocks.getRecentWarehouseGoodsStock", map);
	}
	
	//날짜기준 가장 최근의 전체제품의 창고재고합
	public int getRecentWarehouseAllStock(String date, int warehouse_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("warehouse_id", warehouse_id);
		return getSqlSession().selectOne("stocks.getRecentWarehouseAllStock", map);
	}
	
	//창고id로 창고명 습득
	public String getWarehouseName(int warehouse_id) {
		return getSqlSession().selectOne("stocks.getWarehouseName", warehouse_id);
	}
	
	//특정창고의 전체재품의 합
	public RequestHistoryProgress getAllWarehouseProgress(String due_date, int warehouse_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("due_date", due_date);
		map.put("warehouse_id", warehouse_id);
		return getSqlSession().selectOne("stocks.getAllWarehouseProgress", map);
	}
	
	//특정창고의 전체재품의 합
	public int checkAllWarehouseProgress(String due_date, int warehouse_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("due_date", due_date);
		map.put("warehouse_id", warehouse_id);
		return getSqlSession().selectOne("stocks.checkAllWarehouseProgress", map);
	}
	
	
	//특정창고의 특정날짜의 특정제품의 재고습득
	public Stock getWarehouseStock(String isbn, String date, int warehouse_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("warehouse_id", warehouse_id);
		map.put("isbn", isbn);
		return getSqlSession().selectOne("stocks.getWarehouseStock", map);
	}
	
	//특정창고의특정날짜의 특정제품의 입출고예정이 존재하는지 확인
	public int checkWarehouseProgress(String isbn, String date, int warehouse_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("warehouse_id", warehouse_id);
		map.put("isbn", isbn);
		return getSqlSession().selectOne("stocks.checkWarehouseProgress", map);
	}
	
	//특정창고의 특정날짜의 특정제품의 입출고예정의 합
	public RequestHistoryProgress getWarehouseProgress(String isbn, String date, int warehouse_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("warehouse_id", warehouse_id);
		map.put("isbn", isbn);
		return getSqlSession().selectOne("stocks.getWarehouseProgress", map);
	}
	
	//삭제할 수 있는 제품인지 확인 -> 현재 재고가 0
	public int checkDeleteBook(String isbn) {
		return getSqlSession().selectOne("stocks.checkDeleteBook", isbn);
	}
	
}
