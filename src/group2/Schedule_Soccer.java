package group2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class Schedule_Soccer {
	ArrayList<Schedule_VO> Soccer_list = new  ArrayList<>();
	
	void schedule(String teamName) {
		
		String name = teamName;
		String code = "[2]";
		switch(name)
		{
		case("��������") : 
			code = "[2]" ;
			break;
		case("�泲FC") : 
			code = "[3]" ;
			break;
		case("�������") : 
			code = "[4]" ;
			break;
		case("�����Ｚ") : 
			code = "[5]" ;
			break;
		case("���׽�ƿ����") : 
			code = "[6]" ;
			break;
		case("����FC") : 
			code = "[7]" ;
			break;
		case("�뱸FC") : 
			code = "[8]" ;
			break;
		case("����������Ƽ��") : 
			code = "[9]" ;
			break;
		case("FC����") : 
			code = "[10]" ;
			break;
		case("���ֻ�") : 
			code = "[11]" ;
			break;
		case("�����巡����") : 
			code = "[12]" ;
			break;
		case("��õ������Ƽ��") : 
			code = "[13]" ;
			break;


		}
		String projectPath = System.getProperty("user.dir");
		String chromePath = projectPath + "\\lib\\selenium\\chromedriver.exe";
		System.out.println(chromePath);
		
		System.setProperty("webdriver.chrome.driver", chromePath);
//		
//		ChromeOptions co = new ChromeOptions();
//		co.setHeadless(true);
//		WebDriver driver = new ChromeDriver(co);
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://www.kleague.com/schedule");
		
		WebElement a = (WebElement)driver.findElement( By.xpath("//*[@id=\"select_club\"]/option"+code));
		a.click();
		// Ŭ������ ��Ų ��, getPageSource()
		
		Document doc = Jsoup.parse( driver.getPageSource() );
		
		Elements tr = doc.select("table tbody tr") ;
		Elements tr2 = doc.select("thead tr") ;
		
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy.MM.dd");
	    String date_present = fm1.format(new Date());
	 
	    Schedule_DB db = new Schedule_DB();
	    
		String projectPath2 = System.getProperty("user.dir");
		String dbPath = projectPath2 + "\\lib\\group2\\Schedule.db3";
		String connPath = "jdbc:sqlite:" + dbPath;
		
		//	sqlite jdbc�� ����� ����ƴ��� Ȯ��
		if(db.jdbcCheck("org.sqlite.JDBC") == false)
		{
			JOptionPane.showMessageDialog(null, "jdbc ���̺귯���� Ȯ���ϼ���");	
			return; 
		}
		
		// db����
		if(!db.connectDb(connPath)) { 
			JOptionPane.showMessageDialog(null, "db ���� ������ Ȯ���ϼ���\n"+connPath);	
			return; 
		}
		
		
		// table ����
		db.defTable(1, "Schedule_Soccer");
 
		//	������ ����
//		db.deleteData(); // ������ ���µ� ��� �����ص� ��� ����
		
		// 	������ ��ȸ 
//		db.selectData();
		
		
		for( int i = 0; i < tr.size(); i++ ) {
			Element team = tr.get(i);
			
			String date1 = tr2.get(i).select("th").text();
			String teams1 = team.select("td").get(1).text();
			String place = team.select("td").get(3).text();

			String date[] = date1.split(" ");
			String teams[] = teams1.split(" ");
			
			String home = teams[0];
			String away = teams[2];
			String link = "http://www.kleague.com/schedule";
			
			 try {
			        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
			        Date Date = formatter.parse(date[0]);			 
			        Date DateP = formatter.parse(date_present);
			        
			        long diff = Date.getTime() - DateP.getTime();
			        long diffDays = diff / (24 * 60 * 60 * 1000);

			   		       
			        if(diffDays >= 0)
			        {
			        	Soccer_list.add( new Schedule_VO(
								date[0], date[1], home, away, place, link));
			        }
			        
			      
			   } catch (ParseException g) {
						        g.printStackTrace();
			    }
			 
			 Schedule_CSV.FileWrite(Soccer_list);

		}
		
		for(int j = 0; j < Soccer_list.size();j++)
		{
			Schedule_VO v = Soccer_list.get(j);
			
			db.insertData("Schedule_Soccer", v.getDate(), v.getDate2(), v.getHome(), 
							v.getAway(),v.getPlace(), v.getLink());
		}
		
		db.defTable(2,"Schedule_Soccer");
		
//		driver.close();
	}	
}


