package book.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import book.model.Book;
import book.model.BookDao;
import book.model.Category;
import book.model.CategoryDao;
import book.model.MonthlyHistory;
import book.model.RequestHistoryProgress;
import book.model.Stock;
import book.model.StockDao;
import book.model.TodayStock;
import book.model.UserDao;
import book.model.Warehouse;
@Service
public class GoodsService {

	@Autowired
	BookDao bookDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	StockDao stockDao;
	
	@Autowired
	UserDao userDao;
	
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Autowired
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	@Autowired
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	//전체제품습득
	public List<Book> getBookList(){
		return bookDao.getBookList();
	}
	
	//카테고리습득 - 사용안함
	public List<Category> getCategoryList(){
		return categoryDao.getCategoryList();
	}
	
	//등록여부확인
	public int checkBook(String isbn) {
		return bookDao.checkBook(isbn);
	}
	
	//제품등록
	public int addBook(Book book) {
		return bookDao.addBook(book);
	}
	
	//책제목검색
	public List<Book> searchBook(String title){
		return bookDao.searchBook(title);
	}
	
	/*
	 * //오늘 전제품 재고습득 public List<TodayStock> getAllTodayStock(){ return
	 * stockDao.getAllTodayStock(); }
	 * 
	 * //과거 전제품 재고습득 public List<MonthlyHistory> getAllStockHistory(String month){
	 * return stockDao.getAllStockHistory(month); }
	 * 
	 * public List<MonthlyHistory> getStockHistory(String month, String isbn){
	 * Map<String, String> map = new HashMap<String, String>();
	 * map.put("monthly_history", month); map.put("isbn", isbn); return
	 * stockDao.getStockHistory(map); }
	 */
	
	//전체제품 재고
	public List<Stock> getAllGoodsStock(Map<String, String> map){
		return stockDao.getAllGoodsStock(map);
	}
	
	//특정제품 재고
	public Stock getGoodsStock(String isbn, String date){
		return stockDao.getGoodsStock(isbn, date);
	}
	
	//전체제품 재고추이
	public List<RequestHistoryProgress> getAllGoodsProgress(Map<String, String> map){
		return stockDao.getAllGoodsProgress(map);
	}
	
	//특정제품 재고추이
	public RequestHistoryProgress getGoodsProgress(String isbn, String due_date){
		return stockDao.getGoodsProgress(isbn, due_date);
	}
	
	//재고가 있는 책습득
	public List<Book> getGoodsName(){
		return bookDao.getGoodsName();
	}
	//제품재고존재하는지확인
	public int checkStockCount(String isbn, String date) {
		return stockDao.checkStockCount(isbn, date);
	}
	
	//날짜기준 가장 최근의 재고합
	public int getRecentStock(String isbn, String date) {
		return stockDao.getRecentStock(isbn, date);
	}
	
	//제품의 입출고신청이 존재하는지 확인
	public int checkProgressCount(String isbn, String due_date) {
		return stockDao.checkProgressCount(isbn, due_date);
	}
	
	
	//책제목습득
	public String getTitle(String isbn) {
		return bookDao.getTitle(isbn);
	}
	
	//창고리스트 
	public List<Warehouse> getWarehouseList(){
		return userDao.warehouseList();
	}
	
	//창고의 특정날짜의 모든제품재고의 합
	public Stock getAllWarehouseStock(String date, int warehouse_id) {
		return stockDao.getAllWarehouseStock(date, warehouse_id);
	}
	
	//특정창고의 전체제품재고가 존재하는지 확인
	public int checkAllWarehouseStock(String date, int warehouse_id) {
		return stockDao.checkAllWarehouseStock(date, warehouse_id);
	}
	
	//특정창고의 특정제품재고가 존재하는지 확인
	public int checkWarehouseGoodsStock(String isbn, String date, int warehouse_id) {
		return stockDao.checkWarehouseGoodsStock(isbn, date, warehouse_id);
	}
	
	//날짜기준 가장 최근의 특정제품의 창고재고합
	public int getRecentWarehouseGoodsStock(String isbn, String date, int warehouse_id) {
		return stockDao.getRecentWarehouseGoodsStock(isbn, date, warehouse_id);
	}
		
	//날짜기준 가장 최근의 전체제품의 창고재고합
	public int getRecentWarehouseAllStock(String date, int warehouse_id) {
		return stockDao.getRecentWarehouseAllStock(date, warehouse_id);
	}
	
