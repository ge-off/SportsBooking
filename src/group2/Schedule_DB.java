package group2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class Schedule_DB {
	
	Connection conn; // db연결 정보를 지닌 객체(멤버변수) 쿼리 날릴때마다 사용
	
	boolean jdbcCheck(String path) {
		
		
		boolean check = false;
		
		try {
			Class.forName(path);
			check = true; 
		} catch (ClassNotFoundException e) {}
		
		return check;
	}
	
	boolean connectDb(String path)
	{
		boolean check = false;
		
		try {
			conn = DriverManager.getConnection(path);
			
			check = true; 
		} catch (SQLException e) {
			
		}
		
		return check;
	}
	
	// table 생성/삭제
	void defTable(int num, String name)
	{
		try {
			
			Statement s = conn.createStatement();
			String query = null;
			
			if(num == 1)
			{
				// CREATE
				query = "CREATE TABLE "+name+"(date TEXT, date2 TEXT, home TEXT, away TEXT, place TEXT, link TEXT)";
				
			}
			else
			{
				// DROP
				query = "DROP TABLE "+name;
			}
		
			s.execute(query);
			
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	void insertData(String name, String date, String date2, String home, String away, String place, String link)
	{
		try {
			String query = "INSERT INTO "+name+"(date, date2, home, away, place, link) values(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
						
			ps.setString(1, date);
			ps.setString(2, date2);
			ps.setString(3, home);
			ps.setString(4, away);
			ps.setString(5, place);		
			ps.setString(6, link);
			
			ps.executeUpdate(); 	
	
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void deleteData(String name)
	{
		try {
			Statement s = conn.createStatement();
			String query = "DELETE FROM "+name; 
		
			s.execute(query);

			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	
	
//	void selectData()
//	{
//		// 	응용한다면
//		//	SELECT 뒤에 적는 조회될 컬럼
//		//	SELECT 할 테이블명
//		//	SELECT 할 조건
//		// 	이런 것들을 매개변수로 처리하면, 하나의 메서드에서 가공 가능
//		try {
//			Statement s = conn.createStatement();
//			// student 테이블에서 '나이가 21살인 사람의 전체정보' 조회
//			String query = "SELECT * FROM student WHERE age = 21";
//			
//			ResultSet r = s.executeQuery(query);
//			// SELECT 쿼리 수행의 결과가 r에 대입
//			// r에는 조회 결과가 한 행씩 들어있다
//			
//			while(r.next()) // 조회된 다음 행이 있다면, (2개면 2번 수행)
//			{
//				System.out.println("이름 : " + r.getString("name")); // 컬럼명
//				System.out.println("나이 : " + r.getInt(3)); //  순서(1부터)
//				
//			}
//			
//			// 	닫아야 함(생성한 역순으로)
//			r.close();
//			s.close();
//			// 	항상 conn를 닫으면 안된다...! (얘는 계속 사용할 객체)
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
