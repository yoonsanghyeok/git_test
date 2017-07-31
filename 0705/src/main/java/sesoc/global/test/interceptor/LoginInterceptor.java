package sesoc.global.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//@ControllerAdvice
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		if(loginId != null){
			return true;
		} else {
			String ctx = request.getContextPath();
			//response.sendRedirect(ctx+"/login");
			response.sendRedirect("login");
			System.out.println("rest part of the preHandle() method....");
			return false;
		}
		//return super.preHandle(request, response, handler);
	}
}
