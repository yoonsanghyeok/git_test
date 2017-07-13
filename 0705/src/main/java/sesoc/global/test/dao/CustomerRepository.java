package sesoc.global.test.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sesoc.global.test.vo.Customer;

@Repository
public class CustomerRepository {

	@Autowired
	SqlSession sqlSession;
	
	public int insertCustomer(Customer customer){
		int result = 0;
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		result = dao.insert(customer);
		return result;
	}
	
	public int updateCustomer(Customer customer){
		int result = 0;
		
		return result;
	}
	
	public int deleteCustomer(String custid){
		int result = 0;
		
		return result;
	}
	
	public Customer findCustomer(String custid){
		Customer found = null;
		
		return found;
	}
	
	public List<Customer> findAll(){
		List<Customer> list = new ArrayList<>();
		
		return list;
	}

	public Customer findCustomer(String custid, String password) {
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		Map<String, String> map = new HashMap<>();
		map.put("custid", custid);
		map.put("password", password);
		return dao.selectOne(map);
	}
}
