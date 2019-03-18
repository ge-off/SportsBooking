package group2;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Football_Club {

	String[] clubWebpage = { "05", "20", "01", "02", "03", "21", "17", "04", "09", "23", "07", "18" };
	public String projectPath = System.getProperty("user.dir");
	public String imgPath = projectPath + "\\img";

	public String[] searchFootballClub() {

		String[] club = new String[12];

		try {

			for (int i = 0; i < 12; i++) {

				Document document = Jsoup.connect("http://www.kleague.com/club?club=K" + clubWebpage[i]).get();

				Elements div = document.select("div.team-info.float-left");
				Elements h3 = div.select("h3.club-name");

				club[i] = h3.text();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return club;
	}


	public void extractImageFootball() {

		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {

			String imgUrl = "";

			for (int i = 0; i < 12; i++) {

				Document document = Jsoup.connect("http://www.kleague.com/club?club=K" + clubWebpage[i]).get();

				Elements div = document.select("div.team-logo.float-left");
				Elements img = div.select("img");
				String imgSrc = img.attr("src");

				imgUrl = imgSrc;

				URL url = new URL(imgUrl);
				inputStream = url.openStream();
				outputStream = new FileOutputStream(imgPath + "\\football_club_" + i + ".png");

				byte[] buffer = new byte[2048];
				int length;

				while ((length = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, length);
				}

			}

		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException :- " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException :- " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException :- " + e.getMessage());
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				System.out.println("Finally IOException :- " + e.getMessage());
			}

		}

	}

}
