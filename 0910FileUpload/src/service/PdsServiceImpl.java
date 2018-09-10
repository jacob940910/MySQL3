package service;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PdsDao;
import dao.PdsDaoImpl;
import vo.PdsVO;

public class PdsServiceImpl implements PdsService {
	//Dao의 참조형 변수를 선언 
	private PdsDao pdsDao;
	
	private PdsServiceImpl() {
		pdsDao = PdsDaoImpl.sharedInstance();
	}
	private static PdsService pdsService;
	
	public static PdsService sharedInstance() {
		if(pdsService == null) {
			pdsService = new PdsServiceImpl();
			
		}
		return pdsService;
	}

	@Override
	public boolean insertPds(HttpServletRequest request) {
		try {
			//파라미터 읽기
			request.setCharacterEncoding("utf-8");
			//작업처리 
			///파일업로드
			//파일을 저장할 디렉토리 경로 생성 
			String uploadPath = request.getServletContext().getRealPath("/pdsupload");
			MultipartRequest multi = new MultipartRequest(request, uploadPath, 1024*1024*10, "utf-8", new DefaultFileRenamePolicy());
			
			//가장큰 code 찾아오기
			int code = pdsDao.maxCode();
			//description 읽기
			String description = multi.getParameter("description");
			//업로드된 파일명 찾아오기 
			Enumeration<String> fileNames = multi.getFileNames();
			String file = fileNames.nextElement();
			String filename = multi.getFilesystemName(file);
			File f = new File(uploadPath + "/" + filename);
			int filesize = (int)f.length();
			
			
			//Dao 파라미터 만들기 
			PdsVO pds = new PdsVO();
			pds.setCode(code + 1);    //+1을 해주어야합니다.
			pds.setFilename(filename);
			pds.setFilesize(filesize);
			pds.setDescription(description);
			System.out.println(pds);
			//Dao 메소드를 호출해서 리턴 
			return pdsDao.insertPds(pds);
			
			
		}catch(Exception e) {
			System.out.println("데이터 삽입 예외:" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
