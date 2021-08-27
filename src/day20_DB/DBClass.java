package day20_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBClass { 	//db관련 

	/*
	 1.드라이브 로드(오라클 기능 사용)
	 2.연결된 객체를 얻어온다
	 3.연결된 객체를 이용해서 명령어(쿼리문)을 전송할 수 있는 전송 객체를 얻어온다
	 4.전송 객체를 이용해서 데이터베이스에 전송 후 결과를 얻어온다 
	 5.얻어온 결과는 int 또는 ResultSet으로 받는다
	 */
	
	private String url;
	private String id;
	private String pwd;
	private Connection con;
	
	public DBClass() {	//생성자
	//1,2번	
		try {
			//오라클의 기능을 자바에서 사용하기 위한 메소드
			//객체가 생성되면 무조건 처음 실행시켜준다 (생성자에 생성하는 이유)
			Class.forName("oracle.jdbc.driver.OracleDriver");	//1
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			//오라클developer 아이디와 비밀번호
			id = "idid";
			pwd = "idid";
			con = DriverManager.getConnection(url,id,pwd);		//2, java와DB연결
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<StudentDTO> getUsers() {
	//3번
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		String sql = "select * from newst";
		try {
			PreparedStatement ps = con.prepareStatement(sql);		//3 전송객체 : DB에 명령어를 전달
			ResultSet rs = ps.executeQuery();						//ResultSet에 결과값을 저장
																	//ps.executeQuery();는 명령어 실행
			while(rs.next()) {		// rs.next()를 만나면 다음으로 넘어간다 (eof만나면 끝)
				StudentDTO dto = new StudentDTO();	//while문 밖에 들어가면 마지막에 들어간 data만 출력된다
				dto.setStNum(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return list;
	}
	
	public int saveData(String stNum, String name, int age) {
		//등록시 필요한 쿼리문 : insert into newst values('111','홍길동',20;);
		String sql = "insert into newst values('"+stNum+"','"+name+"',"+age+")";
		
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			//ResultSet rs = ps.executeQuery();
			result = ps.executeUpdate();
			//저장 성공시 1을 반환, 실패시 catch 이동이나 0을 반환
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	public int saveData02(String stNum, String name, int age) {
		//등록시 필요한 쿼리문 : insert into newst values('111','홍길동',20;);
		String sql = "insert into newst values(?,?,?)";		//?는 나중에 채워 넣겠다
		
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, stNum);	// 1번부터 시작!
			ps.setString(2, name);
			ps.setInt(3, age);
			result = ps.executeUpdate();
			//저장 성공시 1을 반환, 실패시 catch 이동이나 0을 반환
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	public int delete(String stNum) {
		int result =0;
		//삭제시 필요한 쿼리문 : delete from newst where id = "stNum";
		String sql = "delete from newst where id = ?";	//where id = '"+stNum+"'"; 동일
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, stNum);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int modify(String stNum, String name, int age) {
		int result = 0;
		//수정시 필요한 쿼리문 : update newst set name='홍길동', age=20 where id='111';
		String sql = "update newst set name=?,age=? where id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, stNum);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return result;
	}
}
