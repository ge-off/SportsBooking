package group2;
import java.io.*;
import java.net.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;


public class BasketBall_Club {
	
	public String projectPath = System.getProperty("user.dir");
	public String imgPath = projectPath + "\\img";

	public String[] searchBasketballClub() {

		String[] clubName = new String[10];

		try {

			Document document = Jsoup.connect("http://www.kbl.or.kr/teams/team_info.asp?tcode=16").get();


			Elements nav = document.select("nav.snb");
			Elements ul = nav.select("ul");
			Elements li = ul.select("li");

			for (int i = 0; i < li.size(); i++) {

				Element club = li.get(i);
				clubName[i] = club.select("a").text();
//				System.out.println(clubName);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return clubName;
	}
	
	

	public void extractImageBasketBall() {
		
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {

			String imgUrl = "";			

			Document document = Jsoup.connect("http://www.kbl.or.kr/teams/team_info.asp?tcode=16").get();

			Elements div = document.select("div.foot_link");
			Elements ul = div.select("ul");
			Elements li = ul.select("li");
			Elements img = li.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

			for (int i = 0; i < li.size(); i++) {

				Element logo = img.get(i);
				imgUrl = "http://kbl.or.kr" + logo.attr("src");

				for (int j = 0; j < 10; j++) {

					URL url = new URL(imgUrl);
					inputStream = url.openStream();
					outputStream = new FileOutputStream(imgPath + "\\basketball_club_" + i + ".png");

					byte[] buffer = new byte[2048];
					int length;

					while ((length = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, length);
					}

				}

			}

		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException :- " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException :- " + e.getMessage());
		}catch (IOException e) {
			System.out.println("IOException :- " + e.getMessage());
		}finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				System.out.println("Finally IOException :- " + e.getMessage());
			}

		}

	}
	


}
