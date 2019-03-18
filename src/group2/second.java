package group2;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class second extends JFrame {
	Database db = new Database();
	String [] team_soccer = {
			"전북현대",
			"경남FC",
			"울산현대",
			"수원삼성",
			"포항",
			"강원FC",
			"대구FC",
			"제주유나",
			"FC서울",
			"상주상무",
			"전남드래곤즈",
			"인천유나" };
	ArrayList<JButton> btn_list = new ArrayList<>();

	public second() {
		
		super("축구");
		// 창
		Container con = getContentPane();
		setPreferredSize(new Dimension(950,550));
		pack();
		setLocationRelativeTo(null);
		con.setLayout(null);

		init();
		start();

//		setLocation(500, 300);
		setVisible(true);
//		setSize(950, 550);
		setResizable(true);

		// 글자
		JLabel label = new JLabel("보고싶은 구단을 클릭하세요");
		label.setBounds(350, 50, 300, 20);
		label.setFont(new Font("맑은고딕", Font.BOLD, 20));
		label.setOpaque(true);
		con.add(label);

		// 버튼
		
		int x = 36;
		int y = 231;
		for( int i = 0; i < team_soccer.length; i++ ) {
			JButton btn = new JButton();
			btn.setText(team_soccer[i]);
			btn.setBounds(x, y, 136, 29);
			btn.setFont(new Font("맑은고딕", Font.BOLD, 15));
			
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Schedule_Soccer s = new Schedule_Soccer();
					s.schedule( btn.getText() );
					
					new GUIJTable( btn.getText(), s.Soccer_list );
				}
			});
			
			con.add(btn);
			btn_list.add(btn);
			
			x += 150;
			
			if( i == 5 ) {
				x = 36;
				y = 419;
			}
		}
		
		//라벨
		int x2 = 36;
		int y2 = 86;
		
		for( int i = 0; i < team_soccer.length; i++ ) {
			
			JLabel clubImage1 = new JLabel();
			clubImage1.setHorizontalAlignment(SwingConstants.CENTER);
			clubImage1.setBounds(x2, y2, 136, 120);
			clubImage1.setIcon(new ImageIcon(db.selectFootballData(i+1)));
			con.add(clubImage1);
			
			x2+=150;
			if( i==5 ) {
				x2 = 36;
				y2 = 270;
			}
			
		}
	}

	public void init() {
	}

	public void start() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}
}
