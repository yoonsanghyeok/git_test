package sesoc.global.library.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import sesoc.global.library.vo.Lend;
import sesoc.global.library.vo.User;

public interface LibraryDAO {

	int signUp(User user);

	User checkid(String userid);

	User signin(User user);

	List<Map<String, Object>> getbooklist();

	Map<String, Object> selectOneBook(int num);

	int reserve(Map<String, Object> reserveInfo);

	Lend checkReserved(Map<String, Object> reserveInfo);

	List<Map<String, Object>> requestlist();

	int grant(Map<String, Object> json);

	List<Map<String, Object>> lentlist();

	int returnprocess(int lendnum);

	int reject(int lendnum);

	void checkdelay();

	List<Map<String, Object>> delaylist();

	String delayedReturn(int lendnum);

	List<Map<String, Object>> lendhistory(String usernum);

	int updateUser(User user);

	int getBookCount(Map<String, String> search);

	List<Map<String, Object>> findAll(Map<String, String> search, RowBounds rb);
}
