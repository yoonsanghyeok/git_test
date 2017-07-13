package sesoc.global.test.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileService {
	
	public static String uploadFile(MultipartFile upload, String uploadPath){
		//랜덤숫자 발생방법 1번
		DateFormat dt = new SimpleDateFormat("yyyyMMdd_HHmmssSSSS");
		String date = dt.format(new Date());
		
		//랜덤숫자 발생방법 2번
		String uid = UUID.randomUUID().toString();

		String originalFilename = upload.getOriginalFilename();
		int lastIndex = originalFilename.lastIndexOf(".");
		String filenameWOext = originalFilename.substring(0, lastIndex);
		String ext = (lastIndex > 0) ? originalFilename.substring(lastIndex+1) : "";
		String savedFilename = filenameWOext + "_" + date + "." + ext;
		File path = new File(uploadPath);
		if(path.isDirectory() == false) path.mkdirs();

		File target = new File(uploadPath, savedFilename);
//		File serverFile = new File(uploadPath + "/" + savedfile);
		try {
			FileCopyUtils.copy(upload.getBytes(), target);
//			upload.transferTo(serverFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return savedFilename;
	}

	/**
	 * 
	 * @param pathWithFilename
	 * @return
	 */
	public static boolean deleteFile(String pathWithFilename){
		boolean result = false;
		File target = new File(pathWithFilename);
		if(target.isFile()) result = target.delete();
		return result;
	}
}
