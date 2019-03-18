package group2;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

public class GUIJTable extends JFrame{

	public GUIJTable(String Str, ArrayList<Schedule_VO> list) {
		super(Str);
		
		setBounds(300, 300, 800, 800);

		Container con = getContentPane();
		
		String [] title = {"날짜", "요일", "홈팀", "원정팀", "장소", "링크"};
		
		DefaultTableModel model = new DefaultTableModel(null, title);
		JTable table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
	
		for( int i = 0; i < list.size(); i++ ) {
			Schedule_VO vo = list.get(i);
			
			String[] addData = new String[6];
			addData[0] = vo.getDate();
			addData[1] = vo.getDate2();
			addData[2] = vo.getHome();
			addData[3] = vo.getAway();
			addData[4] = vo.getPlace();
			addData[5] = vo.getLink();

			model.addRow(addData);
		}
			
			table.setRowSorter(new TableRowSorter(model));

			
			con.add(sp);
			
			pack();
			
			setVisible(true);
		}
	}

