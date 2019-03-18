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

public class fourth extends JFrame {

	Database db = new Database();
	String[] team_basketball = { "DB", "�Ｚ", "SK", "LG", "������", "���ڷ���", "KCC", "KGC", "KT", "���" };
	ArrayList<JButton> btn_list = new ArrayList<>();

	// ��/�౸/�豸 --> ���� �� (1,2,3)
	// ������ ����ִ� �迭
	public fourth() {
		super("��");

		// â
		Container con = getContentPane();
		setPreferredSize(new Dimension(800, 550));
		pack();
		setLocationRelativeTo(null);
		con.setLayout(null);

		init();
		start();

//		setLocation(550, 300);
		setVisible(true);
//		setSize(800, 550);
		setResizable(true);

		// ����

		JLabel label = new JLabel("������� ������ Ŭ���ϼ���");
		label.setBounds(250, 50, 300, 20);
		label.setFont(new Font("�������", Font.BOLD, 20));
		label.setOpaque(true);
		con.add(label);

		// ��ư

		int x = 36;
		int y = 231;
	
//		String[] team_arr;
//		switch ( team_value ) {
//		case 1: // ��
//			team_arr = team_basketball;
//			break;
//		case 2: // �౸
//			team_arr = team_basketball;
//			break;
//		case 3: // �豸
//			team_arr = team_basketball;
//			break;
//
//		default:
//			break;
//		}
		
		for (int i = 0; i < team_basketball.length; i++) {
			JButton btn = new JButton();
			btn.setText(team_basketball[i]);
			btn.setBounds(x, y, 136, 29);
			btn.setFont(new Font("�������", Font.BOLD, 15));

			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Schedule_BasketBall s = new Schedule_BasketBall();
					s.schedule(btn.getText());

					new GUIJTable(btn.getText(), s.basketball_list);
				}
			});

			con.add(btn);
			btn_list.add(btn);

			x += 150;
			if (i == 4) {
				x = 36;
				y = 419;
			}
		}

		// ��
		int x2 = 36;
		int y2 = 86;

		for (int i = 0; i < team_basketball.length; i++) {

			JLabel clubImage1 = new JLabel();
			clubImage1.setHorizontalAlignment(SwingConstants.CENTER);
			clubImage1.setBounds(x2, y2, 136, 120);
			clubImage1.setIcon(new ImageIcon(db.selectBasketballData(i + 1)));
			con.add(clubImage1);

			x2 += 150;
			if (i == 4) {
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