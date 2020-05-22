package book.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class BookDao extends SqlSessionDaoSupport{

	//등록된 제품리스트 습득
	public List<Book> getBookList(){
		return getSqlSession().selectList("goods.getBookList");
	}
	
	//제품추가
	public int addBook(Book book) {
		return getSqlSession().insert("goods.addBook", book);
	}
	
	//등록된 제품인지 확인
	public int checkBook(String isbn) {
		return getSqlSession().selectOne("goods.checkBook", isbn);
	}
	
	//책제목검색
	public List<Book> searchBook(String title){
		return getSqlSession().selectList("goods.searchBook", title);
	}
	
	//재고가 있는 책만 습득
	public List<Book> getGoodsName(){
		return getSqlSession().selectList("goods.getGoodsName");
	}
	
	//책제목습득
	public String getTitle(String isbn) {
		return getSqlSession().selectOne("goods.getTitle", isbn);
	}
	
	public void deleteBook(String isbn) {
		getSqlSession().delete("goods.deleteBook", isbn);
	}
	
}
