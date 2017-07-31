package sesoc.global.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sesoc.global.test.dao.BoardRepository;
import sesoc.global.test.service.BoardService;
import sesoc.global.test.util.FileService;
import sesoc.global.test.util.PageNavigator;
import sesoc.global.test.vo.Board;

@Controller
public class BoardController {

	private String uploadPath = "/boardfile"; //파일이 저장되는 하드디스크 경로
	
	@Autowired
	BoardRepository repo;
	
	@Autowired
	BoardService service;
	
	PlatformTransactionManager transactionManager;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	/**
	 * 글 목록 요청
	 * */
	@RequestMapping(value="boardList", method=RequestMethod.GET)
	public String boardList(@RequestParam(value = "currentPage", defaultValue="1")int currentPage, 
							@RequestParam(value = "searchtype", defaultValue="title")String searchtype,
							@RequestParam(value = "searchword", defaultValue="")String searchword,
							Model model)
	{
		logger.info(currentPage + searchtype + searchword);
		int totalRecordCount = repo.getBoardCount(searchtype, searchword);
		PageNavigator navi = new PageNavigator(currentPage, totalRecordCount);
		
		List<Board> boardList = repo.findAll(searchtype, searchword, navi.getStartRecord(), navi.getCountPerPage());
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("boardList", boardList);
		model.addAttribute("searchtype", searchtype);
		model.addAttribute("searchword", searchword);
		model.addAttribute("navi", navi);
//		model.addAttribute("currentPage", currentPage);
//		model.addAttribute("totalPage", navi.getTotalPageCount());
		return "board/boardList";
	}
	
	@RequestMapping(value="boardDetail", method=RequestMethod.GET)
	public String boardDetail(@RequestParam(value = "currentPage", defaultValue="1")int currentPage, 
			@RequestParam(value = "searchtype", defaultValue="title")String searchtype,
			@RequestParam(value = "searchword", defaultValue="")String searchword,
			int boardnum, Model model){
		repo.increaseHits(boardnum);
		Board selectedBoard = repo.selectOne(boardnum);
		String savedFilename = selectedBoard.getSavedfile();
		if(savedFilename != null){
			File f = new File(uploadPath+"/"+savedFilename);
			System.out.println(f.isFile());
			System.out.println(f.exists());
			String mimetype = new MimetypesFileTypeMap().getContentType(uploadPath+"/"+savedFilename);
			System.out.println(uploadPath+"/"+savedFilename);
			System.out.println(mimetype);
			if(mimetype.contains("image"))	model.addAttribute("mimetype", mimetype);
		}
		model.addAttribute("selectedBoard", selectedBoard);	
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchtype", searchtype);
		model.addAttribute("searchword", searchword);
		return "board/boardDetail";
	}

	@RequestMapping(value = "boardWrite", method = RequestMethod.GET)
	public String boardWrite(){
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "boardWrite", method = RequestMethod.POST)
	public String boardWrite(Board board, HttpSession session, MultipartFile upload){	//jsp에서 file에 부여한 name값과 같은 이름으로 받아야한다(upload)
		String custid = (String) session.getAttribute("loginId");
//		System.out.println("file : " + upload);
//		System.out.println("fileName : " + upload.getOriginalFilename());
//		System.out.println("fileSize : " + upload.getSize());
//		System.out.println("isEmpty : " + upload.isEmpty());
//		System.out.println("contentType : " + upload.getContentType());
		board.setCustid(custid);
		
		if(upload.getSize() > 0 && upload.isEmpty()==false) {//업로드된 파일이 있는 경우
			String savedFilename = FileService.uploadFile(upload, uploadPath);
			board.setOriginalfile(upload.getOriginalFilename());			
			board.setSavedfile(savedFilename);
		}//if file exists
//		else {
//			board.setOriginalfile("null");
//			board.setSavedfile("null");
//		}
		
		
		repo.insertBoard(board);
		return "redirect:boardList";
	}
	
	
	/*
	private String uploadFile(String originalFilename, byte[] bytes) {
		UUID uid = UUID.randomUUID();
		String savedfile = uid.toString() + "_" + originalFilename;
		File target = new File(uploadPath, savedfile);
		if(target.isDirectory() == false) target.mkdirs();
		try {
			FileCopyUtils.copy(bytes, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return savedfile;
	}*/

