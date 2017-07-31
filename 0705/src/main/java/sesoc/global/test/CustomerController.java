package sesoc.global.test;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sesoc.global.test.dao.CustomerRepository;
import sesoc.global.test.vo.Customer;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerRepository repo;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(){
		return "customer/joinForm";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(Customer customer, Model model){
		int insertResult = repo.insertCustomer(customer);
		String message = insertResult > 0 ? "성공" : "실패";
		model.addAttribute("message", message);
		return "message";
	}
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(){
		return "customer/loginForm";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(String custid, String password, Model model, HttpSession session){
		Customer customer = repo.findCustomer(custid, password);
		System.out.println(customer);
		String message = "";
		if(customer == null){
			message = "로그인 실패";
			model.addAttribute("message", message);
			return "message";
		}
		session.setAttribute("loginId", customer.getCustid());
		session.setAttribute("name", customer.getName());
		return "redirect:/";	//HomeControllerdml '/'로 이동한다
		// return "index";		//같은 결과
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
}
