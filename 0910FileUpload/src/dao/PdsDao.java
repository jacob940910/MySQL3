package dao;

import vo.PdsVO;

public interface PdsDao {
	//Pds 테이블에서 가장큰 code응 찾아오는 메소드
	public int maxCode();
	
	//Pds 테이블에 데이터를 삽입하는 메소드 
	public boolean insertPds(PdsVO pds);
	
}
