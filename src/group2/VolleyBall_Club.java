package group2;
import java.io.*;
import java.net.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class VolleyBall_Club {
	
	public String projectPath = System.getProperty("user.dir");
	public String imgPath = projectPath + "\\img";

	public String[] searchVolleyBallClub() {

		String[] clubName = new String[7];

		try {

			Document document = Jsoup.connect("http://www.kovo.co.kr/team/21101_allteam_list.asp").get();

			Elements article = document.select("article.lst_allteam");
			Elements ul = article.select("ul");
			Elements li = ul.select("li");
			Elements div = li.select("div.wrp_team");

			for (int i = 0; i < li.size(); i++) {

				Element club = li.get(i);
				clubName[i] = club.select("h4").text();
//				System.out.println(clubName);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return clubName;

	}

	public void extractImageVolleyBallClub() {

		String url = "";
		String noDotUrl = "";

		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {

			Document document = Jsoup.connect("http://www.kovo.co.kr/team/21101_allteam_list.asp").get();

			Elements article = document.select("article.lst_allteam");
			Elements ul = article.select("ul");
			Elements li = ul.select("li");
			Elements div = li.select("div.wrp_emblem");
			Elements img = div.select("img");

			for (int i = 0; i < li.size(); i++) {

				Element club = img.get(i);
				url = club.attr("src");
				noDotUrl = "http://kovo.co.kr" + url.replace("..", "");

				for (int j = 0; j < 10; j++) {

					URL imgUrl = new URL(noDotUrl);
					inputStream = imgUrl.openStream();
					outputStream = new FileOutputStream(imgPath + "\\volleyball_club_" + i + ".png");

					byte[] buffer = new byte[2048];
					int length;

					while ((length = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, length);
					}

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
