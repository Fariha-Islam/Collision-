import java.awt.BorderLayout;
import java.util.Scanner.*;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;

import javax.print.DocFlavor.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;

public class HowToPlay extends JFrame  {

	private JPanel contentPane;
	private static JFrame frame2;
	FrameWindow fw=new FrameWindow();


	/**
	 * Launch the application.
	 */
	public static void HowToPlay (String[] args)  throws MalformedURLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HowToPlay frame = new HowToPlay();
					frame2=frame;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HowToPlay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 532, 470);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPlayersingle = new JLabel("Player 1 Controls: ");
		lblPlayersingle.setFont(new Font("Lucida Calligraphy", Font.BOLD, 15));
		lblPlayersingle.setForeground(new Color(153, 0, 0));
		lblPlayersingle.setBounds(10, 11, 285, 32);
		contentPane.add(lblPlayersingle);
		
		JLabel lblNewLabel = new JLabel("New label");
		Image img4= new ImageIcon(this.getClass().getResource("arrows.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img4));
		lblNewLabel.setBounds(10, 56, 181, 94);
		contentPane.add(lblNewLabel);
		
		JLabel lblPlayerControls = new JLabel("Player 2 Controls:");
		lblPlayerControls.setFont(new Font("Lucida Calligraphy", Font.BOLD, 15));
		lblPlayerControls.setForeground(new Color(153, 0, 0));
		lblPlayerControls.setBounds(10, 161, 235, 36);
		contentPane.add(lblPlayerControls);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		Image img= new ImageIcon(this.getClass().getResource("key2.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img));
		lblNewLabel_1.setBounds(30, 204, 164, 120);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Instructions: Eat the foods to grow bigger and earn points. When 30 points reached,\r\n");
		lblNewLabel_2.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_2.setForeground(new Color(153, 0, 0));
		lblNewLabel_2.setBounds(10, 324, 481, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("eat each other to deduce size and minus score of your opponent! Watch out for the");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_3.setForeground(new Color(153, 0, 0));
		lblNewLabel_3.setBounds(10, 347, 496, 26);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("bigger enemies as they will kill you and the game will be over!! You can eat the smaller ");
		lblNewLabel_4.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_4.setForeground(new Color(153, 0, 0));
		lblNewLabel_4.setBounds(10, 371, 506, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			FrameWindow fw=new FrameWindow();
			fw.frame.setVisible(true);
			dispose();

				
			
			}
		});
		btnBack.setBackground(Color.BLACK);
		btnBack.setForeground(new Color(153, 0, 0));
		btnBack.setFont(new Font("Lucida Calligraphy", Font.BOLD | Font.ITALIC, 13));
		btnBack.setBounds(10, 408, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblEnemiesAllThe = new JLabel("enemies! All the best!  ");
		lblEnemiesAllThe.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 13));
		lblEnemiesAllThe.setForeground(new Color(153, 0, 0));
		lblEnemiesAllThe.setBounds(10, 384, 285, 26);
		contentPane.add(lblEnemiesAllThe);
		
		JLabel lblNewLabel_5 = new JLabel("");
		Image img5= new ImageIcon(this.getClass().getResource("i5.jpg")).getImage();
		lblNewLabel_5.setIcon(new ImageIcon(img5));
		lblNewLabel_5.setBounds(170, 0, 346, 431);
		contentPane.add(lblNewLabel_5);
	}
}
