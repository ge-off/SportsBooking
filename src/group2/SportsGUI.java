package group2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SportsGUI extends JFrame {
	final int FRAME_W = 500;
	final int FRAME_H = 400;

	public SportsGUI() {

		// Ã¢
		super("2018 Sports");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = new Dimension();
		Container con = getContentPane();

		con.setLayout(null);

		screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (screen.width / 2) - (FRAME_W / 2);
		int yPos = (screen.height / 2) - (FRAME_H / 2);

		setBounds(xPos, yPos, FRAME_W, FRAME_H);

		// º¸°í½ÍÀº ¾îÂ¼°í ÀúÂ¼°í

		JLabel label = new JLabel("º¸°í½ÍÀº Á¾¸ñÀ» ¼±ÅÃÇÏ¼¼¿ä");
		label.setBounds(110, 50, 300, 20);
		label.setForeground(Color.black); // ±ÛÀÚ»ö
		label.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 20));
		label.setOpaque(true);
		con.add(label);

		// ¹öÆ°

		JButton btn_soccer = new JButton();
		btn_soccer.setText("Ãà±¸");
		btn_soccer.setBounds(50, 150, 80, 80);
		btn_soccer.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 15));
		con.add(btn_soccer);

		JButton btn_volley = new JButton();
		btn_volley.setText("¹è±¸");
		btn_volley.setBounds(200, 150, 80, 80);
		btn_volley.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 15));
		con.add(btn_volley);

		JButton btn_basket = new JButton();
		btn_basket.setText("³ó±¸");
		btn_basket.setBounds(350, 150, 80, 80);
		btn_basket.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 15));
		con.add(btn_basket);

		setVisible(true);

		btn_soccer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Object obj = e.getSource();
				if ((JButton) obj == btn_soccer) {
					new second();
				}
			}
		});

		btn_volley.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Object obj = e.getSource();
				if ((JButton) obj == btn_volley) {
					new third();
				}
			}
		});

		btn_basket.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Object obj = e.getSource();
				if ((JButton) obj == btn_basket) {
					new fourth();
				}
			}
		});
	}

	public static void main(String[] args) {
		Database db = new Database();
		// TODO Auto-generated method stub
		db.connectDB();
		db.deleteData();
		db.insertData();
		new SportsGUI();
	}

}
