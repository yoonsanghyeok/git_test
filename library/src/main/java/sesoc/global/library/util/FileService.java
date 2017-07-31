package sesoc.global.library.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileService {

	public static boolean uploadFile(MultipartFile file, String uploadPath, String userid) {
		File path = new File(uploadPath);
		if(path.isDirectory()==false) path.mkdirs();
		File target = new File(uploadPath, userid+"_"+file.getOriginalFilename());
		try {
			FileCopyUtils.copy(file.getBytes(), target);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	    
	
	public static String makeThumbnail(String uploadPath, String fileName){
		String result = "";
		try {
			BufferedImage sourceImg = ImageIO.read(new File(uploadPath, fileName));
			BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
			String thumbnailName = uploadPath + File.separator + "s_" + fileName;
			File newFile = new File(thumbnailName);
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			ImageIO.write(destImg, formatName.toUpperCase(), newFile);
			result = thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