	//창고id로 창고명 습득
	public String getWarehouseName(int warehouse_id) {
		return stockDao.getWarehouseName(warehouse_id);
	}
	
	//특정창고의 전체재품의 합
	public RequestHistoryProgress getAllWarehouseProgress(String due_date, int warehouse_id) {
		return stockDao.getAllWarehouseProgress(due_date, warehouse_id);
	}
	
	//특정창고의 전체재품의 합
	public int checkAllWarehouseProgress(String due_date, int warehouse_id) {
		return stockDao.checkAllWarehouseProgress(due_date, warehouse_id);
	}
	
	//특정창고의 특정날짜의 특정제품의 재고습득
	public Stock getWarehouseStock(String isbn, String date, int warehouse_id) {
		return stockDao.getWarehouseStock(isbn, date, warehouse_id);
	}
	
	
	//특정창고의특정날짜의 특정제품의 입출고예정이 존재하는지 확인
	public int checkWarehouseProgress(String isbn, String date, int warehouse_id) {
		return stockDao.checkWarehouseProgress(isbn, date, warehouse_id);
	}
	
	//특정창고의 특정날짜의 특정제품의 입출고예정의 합
	public RequestHistoryProgress getWarehouseProgress(String isbn, String date, int warehouse_id) {
		return stockDao.getWarehouseProgress(isbn, date, warehouse_id);
	}
	
	public int createExcelStock(HttpServletResponse response, List<Stock> stockArr, String month) {
		// 엑셀 출력
        try {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet(month+"재고");
		Row row = null;
		Cell cell = null;
		int rowNo = 0;
		
		CellStyle headStyle = workbook.createCellStyle();
		headStyle.setBorderTop(BorderStyle.THIN);
        headStyle.setBorderBottom(BorderStyle.THIN);
        headStyle.setBorderLeft(BorderStyle.THIN);
        headStyle.setBorderRight(BorderStyle.THIN);

        // 배경색은 노란색입니다.
        headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // 데이터는 가운데 정렬합니다.
        headStyle.setAlignment(HorizontalAlignment.CENTER);

        // 데이터용 경계 스타일 테두리만 지정
        CellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.setBorderTop(BorderStyle.THIN);
        bodyStyle.setBorderBottom(BorderStyle.THIN);
        bodyStyle.setBorderLeft(BorderStyle.THIN);
        bodyStyle.setBorderRight(BorderStyle.THIN);

        // 헤더 생성
        row = sheet.createRow(rowNo++);

        cell = row.createCell(0);
        cell.setCellStyle(headStyle);
        cell.setCellValue("날짜");

        cell = row.createCell(1);
        cell.setCellStyle(headStyle);
        cell.setCellValue("제품명");

        cell = row.createCell(2);
        cell.setCellStyle(headStyle);
        cell.setCellValue("입고수량");
        
        cell = row.createCell(3);
        cell.setCellStyle(headStyle);
        cell.setCellValue("출고수량");
        
        cell = row.createCell(4);
        cell.setCellStyle(headStyle);
        cell.setCellValue("재고수량");
        int totalIncoming = 0;
        int totalRelease = 0;
        int totalStock = 0;

        // 데이터 부분 생성
        for(Stock stock : stockArr) {
            //Map<String, Object> mapValue = (Map<String, Object>) map1;
            
            //logger.info("DB DATA : "+mapValue.toString());
            
            row = sheet.createRow(rowNo++);
            cell = row.createCell(0);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(""+stock.getDumpDate());
            cell = row.createCell(1);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(""+stock.getTitle());
            cell = row.createCell(2);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(""+stock.getIncoming_amount());
            cell = row.createCell(3);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(""+stock.getRelease_amount());
            cell = row.createCell(2);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(""+stock.getStock());
            totalIncoming += stock.getIncoming_amount();
            totalRelease += stock.getRelease_amount();
            totalStock += stock.getStock();
        }
        row = sheet.createRow(rowNo++);
        cell = row.createCell(0);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("");
        cell = row.createCell(1);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("");
        cell = row.createCell(2);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(""+totalIncoming);
        cell = row.createCell(3);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(""+totalRelease);
        cell = row.createCell(2);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(""+totalStock);

        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename="+month+"_재고현황.xls");

        ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		workbook.close();
		out.flush();
		
		return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}
	//삭제하려는 책의 재고가 몇인지 확인
	public int checkDeleteBook(String isbn) {
		return stockDao.checkDeleteBook(isbn);
	}
	
	public void deleteBook(String isbn) {
		bookDao.deleteBook(isbn);
	}
}
