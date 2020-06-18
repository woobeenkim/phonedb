package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PhoneDao {
	//필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";
	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	//생성자
	public PhoneDao() {}
	
	private void getConnect(){
		try {
			
			//1. JDBC 드리이버 오라클 로딩
			Class.forName(driver);
			
			//2. Connection 얻어오기
			
			conn = DriverManager.getConnection(url,id,pw);
			System.out.println("접속 성공!");
			}catch(ClassNotFoundException e)
			{
				System.out.println("error.드라이버 로딩 실패 -"+ e);
			}catch(SQLException e)
			{
				System.out.println("error."+ e);
			}
	}
	private void Closer(){
		try {
			if(rs != null)
			{
				rs.close();
			}
			if(pstmt != null)
			{
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException e) {
			System.out.println("error."+ e);
		}
	}
	
	//시작
	public void Start(){
		
		System.out.println("***********************************************");
		System.out.println("*            전화번호 관리 프로그램           *");
		System.out.println("***********************************************");
	}
	
	//리스트
	public List<PersonVo> Select(){
		getConnect();
		List<PersonVo> PL = new ArrayList<PersonVo>();

		
			
		try {
				
			//3. SQL문 준비/바인딩/실행
			String Query = "";
		
			Query += "select person_id,  "; 
			Query += " name, "; 
			Query += " hp,  ";
			Query += " company  ";
			Query += " from person "; 

			pstmt = conn.prepareStatement(Query);
			
			rs = pstmt.executeQuery(); // 쿼리문 실행(정보 oracle로 보내기)
			
			//4.결과처리
			while(rs.next()){
				int person_id = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				//리스트에 추가
				PersonVo pv = new PersonVo(person_id,name,hp,company);
				PL.add(pv);
				
				//System.out.println(bookID + "\t"+title + "\t" + pubs + "\t"+ pub_date+ "\t"+ author_id+"\t"+author_name+"\t"+author_desc);
				
			}
			
		}catch(SQLException e)
		
		{
			System.out.println("error."+ e);
		}
		Closer();
		
		return PL;
		
	}
	//등록
	public void Insert(PersonVo personvo){
		getConnect();
		try {
			//3. SQL문 준비/바인딩/실행
			String Query = "INSERT INTO person VALUES(seq_person_id.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(Query);

			pstmt.setString(1,personvo.getName());
			pstmt.setString(2,personvo.getHp());
			pstmt.setString(3, personvo.getCompany());

			int count = pstmt.executeUpdate();
			//받는 값 가정
			//4.결과처리
			System.out.println(count + "건이 등록되었습니다.");
		
		}catch(SQLException e)
		{
			System.out.println("error."+ e);
		}	
		Closer();
	}
	//수정
	public void Update(PersonVo personvo){
		getConnect();
		try {
			//3. SQL문 준비/바인딩/실행
			String Query = "";
			Query += " update person ";
			Query += " set    name = ?,";
			Query += "     	  hp = ?, ";
			Query += "     	  company = ? ";
			Query += " where  person_id = ? ";
	
			pstmt = conn.prepareStatement(Query);
			
			
			//받는 값 가정
			
			pstmt.setString(1, personvo.getName());
			pstmt.setString(2, personvo.getHp());
			pstmt.setString(3, personvo.getCompany());
			pstmt.setInt(4, personvo.getPersonID());
			int count = pstmt.executeUpdate();
			//4.결과처리
			System.out.println(count + "건이 수정되었습니다.");
			
		}catch(SQLException e)
		{
			System.out.println("error."+ e);
		}
		Closer();
	}
	//삭제
	public void Delete(int person_id){
		getConnect();
		try {
			
			//3. SQL문 준비/바인딩/실행
			String Query = "";
			Query += " delete from person ";
			Query += " where person_id = ? ";

			
			pstmt = conn.prepareStatement(Query);
			
			
			pstmt.setInt(1, person_id);  //?에 대한 값 입력
			
			int count = pstmt.executeUpdate(); // 쿼리문 실행(정보 oracle로 보내기)
			//4.결과처리
			System.out.println(count + "건 이 삭제되었습니다.");
			
		}catch(SQLException e)
		{
			System.out.println("error."+ e);
		}
		Closer();
	}
	//검색
	public List<PersonVo> Find(String find){
		getConnect();
		List<PersonVo> PL = new ArrayList<PersonVo>();
		try {
			
			
			//3. SQL문 준비/바인딩/실행
			String Query = "";

			Query += "select 	person_id,  "; 
			Query += "		 	name,  "; 
			Query += "  		hp,  "; 
			Query += " 			company  "; 
			Query += " from 	person "; 
			if(find != "!")
			{
			Query += " where  	name like ? ";
			Query += " or	  	hp like ? ";
			Query += " or	  	company like ? "; 

			pstmt = conn.prepareStatement(Query);
			
			pstmt.setString(1, "%"+find+"%");
			pstmt.setString(2, "%"+find+"%");
			pstmt.setString(3, "%"+find+"%");
			}
			else {
			
			}
			rs = pstmt.executeQuery(); // 쿼리문 실행(정보 oracle로 보내기)
			
			//4.결과처리
			while(rs.next()){
				int person_id = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				//리스트에 추가
				PersonVo pv = new PersonVo(person_id,name,hp,company);
				PL.add(pv);
				
				
			}
			}catch(SQLException e)
			{
				System.out.println("error."+ e);
			}
			Closer();
			return PL;
	}
	
	//끝
	public void End(){
		System.out.println("***********************************************");
    	System.out.println("*                  감사합니다                 *");
    	System.out.println("***********************************************");
	
		
	}
	
	
}
