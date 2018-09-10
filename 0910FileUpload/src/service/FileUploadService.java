package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FileUploadService {
	//파일 업로드를 처리할 메소드 
	public Map<String, Object> fileUpload(HttpServletRequest request);
	
}
