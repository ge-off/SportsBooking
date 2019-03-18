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
		case("�����װ�") : 
			code = "1001";
			break;
		case("����ĳ��Ż") : 
			code = "1005";
			break;
		case("�Ｚȭ��") : 
			code = "1002";
			break;
		case("KB���غ���") : 
			code = "1004";
			break;
		case("�ѱ�����") : 
			code = "1006";
			break;
		case("�츮ī��") : 
			code = "1009";
			break;
		case("OK��������") : 
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
	   
		//	sqlite jdbc�� ����� ����ƴ��� Ȯ��
		if(db.jdbcCheck("org.sqlite.JDBC") == false)
		{
			JOptionPane.showMessageDialog(null, "jdbc ���̺귯���� Ȯ���ϼ���");	
			return;
		}
		
//		// db����
		if(!db.connectDb(connPath)) { 
			JOptionPane.showMessageDialog(null, "db ���� ������ Ȯ���ϼ���\n"+connPath);	
			return; 
		}

		db.defTable(1, "Schedule_VolleyBall"); 
 
		//	������ ����
//		db.deleteData(); 
		
		// 	������ ��ȸ
//		db.selectData();
		
		for(int mon = 10; mon <= 12 ; mon++)	{
	   
			driver.get("http://kovo.co.kr/game/v-league/11110_schedule_list.asp?team="+code+"&season=015&yymm=2018-"+mon+"&s_part=1");
	
			Document doc = Jsoup.parse( driver.getPageSource() );
			
			Elements table = doc.select("table.lst_board.lst_schedule") ;
			Elements tr = table.select("tbody tr");
					
			
			for( int i = 0; i < tr.size(); i++ ) {	
				
				Element team = tr.get(i);
				
				if(team.select("td").get(2).text().equals("��Ⱑ �����ϴ�.") == false)
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
			// �̵��� �������� �ҽ� �о����
			Document doc = Jsoup.parse( driver.getPageSource() );
			
			// ���� �Ľ� �ڵ�� Jsoup�� �̿��Ͽ� �����ϰ� ���!!

			Elements table = doc.select("table.lst_board.lst_schedule") ;
			Elements tr = table.select("tbody tr");
			
			
			
			for( int i = 0; i < tr.size(); i++ ) {	
				
				Element team = tr.get(i);
				
				if(team.select("td").get(2).text().equals("��Ⱑ �����ϴ�.") == false)
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
	