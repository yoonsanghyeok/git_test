package sesoc.global.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sesoc.global.test.service.NoticeService;
import sesoc.global.test.util.FileService;
import sesoc.global.test.util.PageNavigator;
import sesoc.global.test.vo.Notice;

@Controller
public class NoticeController {
	
	@Autowired
	NoticeService service;
	
	private String uploadPath = "c:/noticeFiles";
	
	@RequestMapping("noticeList")
	public String noticeList(@RequestParam(value="currentPage", defaultValue="1")int currentPage,
							@RequestParam(value="searchtype", defaultValue="title")String searchtype,
							@RequestParam(value="searchword", defaultValue="")String searchword,
			Model model){
		Map<String, String> search = new HashMap<>();
		search.put("searchtype", searchtype);
		search.put("searchword", searchword);
		int totalRecordCount = service.getNoticeCount(search);
		PageNavigator navi = new PageNavigator(currentPage, totalRecordCount);
		List<Notice> noticeList = service.selectAll(search, navi.getStartRecord(), navi.getCountPerPage());
		model.addAttribute("navi", navi);
		model.addAttribute("noticeList", noticeList);
		return "notice/noticeList";
	}
	
	@RequestMapping("noticeDetail")
	public String noticeDetail(int noticenum, Model model){
		Notice notice = service.selectOne(noticenum);
		service.increaseHits(noticenum);
		model.addAttribute("notice", notice);
		return "notice/noticeDetail";
	}
	@RequestMapping("noticeWrite")
	public String insertNotice(){
		return "notice/noticeWrite";
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public String noticeWrite(Notice notice, MultipartFile file){
		if(file.isEmpty() == false){
			String originalFilename = file.getOriginalFilename();
			String savedFilename = FileService.uploadFile(file, uploadPath);

			notice.setOriginalfile(originalFilename);
			notice.setSavedfile(savedFilename);
		}
		service.insertNotice(notice);
		return "redirect:noticeList";
	}
	
	@RequestMapping("noticeUpdate")
	public String noticeUpdate(int noticenum, Model model){
		Notice target = service.selectOne(noticenum);
		model.addAttribute("notice", target);
		return "notice/noticeUpdate";
	}
	
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public String noticeUpdate(Notice notice, RedirectAttributes rttr, MultipartFile file){
		if(file.isEmpty()==false){
			notice.setOriginalfile(file.getOriginalFilename());
			notice.setSavedfile(file.getOriginalFilename()+"_"+UUID.randomUUID().toString());
			FileService.uploadFile(file, uploadPath);
			FileService.deleteFile(uploadPath+"/"+service.selectOne(notice.getNoticenum()).getSavedfile());
		}
		service.updateNotice(notice);
		rttr.addAttribute("noticenum", notice.getNoticenum());
		return "redirect:noticeDetail";
	}
	
	@RequestMapping("noticeDelete")
	public String noticeDelete(int noticenum, Model model){
		Notice notice = service.selectOne(noticenum);
		FileService.deleteFile(uploadPath+"/"+notice.getSavedfile());
		service.deleteNotice(noticenum);
		return "redirect:noticeList";
	}
}