	@RequestMapping("/boardUpdateForm")
	public String boardUpdateForm(@RequestParam(value = "currentPage", defaultValue="1")int currentPage, 
			@RequestParam(value = "searchtype", defaultValue="title")String searchtype,
			@RequestParam(value = "searchword", defaultValue="")String searchword,
			int boardnum, Model model){
		Board board = repo.selectOne(boardnum);
		model.addAttribute("board", board);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchtype", searchtype);
		model.addAttribute("searchword", searchword);
		return "board/boardUpdate";
	}
	
	@RequestMapping(value = "boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(@RequestParam(value = "currentPage", defaultValue="1")int currentPage, 
			@RequestParam(value = "searchtype", defaultValue="title")String searchtype,
			@RequestParam(value = "searchword", defaultValue="")String searchword,
			Board newBoard, RedirectAttributes rttr, MultipartFile upload){
		System.out.println("upload : " + upload);
		if(upload.isEmpty()==false){
			System.out.println("새로 업로드된 파일 있음");
			//기존파일 삭제
			repo.deleteFile(newBoard.getBoardnum());
			
			//새로운 파일 등록
			String savedFilename = FileService.uploadFile(upload, uploadPath);
			newBoard.setOriginalfile(upload.getOriginalFilename());			
			newBoard.setSavedfile(savedFilename);
		}
		repo.updateBoard(newBoard);
		rttr.addAttribute("boardnum", newBoard.getBoardnum());
		rttr.addAttribute("currentPage", currentPage);
		rttr.addAttribute("searchtype", searchtype);
		rttr.addAttribute("searchword", searchword);
		return "redirect:boardDetail";
	}
	
	@RequestMapping("boardDelete")
	public String boardDelete(@RequestParam(value = "currentPage", defaultValue="1")int currentPage, 
			@RequestParam(value = "searchtype", defaultValue="title")String searchtype,
			@RequestParam(value = "searchword", defaultValue="")String searchword,
			int boardnum,
			RedirectAttributes rttr){
		repo.deleteBoard(boardnum);
		repo.deleteFile(boardnum);
		rttr.addAttribute("currentPage", currentPage);
		rttr.addAttribute("searchtype", searchtype);
		rttr.addAttribute("searchword", searchword);
		return "redirect:boardList";
	}
	
	@RequestMapping("download")
	public String download(int boardnum, HttpServletResponse response){
		System.out.println("download() enter...");
		Board board = repo.selectOne(boardnum);
		String originalFilename = board.getOriginalfile();
		String savedFilename = board.getSavedfile();
		//이미지 등과 같이 브라우저에서 직접 열리는 파일
		//열리지 않고 다운받도록 하려면 Content-Disposition을 이용
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(originalFilename, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String fullPath = uploadPath + "/" + savedFilename;
		FileInputStream fi = null;			//파일을 서버의 hdd에서 서버로 가져오는데 쓸 스트림.
		ServletOutputStream so = null;		//서버 -> 사용자로 전송. 
		
		try{
			fi = new FileInputStream(fullPath);
			so = response.getOutputStream();
			FileCopyUtils.copy(fi, so);
			fi.close();
			so.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("download() returning...");
		return null;
	}
	
	@RequestMapping("deleteFile")
	public String deleteFile(@RequestParam(value = "currentPage", defaultValue="1")int currentPage, 
			@RequestParam(value = "searchtype", defaultValue="title")String searchtype,
			@RequestParam(value = "searchword", defaultValue="")String searchword,
			int boardnum, RedirectAttributes rttr){
		Board board = repo.selectOne(boardnum);
		repo.deleteFile(boardnum);
		new File(uploadPath+"/"+board.getSavedfile()).delete();
		rttr.addAttribute("boardnum", boardnum);
		rttr.addAttribute("currentPage", currentPage);
		rttr.addAttribute("searchtype", searchtype);
		rttr.addAttribute("searchword", searchword);
		return "redirect:boardUpdateForm";
	}
}
