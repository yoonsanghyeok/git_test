package sesoc.global.test.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);
	@ExceptionHandler(Exception.class)//핸들하고자 하는 예외의 종류를 매개변수로 사용하면 된다. 이 경우에는 모든 종류의 Exception을 처리하도록 되어 있다
	public String errorHandler(Model model, Exception e){
		System.out.println("errorHandler()...");
		model.addAttribute("errormsg", e.getMessage());
		logger.debug("Error ===> ", e.getMessage());
		e.printStackTrace();
		return "error";
	}
}
