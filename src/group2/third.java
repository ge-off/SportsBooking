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

public class third extends JFrame {

	Database db = new Database();

	String[] team_volleyball = { "�����װ�", "����ĳ��Ż", "�Ｚȭ��", "KB���غ���", "�ѱ�����", "�츮ī��", "OK��������", };

	ArrayList<JButton> btn_list = new ArrayList<>();

	public third() {
		super("�豸");

		// â
		Container con = getContentPane();
		setPreferredSize(new Dimension(650, 550));
		pack();
		setLocationRelativeTo(null);
		con.setLayout(null);

		init();
		start();

//		setLocation(600, 300);
		setVisible(true);
//		setSize(650, 550);
		setResizable(true);

		// ����

		JLabel label = new JLabel("������� ������ Ŭ���ϼ���");
		label.setBounds(200, 50, 300, 20);
		label.setFont(new Font("�������", Font.BOLD, 20));
		label.setOpaque(true);
		con.add(label);

		// ��ư
		int x = 40;
		int y = 219;
		for (int i = 0; i < team_volleyball.length; i++) {
			JButton btn = new JButton();
			btn.setText(team_volleyball[i]);
			btn.setBounds(x, y, 126, 29);
			btn.setFont(new Font("�������", Font.BOLD, 15));

			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Schedule_VolleyBall v = new Schedule_VolleyBall();
					v.schedule(btn.getText());

					new GUIJTable(btn.getText(), v.VolleyBall_list);

				}
			});

			con.add(btn);
			btn_list.add(btn);

			x += 150;
			if (i == 3) {
				x = 36;
				y = 419;
			}
		}

		int x2 = 36;
		int y2 = 86;

		for (int i = 0; i < team_volleyball.length; i++) {

			JLabel clubImage1 = new JLabel();
			clubImage1.setHorizontalAlignment(SwingConstants.CENTER);
			clubImage1.setBounds(x2, y2, 136, 120);
			clubImage1.setIcon(new ImageIcon(db.selectVolleyballData(i + 1)));
			con.add(clubImage1);

			x2 += 150;
			if (i == 3) {
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
