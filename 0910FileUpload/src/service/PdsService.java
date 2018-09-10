package service;

import javax.servlet.http.HttpServletRequest;

public interface PdsService {
	//데이터 삽입을 처리하기 위한 메소드
	public boolean insertPds(HttpServletRequest request);
	
}
