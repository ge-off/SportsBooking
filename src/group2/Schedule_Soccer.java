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
		case("전북현대") : 
			code = "[2]" ;
			break;
		case("경남FC") : 
			code = "[3]" ;
			break;
		case("울산현대") : 
			code = "[4]" ;
			break;
		case("수원삼성") : 
			code = "[5]" ;
			break;
		case("포항스틸러스") : 
			code = "[6]" ;
			break;
		case("강원FC") : 
			code = "[7]" ;
			break;
		case("대구FC") : 
			code = "[8]" ;
			break;
		case("제주유나이티드") : 
			code = "[9]" ;
			break;
		case("FC서울") : 
			code = "[10]" ;
			break;
		case("상주상무") : 
			code = "[11]" ;
			break;
		case("전남드래곤즈") : 
			code = "[12]" ;
			break;
		case("인천유나이티드") : 
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
		// 클릭까지 시킨 후, getPageSource()
		
		Document doc = Jsoup.parse( driver.getPageSource() );
		
		Elements tr = doc.select("table tbody tr") ;
		Elements tr2 = doc.select("thead tr") ;
		
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy.MM.dd");
	    String date_present = fm1.format(new Date());
	 
	    Schedule_DB db = new Schedule_DB();
	    
		String projectPath2 = System.getProperty("user.dir");
		String dbPath = projectPath2 + "\\lib\\group2\\Schedule.db3";
		String connPath = "jdbc:sqlite:" + dbPath;
		
		//	sqlite jdbc가 제대로 연결됐는지 확인
		if(db.jdbcCheck("org.sqlite.JDBC") == false)
		{
			JOptionPane.showMessageDialog(null, "jdbc 라이브러리를 확인하세요");	
			return; 
		}
		
		// db연결
		if(!db.connectDb(connPath)) { 
			JOptionPane.showMessageDialog(null, "db 연결 정보를 확인하세요\n"+connPath);	
			return; 
		}
		
		
		// table 생성
		db.defTable(1, "Schedule_Soccer");
 
		//	데이터 삭제
//		db.deleteData(); // 데이터 없는데 계속 수행해도 상관 없음
		
		// 	데이터 조회 
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


