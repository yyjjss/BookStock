package book.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class CategoryDao extends SqlSessionDaoSupport{

	public List<Category> getCategoryList(){
		return getSqlSession().selectList("goods.getCategory");
	}
	
}
