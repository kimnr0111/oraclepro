package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneDao {
	
	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";
	
	//생성자
	public PhoneDao() {}
	
	//일반메소드
	private void getConnect() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch(ClassNotFoundException e) {
			System.out.println("error:드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	private void closeAll() {
		// 5. 자원정리
	    try {
	    	if (rs != null) {
	    		rs.close();
	    	}
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    }
	}
	
	//번호추가
	public void phoneInsert(PhoneVo phoneVo) {
		
		try {
			//db연결
			getConnect();
			
		    // 3. SQL문 준비 / 바인딩 / 실행
			String quary = "INSERT INTO person VALUES (seq_person_id.nextval, ?, ?, ? )";
			pstmt = conn.prepareStatement(quary);
			
			pstmt.setString(1, phoneVo.getName());
			pstmt.setString(2, phoneVo.getHp());
			pstmt.setString(3, phoneVo.getCompany());
			
			int count = pstmt.executeUpdate();		//insert, update, delete
		    
		    // 4.결과처리
			System.out.println(count + "건 처리되었습니다.");

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		//자원정리
		closeAll();
		
	}
	
	//번호수정
	public void phoneUpdate(PhoneVo phoneVo) {
		
		try {
			//db연결
			getConnect();
			
		    // 3. SQL문 준비 / 바인딩 / 실행
			String quary = "";
			quary +="update person ";
			quary +="set name = ?, ";
			quary +="hp = ?, ";
			quary +="company = ? ";
			quary +="where person_id = ? ";
			pstmt = conn.prepareStatement(quary);
			
			pstmt.setString(1, phoneVo.getName());
			pstmt.setString(2, phoneVo.getHp());
			pstmt.setString(3, phoneVo.getCompany());
			pstmt.setInt(4, phoneVo.getPersonId());
			
			int count = pstmt.executeUpdate();		//insert, update, delete
		    
		    // 4.결과처리
			System.out.println(count + "건 처리되었습니다.");

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		//자원정리
		closeAll();
		
	}
	
	//번호제거
	public void phoneDelete(PhoneVo phoneVo) {
		
		try {
			//db연결
			getConnect();
			
		    // 3. SQL문 준비 / 바인딩 / 실행
			String quary = "";
			quary += "delete from person ";
			quary += "where person_id = ? ";
			pstmt = conn.prepareStatement(quary);
			
			pstmt.setInt(1, phoneVo.getPersonId());
			
			int count = pstmt.executeUpdate();		//insert, update, delete
		    
		    // 4.결과처리
			System.out.println(count + "건 처리되었습니다.");

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		//자원정리
		closeAll();
		
	}
	
	//출력
	public List<PhoneVo> getPhoneList() {
		List<PhoneVo> phoneList = new ArrayList<PhoneVo>();
		try {
			//db연결
			getConnect();
			
		    // 3. SQL문 준비 / 바인딩 / 실행
			String quary = "";
			quary += "select * ";
			quary += "from person ";
			pstmt = conn.prepareStatement(quary);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PhoneVo phoneVo = new PhoneVo(personId, name, hp, company);
				phoneList.add(phoneVo);
			}

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		//자원정리
		closeAll();
		
		return phoneList;
		
	}
	
	//검색
	public List<PhoneVo> searchPhoneList() {
		List<PhoneVo> phoneList = new ArrayList<PhoneVo>();
		try {
			//db연결
			getConnect();
			
		    // 3. SQL문 준비 / 바인딩 / 실행
			String quary = "";
			quary += "select * ";
			quary += "from person ";
			pstmt = conn.prepareStatement(quary);
			
			rs = pstmt.executeQuery();
			
			Scanner sc = new Scanner(System.in);
			System.out.print(">검색어: ");
			String str = sc.nextLine();
			System.out.println(str);
			
			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				if(name.contains(str) || hp.contains(str) || company.contains(str)) {
					PhoneVo phoneVo = new PhoneVo(personId, name, hp, company);
					phoneList.add(phoneVo);
				}
			} 
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		//자원정리
		
		closeAll();
		
		return phoneList;
		
	}

}
