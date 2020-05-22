package book.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserDao extends SqlSessionDaoSupport{

	public UserDto loginCheck(HashMap<String, String> m) {
		UserDto userDto = getSqlSession().selectOne("bookSql.loginCheck", m);
		return userDto;
	}
	
	//창고리스트 
	public List<Warehouse> warehouseList() {
		return getSqlSession().selectList("bookSql.warehouseList");
	}
	//창고 insert
	public int warehouseInsert(HashMap<String, Object> m) {
		return getSqlSession().insert("bookSql.warehouseInsert", m);
	}
	//창고 update
	public int warehouseUpdate(HashMap<String, Object> m) {
		return getSqlSession().update("bookSql.warehouseUpdate", m);
	}
	//창고 delete
	public void warehouseDelete(int warehouse_id) {
		getSqlSession().delete("bookSql.warehouseDelete", warehouse_id);
	}
}
