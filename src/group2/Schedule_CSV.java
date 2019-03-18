package group2;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Schedule_CSV {
	
	static String csvPath;
	static void FileWrite(ArrayList<Schedule_VO> list) {
	
		String projectPath 	= System.getProperty("user.dir");
		csvPath = projectPath + "\\lib\\Schedule.csv";
		
		try {
			BufferedWriter writer = new BufferedWriter( new FileWriter(csvPath) );
			
			writer.write("date,date2,home,away,place,link\n");
			
			for(int i = 0; i < list.size(); i++) {
				Schedule_VO s = list.get(i); 
				
				writer.write( String.format("%s,%s,%s,%s,%s,%s\n", 
												s.getDate(),
												s.getDate2(),
												s.getHome(),
												s.getAway(),
												s.getPlace(),
												s.getLink()) 	);
			} 
			
			writer.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
