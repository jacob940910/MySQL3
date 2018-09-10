package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.Pds;
import vo.PdsVO;

public class PdsDaoImpl implements PdsDao {
	private PdsDaoImpl() {}
	
	private static PdsDao pdsDao;
	
	public static PdsDao sharedInstance() {
		if(pdsDao == null) {
			pdsDao = new PdsDaoImpl();
		}
		return pdsDao;
	}
	
	//데이터베이스 작업을 위한 변수
	protected Connection con;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	//데이터베이스 접속을 수행해주는 메소드
	protected void connect() {
		try {
			//context.xml파일의 DBConn이라는 
			//리소스 이름을 이용해서 데이터베이스 접속 만들기 
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/DBConn");
			con = ds.getConnection();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	//데이터베이스 자원을 해제하는 메소드
	protected void close() {
		try {
			if(rs != null)rs.close();
			if(pstmt != null)pstmt.close();
			if(con != null)con.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public int maxCode() {
		int code = 0;
		try {
			connect();
			//pds 테이블에서 가장 큰  code 가져오기
			pstmt = con.prepareStatement("select max(code) from pds");
			rs = pstmt.executeQuery();
			
			//데이터 읽기
			if(rs.next()) {
				code = rs.getInt("max(code)");
			}
		}catch(Exception e) {
			System.out.println("가장 큰 code 가져오기 에러:" + e.getMessage());
			e.printStackTrace();
		}finally {
			close();
		}
		
		return code;
	}

	@Override
	public boolean insertPds(PdsVO pds) {
		boolean result = false;
		try {
			connect();
			pstmt = con.prepareStatement("insert into pds(code, filename, filesize, description) values( ?,?,?,?)");
			pstmt.setInt(1, pds.getCode());
			pstmt.setString(2, pds.getFilename());
			pstmt.setInt(3, pds.getFilesize());
			pstmt.setString(4, pds.getDescription());
			int r = pstmt.executeUpdate();
			if(r > 0)
				result = true;
		}catch(Exception e) {
			System.out.println("데이터 저장 에러:" + e.getMessage());
			e.printStackTrace();
		}finally {
			close();
		}
		
		return result;
	}
	
}
