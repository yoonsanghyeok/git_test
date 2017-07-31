package sesoc.global.library;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import sesoc.global.library.dao.LibraryRepository;
import sesoc.global.library.util.FileService;
import sesoc.global.library.util.PageNavigator;
import sesoc.global.library.vo.Lend;
import sesoc.global.library.vo.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private final String uploadPath = "/userImages";
	//C:/framework/springtest/library/src/main/webapp/resources/userImages
	@Autowired
	LibraryRepository repo;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String rootPage(Locale locale, Model model) {
		return "home";
	}
	
	@RequestMapping("signUpForm")
	public String signUpForm(){
		return "signup";
	}
	
	@RequestMapping(value = "signUp", method = RequestMethod.POST)
	public String signUp(User user, Model model, MultipartFile file){
		
		String message= "회원 가입에 실패했습니다";
		String location = "signUpForm";
		
		user.setNum(UUID.randomUUID().toString());
		try{
			if(file != null && file.isEmpty() == false){
				boolean uploadResult = FileService.uploadFile(file, uploadPath, user.getUserid());
				String thumbnailName = FileService.makeThumbnail(uploadPath, user.getUserid()+"_"+file.getOriginalFilename());
				if(uploadResult == true){
					user.setImage(file.getOriginalFilename());
					user.setThumbnail(thumbnailName);
					int signUpResult = repo.signUp(user);
					if(uploadResult == true && signUpResult == 1){
						message = "회원 가입에 성공했습니다";
						location = "home";
					}
				}
			}
		} catch(Exception e){
			
		}
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		return "message";
	}
	
	@RequestMapping("updateUserForm")
	public String updateUserForm(){
		return "userupdate";
	}
	
	@RequestMapping("updateUser")
	public String updateUser(User user, Model model, MultipartFile file, HttpSession session){
		String message= "정보 수정에 실패했습니다";
		String location = "userupdate";
		
		try{
			if(file != null && file.isEmpty() == false){
				boolean uploadResult = FileService.uploadFile(file, uploadPath, user.getUserid());
				String thumbnailName = FileService.makeThumbnail(uploadPath, user.getUserid()+"_"+file.getOriginalFilename());
				if(uploadResult == true){
					user.setImage(file.getOriginalFilename());
					user.setThumbnail(thumbnailName);
					int updateResult = repo.updateUser(user);
					if(uploadResult == true && updateResult == 1){
						message = "정보 수정에 성공했습니다";
						location = "home";
					}
				}
			}
		} catch(Exception e){
			
		}
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		session.setAttribute("loginUser", user);
		return "message";
	}
	
	@RequestMapping("home")
	public String home(){
		return "home";
	}
	
	@ResponseBody
	@RequestMapping("checkid")
	public User checkid(String userid){
		User result = repo.checkid(userid);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="signin", method=RequestMethod.POST)
	public User signin(User user, HttpSession session){
		User loginResult = repo.signin(user);
		if(loginResult != null){
			session.setAttribute("loginUser", loginResult);
		}
		return loginResult;
	}
	
	@ResponseBody
	@RequestMapping("getbooklist")
	public List<Map<String, Object>> getbooklist(){
		System.out.println("getbooklist method............");
		System.out.println(repo.getbooklist());
		return repo.getbooklist();
	}
	@ResponseBody
	@RequestMapping("showImage")
	public ResponseEntity<byte[]> download(User user, HttpServletResponse response){
		System.out.println(user);
		System.out.println(user.getThumbnail());
		System.out.println("download() enter...");
		String fullPath = uploadPath + user.getThumbnail();
		HttpHeaders headers = new HttpHeaders();
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("bookdetail")
	public Map<String, Object> bookdetail(int num){
		System.out.println("bookdetail method.......");
		System.out.println(num);
		return repo.selectOneBook(num);
	}
	
	//void라도 ajax로 호출된 메소드는 ResponseBody 어노테이션을 꼭 붙여야 한다.
	@ResponseBody
	@RequestMapping("logout")
	public void logout(HttpSession session){
		System.out.println("logout method......");
		session.invalidate();
	}
	
	@ResponseBody
	@RequestMapping("checkReserved")
	public Lend checkReserved(String usernum, int booknum){
		Map<String, Object> reserveInfo = new HashMap<>();
		reserveInfo.put("usernum", usernum);
		reserveInfo.put("booknum", booknum);
		Lend checkResult = repo.checkReserved(reserveInfo);
		return checkResult;
	}
	
	@ResponseBody
	@RequestMapping("reserve")
	public String reserve(String usernum, int booknum){
		Map<String, Object> reserveInfo = new HashMap<>();
		reserveInfo.put("usernum", usernum);
		reserveInfo.put("booknum", booknum);
		int result = repo.reserve(reserveInfo);
		return result+"";
	}
	
	@RequestMapping("toAdminPage")
	public String toAdminPage(){
		return "adminmain";
	}
	
	@ResponseBody
	@RequestMapping("requestlist")
	public List<Map<String, Object>> requestlist(){
		return repo.requestlist();
	}
	
	@ResponseBody
	@RequestMapping("lentlist")
	public List<Map<String,Object>> lentlist(){
		repo.checkdelay();
		return repo.lentlist();
	}
	
	@ResponseBody
	@RequestMapping("delaylist")
	public List<Map<String,Object>> delaylist(){
		repo.checkdelay();
		return repo.delaylist();
	}
	
	@ResponseBody
	@RequestMapping("lendhistory")
	public List<Map<String, Object>> lendhistory(HttpSession session){
		User loginUser = (User) session.getAttribute("loginUser");
		String usernum = loginUser.getNum();
		System.out.println("lendhistory.......");
		System.out.println(usernum);
		return repo.lendhistory(usernum);
	}
	
	@ResponseBody
	@RequestMapping("grant")
	public String grant(int lendnum, int days){
		Map<String, Object> json = new HashMap<>();
		json.put("lendnum", lendnum);
		json.put("days", days);
//		System.out.println(result);
		return repo.grant(json)+"";
	}
	
	@ResponseBody
	@RequestMapping("reject")
	public String reject(int lendnum){
		int result = repo.reject(lendnum);
		System.out.println(result);
		return result+"";
	}
	
	@ResponseBody
	@RequestMapping("return")
	public String returnprocess(int lendnum){
		return repo.returnprocess(lendnum)+"";
	}
	
	@ResponseBody
	@RequestMapping("checkdelay")
	public void checkDelay(){
		repo.checkdelay();
	}
	
	@ResponseBody
	@RequestMapping("delayedReturn")
	public String delayedReturn(int lendnum){
		return repo.delayedReturn(lendnum);
	}
	
	@ResponseBody
	@RequestMapping("searchBook")
	public List<Map<String, Object>> boardList(@RequestParam(value = "currentPage", defaultValue="1")int currentPage, 
			@RequestParam(value = "searchtype", defaultValue="title")String searchtype,
			@RequestParam(value = "searchword", defaultValue="")String searchword,
			Model model) {
		int totalRecordCount = repo.getBookCount(searchtype, searchword);
		PageNavigator navi = new PageNavigator(currentPage, totalRecordCount);
		
		List<Map<String, Object>> bookList = repo.findAll(searchtype, searchword, navi.getStartRecord(), navi.getCountPerPage());
		return bookList;
	}
}
