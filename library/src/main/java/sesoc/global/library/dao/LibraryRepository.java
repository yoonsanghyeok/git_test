package sesoc.global.library.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sesoc.global.library.vo.Lend;
import sesoc.global.library.vo.User;

@Repository
public class LibraryRepository {

	@Autowired
	SqlSession sqlSession;
	
	public int signUp(User user) {
		return sqlSession.getMapper(LibraryDAO.class).signUp(user);
	}

	public User checkid(String userid) {
		return sqlSession.getMapper(LibraryDAO.class).checkid(userid);
	}

	public User signin(User user) {
		return sqlSession.getMapper(LibraryDAO.class).signin(user);
	}

	public List<Map<String, Object>> getbooklist() {
		return sqlSession.getMapper(LibraryDAO.class).getbooklist();
	}

	public Map<String, Object> selectOneBook(int num) {
		return sqlSession.getMapper(LibraryDAO.class).selectOneBook(num);
	}

	public int reserve(Map<String, Object> reserveInfo) {
		return sqlSession.getMapper(LibraryDAO.class).reserve(reserveInfo);
	}

	public Lend checkReserved(Map<String, Object> reserveInfo) {
		return sqlSession.getMapper(LibraryDAO.class).checkReserved(reserveInfo);
	}

	public List<Map<String, Object>> requestlist() {
		return sqlSession.getMapper(LibraryDAO.class).requestlist();
	}

	public int grant(Map<String, Object> json) {
		return sqlSession.getMapper(LibraryDAO.class).grant(json);
	}

	public List<Map<String, Object>> lentlist() {
		return sqlSession.getMapper(LibraryDAO.class).lentlist();
	}

	public int returnprocess(int lendnum) {
		return sqlSession.getMapper(LibraryDAO.class).returnprocess(lendnum);
	}

	public int reject(int lendnum) {
		return sqlSession.getMapper(LibraryDAO.class).reject(lendnum);
	}

	public void checkdelay() {
		sqlSession.getMapper(LibraryDAO.class).checkdelay();
	}

	public List<Map<String, Object>> delaylist() {
		return sqlSession.getMapper(LibraryDAO.class).delaylist();
	}

	public String delayedReturn(int lendnum) {
		return sqlSession.getMapper(LibraryDAO.class).delayedReturn(lendnum);
	}

	public List<Map<String, Object>> lendhistory(String usernum) {
		return sqlSession.getMapper(LibraryDAO.class).lendhistory(usernum);
	}

	public int updateUser(User user) {
		return sqlSession.getMapper(LibraryDAO.class).updateUser(user);
	}

	public int getBookCount(String searchtype, String searchword) {
		Map<String, String> search = new HashMap<>();
		search.put("searchtype", searchtype);
		search.put("searchword", searchword);
		return sqlSession.getMapper(LibraryDAO.class).getBookCount(search);
	}

	public List<Map<String, Object>> findAll(String searchtype, String searchword, int startRecord, int countPerPage) {
		List<Map<String, Object>> bookList;
		RowBounds rb = new RowBounds(startRecord, countPerPage);

		Map<String, String> search = new HashMap<>();
		search.put("searchtype", searchtype);
		search.put("searchword", searchword);
		
		return sqlSession.getMapper(LibraryDAO.class).findAll(search, rb);
	}

}
