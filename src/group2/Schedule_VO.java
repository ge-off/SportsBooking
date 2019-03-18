package group2;

public class Schedule_VO {
	private String date;
	private String date2;
	private String home;
	private String away;
	private String place;
	private String link;
	
	
	public Schedule_VO(String date, String date2, String home, String away, String place, String link) {
		this.date = date;
		this.date2 = date2;
		this.home = home;
		this.away = away;
		this.place = place;
		this.link = link;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getAway() {
		return away;
	}

	public void setAway(String away) {
		this.away = away;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}


}
