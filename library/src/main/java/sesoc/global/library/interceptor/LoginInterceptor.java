package sesoc.global.library.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String loginid = (String) session.getAttribute("loginUser");
		System.out.println(loginid);
		if(loginid != null && loginid.equals("admin")){
			System.out.println("관리자 로그인 상태 확인됨");
			return true;
		} else {
			String ctx = request.getContextPath();
			//response.sendRedirect(ctx+"/login");
			String message = "관리자 로그인 상태가 아닙니다. 관리자로 로그인 해주세요.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			//response.sendRedirect("main");
			//System.out.println("rest part of the preHandle() method....");
			return false;
		}
		//return super.preHandle(request, response, handler);
	}
}
