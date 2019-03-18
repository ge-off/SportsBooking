package group2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class Schedule_DB {
	
	Connection conn; // db���� ������ ���� ��ü(�������) ���� ���������� ���
	
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
	
	// table ����/����
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
//		// 	�����Ѵٸ�
//		//	SELECT �ڿ� ���� ��ȸ�� �÷�
//		//	SELECT �� ���̺��
//		//	SELECT �� ����
//		// 	�̷� �͵��� �Ű������� ó���ϸ�, �ϳ��� �޼��忡�� ���� ����
//		try {
//			Statement s = conn.createStatement();
//			// student ���̺��� '���̰� 21���� ����� ��ü����' ��ȸ
//			String query = "SELECT * FROM student WHERE age = 21";
//			
//			ResultSet r = s.executeQuery(query);
//			// SELECT ���� ������ ����� r�� ����
//			// r���� ��ȸ ����� �� �྿ ����ִ�
//			
//			while(r.next()) // ��ȸ�� ���� ���� �ִٸ�, (2���� 2�� ����)
//			{
//				System.out.println("�̸� : " + r.getString("name")); // �÷���
//				System.out.println("���� : " + r.getInt(3)); //  ����(1����)
//				
//			}
//			
//			// 	�ݾƾ� ��(������ ��������)
//			r.close();
//			s.close();
//			// 	�׻� conn�� ������ �ȵȴ�...! (��� ��� ����� ��ü)
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
