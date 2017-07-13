package sesoc.global.test;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "index";
	}
	
	/*@RequestMapping(value = "/input", method=RequestMethod.POST)
	public String input(@ModelAttribute("friend") Friend friend){
		logger.info("데이터 수집");
		logger.info(friend.toString());
//		model.addAttribute(friend);
		return "home";
	}*/
	
	@RequestMapping(value = "/input", method=RequestMethod.POST)
	public @ResponseBody Friend input(Friend friend){
		logger.info("데이터 수집");
//		logger.info(friend.toString());
//		model.addAttribute(friend);
		return friend;
	}
	
	/*@RequestMapping(value="/input", method=RequestMethod.POST)
	public String doA(RedirectAttributes rttr, @ModelAttribute Friend friend){
		logger.info("doA.......");
		logger.info(""+friend);
		rttr.addFlashAttribute(friend);
		rttr.addFlashAttribute("msg", "kkkkk");
		return "redirect:/doB";
	}*/
	
	@RequestMapping("/doB")
	public String doB(@ModelAttribute Friend friend, @ModelAttribute String msg){
		logger.info("doB.......");
		logger.info(""+friend);
		return "home";
	}
}
