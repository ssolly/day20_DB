package day20_DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBClass { 	//db관련 
	
	private String url;
	private String id;
	private String pwd;
	private Connection con;
	
	public DBClass() {	//생성자
		
		try {
			//오라클의 기능을 자바에서 사용하기 위한 메소드
			//객체가 생성되면 무조건 처음 실행시켜준다 (생성자에 생성하는 이유)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			//오라클developer 아이디와 비밀번호
			id = "idid";
			pwd = "idid";
			con = DriverManager.getConnection(url,id,pwd);
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
