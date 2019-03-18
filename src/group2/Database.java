package group2;
import java.io.*;
import java.sql.*;

public class Database {

	Connection conn;

	public String projectPath = System.getProperty("user.dir");
	public String imgPath = projectPath + "\\img";

	// DB연결
	public void connectDB() {

		String os = System.getProperty("os.name").toLowerCase();

		String projectPath = null;
		String dbPath = null;
		String connPath = null;

		if (os.contains("mac")) {
			projectPath = System.getProperty("user.dir");
			dbPath = projectPath + "//lib//Sqlite3//DB.db";
			connPath = "jdbc:sqlite:" + dbPath;

		} else {

			projectPath = System.getProperty("user.dir");
			dbPath = projectPath + "\\lib\\sqlite\\club.db3";
			connPath = "jdbc:sqlite:" + dbPath;

		}

		try {
			Class.forName("org.sqlite.JDBC");
			//System.out.println("JDBC connected successfully!!");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(connPath);
			//System.out.println("connected successfully!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// table 생성 및 제거
	void defTable(int num) {

		try {

			Statement s = conn.createStatement();
			String query1 = null;
			String query2 = null;
			String query3 = null;

			if (num == 1) { // CREATE
				query1 = "CREATE TABLE Basketball_Club(ID INT PRIMARY KEY, Photo Text, Club_Name Text)";
				query2 = "CREATE TABLE Football_Club(ID INT PRIMARY KEY, Photo Text, Club_Name Text)";
				query3 = "CREATE TABLE Volleyball_Club(ID INT PRIMARY KEY, Photo Text, Club_Name Text)";
			} else if (num == 2) { // DROP Basketball_Club Table
				query1 = "DROP TABLE Basketball_Club";
			} else if (num == 3) { // DROP Football_Club Table
				query2 = "DROP TABLE Football_Club";
			} else if (num == 4) { // DROP Volleyball_Club Table
				query3 = "DROP TABLE Volleyball_Club";
			} else if (num == 5) { // DROP all Tables
				query1 = "DROP TABLE Basketball_Club";
				query2 = "DROP TABLE Football_Club";
				query3 = "DROP TABLE Volleyball_Club";
			}

			s.execute(query1);
			s.execute(query2);
			s.execute(query3);

			s.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	// 데이터 추가
	public void insertBasketballData() {

		BasketBall_Club bc = new BasketBall_Club();

		String[] result1 = bc.searchBasketballClub();
		

		try {
			
			String bcQuery = "INSERT INTO Basketball_Club(ID, Photo, Club_Name) values (?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(bcQuery);
			
			for (int i = 0; i < result1.length; i++) {
				ps.setInt(1, (i + 1));
				ps.setString(2, (imgPath +"\\basketball_club_"+i+".png"));
				ps.setString(3, result1[i]);
				
				ps.executeUpdate();

			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertFootballData() {

		Football_Club fc = new Football_Club();

		String[] result2 = fc.searchFootballClub();

		String fcQuery = "INSERT INTO Football_Club(ID, Photo, Club_Name) values (?,?,?)";

		try {
		
			PreparedStatement ps = conn.prepareStatement(fcQuery);

		for (int i = 0; i < result2.length; i++) {

			ps.setInt(1, (i + 1));
			ps.setString(2, (imgPath + "\\football_club_"+i+".png"));
			ps.setString(3, result2[i]);
			
			ps.executeUpdate();

		}

		ps.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertVolleyballData() {
		
		VolleyBall_Club vc = new VolleyBall_Club();
		
		String[] result3 = vc.searchVolleyBallClub();
		
		String vcQuery = "INSERT INTO Volleyball_Club(ID, Photo, Club_Name) values (?,?,?)";
		
		try {
		
			PreparedStatement ps = conn.prepareStatement(vcQuery);

		for (int i = 0; i < result3.length; i++) {

			ps.setInt(1, (i + 1));
			ps.setString(2, (imgPath + "\\volleyball_club_"+i+".png"));
			ps.setString(3, result3[i]);
			
			ps.executeUpdate();

		}

		ps.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 데이터 삭제
	public void deleteData() {
		try {
			Statement s = conn.createStatement();
			String query = "DELETE FROM Basketball_Club";
			String query2 = "DELETE FROM Football_Club";
			String query3 = "DELETE FROM Volleyball_Club";
			s.execute(query);
			s.execute(query2);
			s.execute(query3);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	public void insertData() {
		insertBasketballData();
		insertFootballData();
		insertVolleyballData();
	}


public String selectBasketballData(int num) {
	
	String result = "";
	
	String sql = "SELECT Photo FROM Basketball_Club WHERE id = " + num;
	
	try {
		connectDB();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			result = (rs.getString("Photo"));
		
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	return result;
}

public String selectFootballData(int num) {
	
	String result = "";
	
	String sql = "SELECT Photo FROM Football_Club WHERE id = " + num;
	
	try {
		connectDB();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			result = (rs.getString("Photo"));
		
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	return result;
}

public String selectVolleyballData(int num) {
	
	String result = "";
	
	String sql = "SELECT Photo FROM Volleyball_Club WHERE id = " + num;
	
	try {
		connectDB();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			result = (rs.getString("Photo"));
		
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	return result;
}
	
}

/*
 * ---------------------------사진 데이터 삽입--------------------------
 * 
 * try {
 * 
 * FileInputStream fls; int num_rows = 0; File image = new File(
 * "C:\\이경민\\web_app\\01_JAVA\\workspace\\Sports_Booking_System\\img\\basketball_club_0.jpg"
 * ); // image = new File(
 * "/Users/geofflee/eclipse-workspace/01_JAVA/workspace/Sports_Booking_System/img/basketball_club_0.jpg"
 * ); fls = new FileInputStream(image);
 * 
 * ByteArrayOutputStream bos = new ByteArrayOutputStream(); byte[] buf = new
 * byte[1024]; for(int readNum; (readNum = fls.read(buf)) != -1;) {
 * bos.write(buf, 0, readNum); }
 * 
 * fls.close();
 * 
 * String query =
 * "INSERT INTO Basketball_Club(ID, Photo, Club_Name) values (?,?,?)";
 * 
 * PreparedStatement ps = conn.prepareStatement(query);
 * 
 * ps.setInt(1, num); ps.setBytes(2, bos.toByteArray()); ps.setString(3, name);
 * 
 * 
 * num_rows = ps.executeUpdate(); if(num_rows>0) {
 * System.out.println("Data has been inserted"); } ps.close();
 * 
 * 
 * } catch (Exception e) { e.printStackTrace(); }
 * 
 */
