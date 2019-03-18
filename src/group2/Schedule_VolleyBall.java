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



public class Schedule_VolleyBall {
		ArrayList<Schedule_VO> VolleyBall_list = new  ArrayList<>();
		
		void schedule(String teamName) {
			
		String name = teamName;
		String code = null;
		switch(name)
		{
		case("대한항공") : 
			code = "1001";
			break;
		case("현대캐피탈") : 
			code = "1005";
			break;
		case("삼성화재") : 
			code = "1002";
			break;
		case("KB손해보험") : 
			code = "1004";
			break;
		case("한국전력") : 
			code = "1006";
			break;
		case("우리카드") : 
			code = "1009";
			break;
		case("OK저축은행") : 
			code = "1008";
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
		
//		// db연결
		if(!db.connectDb(connPath)) { 
			JOptionPane.showMessageDialog(null, "db 연결 정보를 확인하세요\n"+connPath);	
			return; 
		}

		db.defTable(1, "Schedule_VolleyBall"); 
 
		//	데이터 삭제
//		db.deleteData(); 
		
		// 	데이터 조회
//		db.selectData();
		
		for(int mon = 10; mon <= 12 ; mon++)	{
	   
			driver.get("http://kovo.co.kr/game/v-league/11110_schedule_list.asp?team="+code+"&season=015&yymm=2018-"+mon+"&s_part=1");
	
			Document doc = Jsoup.parse( driver.getPageSource() );
			
			Elements table = doc.select("table.lst_board.lst_schedule") ;
			Elements tr = table.select("tbody tr");
					
			
			for( int i = 0; i < tr.size(); i++ ) {	
				
				Element team = tr.get(i);
				
				if(team.select("td").get(2).text().equals("경기가 없습니다.") == false)
				{
					String date1 = team.select("td.date").text();
					String date[] = date1.split("[(]");
				
					date[0] = date[0].replace(" ", "");
					String date2 = "2018."+date[0];
					date[1] = date[1].replaceAll("[)]", "");
					
					String home = team.select("td.tright").text();
					String away = team.select("td.tleft").text();
					home = home.replace(":", "");
					String place = team.select("td").get(6).text();
					String link = team.select("td a.btn.btn_lst.wrp_rounded.w82.btn_blue").attr("href");
					
					
					 try {
					        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
					        Date Date = formatter.parse(date2);			 
					        Date DateP = formatter.parse(date_present);
					        					      			       
					        long diff = Date.getTime() - DateP.getTime();
					        long diffDays = diff / (24 * 60 * 60 * 1000);
					   		       
					        if(diffDays >= 0)
					        {
					        	VolleyBall_list.add( new Schedule_VO(
										date2, date[1], home, away, place, link));
					        	
					        }
				        
					   } catch (ParseException e) {
					        e.printStackTrace();
					    }
					 
					 Schedule_CSV.FileWrite(VolleyBall_list);
				}
				
			}
	
		}
		
		for(int mon = 1; mon <= 3 ; mon++)	{
			   
			driver.get("http://kovo.co.kr/game/v-league/11110_schedule_list.asp?season=015&team="+code+"&yymm=2019-0"+mon);
			// 이동된 페이지의 소스 읽어오기
			Document doc = Jsoup.parse( driver.getPageSource() );
			
			// 이후 파싱 코드는 Jsoup을 이용하여 동일하게 사용!!

			Elements table = doc.select("table.lst_board.lst_schedule") ;
			Elements tr = table.select("tbody tr");
			
			
			
			for( int i = 0; i < tr.size(); i++ ) {	
				
				Element team = tr.get(i);
				
				if(team.select("td").get(2).text().equals("경기가 없습니다.") == false)
				{
					String date1 = team.select("td.date").text();
					String date[] = date1.split("[(]");
				
					date[0] = date[0].replace(" ", "");
					String date2 = "2019."+date[0];
					date[1] = date[1].replaceAll("[)]", "");
					
					String home = team.select("td.tright").text();
					String away = team.select("td.tleft").text();
					home = home.replace(":", "");
					String place = team.select("td").get(6).text();
					String link = team.select("td a.btn.btn_lst.wrp_rounded.w82.btn_blue").attr("href");
					
					
					 try {
					        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
					        Date Date = formatter.parse(date2);			 
					        Date DateP = formatter.parse(date_present);
					        					      			       
					        long diff = Date.getTime() - DateP.getTime();
					        long diffDays = diff / (24 * 60 * 60 * 1000);
					   		       
					        if(diffDays >= 0)
					        {
					        	VolleyBall_list.add( new Schedule_VO(
										date2, date[1], home, away, place, link));
					        	
					        }
				        
					   } catch (ParseException e) {
					        e.printStackTrace();
					    }
					 
					 Schedule_CSV.FileWrite(VolleyBall_list);
				}
				
			}
	
		}
		
				
				for(int j = 0; j < VolleyBall_list.size();j++)
				{
					Schedule_VO v = VolleyBall_list.get(j);
					
					db.insertData("Schedule_VolleyBall", v.getDate(), v.getDate2(), v.getHome(), 
									v.getAway(),v.getPlace(), v.getLink());
				}
			
				db.defTable(2,"Schedule_VolleyBall");
				
//				driver.close();
			}

			
		}
	