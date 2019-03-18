package group2;

import java.text.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class Schedule_BasketBall {

	ArrayList<Schedule_VO> basketball_list = new  ArrayList<>();
	
	void schedule(String teamName) {
		
		String name = teamName;
		String code = null;
		
		switch(name)
		{
		case("DB") : 
			code = "16";
			break;
		case("삼성") : 
			code = "35";
			break;
		case("SK") : 
			code = "55";
			break;
		case("LG") : 
			code = "50";
			break;
		case("오리온") : 
			code = "30";
			break;
		case("전자랜드") : 
			code = "65";
			break;
		case("KCC") : 
			code = "60";
			break;
		case("KGC") : 
			code = "70";
			break;
		case("KT") : 
			code = "06";
			break;
		case("모비스") : 
			code = "10";
			break;
		}
				
		String projectPath = System.getProperty("user.dir");

		String chromePath = projectPath + "\\lib\\selenium\\chromedriver.exe";
		System.out.println(chromePath);
		
		System.setProperty("webdriver.chrome.driver", chromePath);
		
//		ChromeOptions co = new ChromeOptions();
//		co.setHeadless(true);
//		WebDriver driver = new ChromeDriver(co);
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://kbl.or.kr/teams/team_schedule.asp?tcode="+code);
		
		Document doc = Jsoup.parse( driver.getPageSource() );
		
		Elements tr = doc.select("table tbody tr") ;
		
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy.MM.dd");
	    String date_present = fm1.format(new Date());
 	    
	    Schedule_DB db = new Schedule_DB();
		
		String projectPath2 = System.getProperty("user.dir");
		String dbPath = projectPath2 + "\\lib\\group2\\Schedule.db3";
		String connPath = "jdbc:sqlite:" + dbPath;
				
			// sqlite jdbc가 제대로 연결됐는지 확인
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
		db.defTable(1,"Schedule_BasketBall"); 

		
		//	데이터 삭제
//		db.deleteData(); 
		
		String [][] ary = new String [tr.size()][6];
	    
	    for( int i = 0; i < tr.size(); i++ ) {
			
			Element team = tr.get(i);
			String date = team.select("td").get(0).text();
			String date2 = team.select("td").get(1).text();
			String home = team.select("td").get(2).text();
			String away = team.select("td").get(3).text();
			String place = team.select("td").get(4).text();
			String link = null;
			switch(place)
    		{
    		case("원주종합") : 
    			link = "http://www.ticketlink.co.kr/sports/basketball/69#reservation";
    			break;
    		case("잠실실내") : 
    			link = "http://www.ticketlink.co.kr/sports/basketball/70#reservation";
    			break;
    		case("잠실학생") : 
    			link = "http://ticket.interpark.com/Contents/Sports/GoodsInfo?SportsCode=07003&TeamCode=PK008";
    			break;
    		case("창원실내") : 
    			link = "http://ticket.interpark.com/Contents/Sports/GoodsInfo?SportsCode=07003&TeamCode=PK003";
    			break;
    		case("고양체육관") : 
    			link = "http://www.ticketlink.co.kr/sports/basketball/91#reservation";
    			break;
    		case("인천삼산") : 
    			link = "https://etlticket.kbl.or.kr/#/event-list";
    			break;
    		case("전주실내") : 
    			link = "http://ticket.interpark.com/Contents/Sports/GoodsInfo?SportsCode=07003&TeamCode=PK004";
    			break;
    		case("군산월명") : 
    			link = "http://ticket.interpark.com/Contents/Sports/GoodsInfo?SportsCode=07003&TeamCode=PK004";
    			break;
    		case("안양실내") : 
    			link = " http://ticket.interpark.com/Contents/Sports/GoodsInfo?SportsCode=07003&TeamCode=PK001";
    			break;
    		case("부산사직") : 
    			link = "http://www.ticketlink.co.kr/sports/basketball/128#reservation";
    			break;
    		case("울산동천") : 
    			link = "http://www.ticketlink.co.kr/sports/basketball/123#reservation";
    			break;
    		}
			
			
		    
			 try {
			        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
			        Date Date = formatter.parse(date);			 
			        Date DateP = formatter.parse(date_present);
			        
			        long diff = Date.getTime() - DateP.getTime();
			        long diffDays = diff / (24 * 60 * 60 * 1000);
			   		       
			        if(diffDays >= 0)
			        {
			        	basketball_list.add( new Schedule_VO(date, date2, home, away, place, link));
			         }
			        
			   } catch (ParseException e) {
			        e.printStackTrace();
			    }
 
			 Schedule_CSV.FileWrite(basketball_list);
			
		}
		
		for(int j = 0; j < basketball_list.size();j++)
		{
			Schedule_VO v = basketball_list.get(j);
			
			db.insertData("Schedule_BasketBall", v.getDate(), v.getDate2(), v.getHome(), 
							v.getAway(),v.getPlace(), v.getLink());
		}
	 
		db.defTable(2,"Schedule_BasketBall");
		
//		driver.close();
	}
}
