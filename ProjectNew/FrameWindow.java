import java.awt.EventQueue;
import java.util.Scanner;
import java.io.*;

import javax.swing.JFrame;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class FrameWindow {

	
	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameWindow window = new FrameWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(450, 150, 464, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("How To Play");
		btnNewButton.setForeground(new Color(102, 51, 102));
		btnNewButton.setBackground(new Color(51, 0, 0));
		btnNewButton.setFont(new Font("Lucida Calligraphy", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				HowToPlay htp= new HowToPlay();
				htp.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(148, 282, 168, 37);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("QUIT ");
		JButton close = new JButton("Close");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			}
		});
		btnNewButton_2.setForeground(new Color(204, 51, 102));
		btnNewButton_2.setBackground(SystemColor.textText);
		btnNewButton_2.setFont(new Font("Lucida Calligraphy", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_2.setBounds(329, 363, 119, 32);
		frame.getContentPane().add(btnNewButton_2);
		Image img= new ImageIcon(this.getClass().getResource("2.png")).getImage();
		Image img1= new ImageIcon(this.getClass().getResource("2.png")).getImage();
		
		JLabel lblNewLabel_3 = new JLabel("WELCOME TO COLLISION()");
		lblNewLabel_3.setFont(new Font("Curlz MT", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(32, 16, 416, 36);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		Image img2= new ImageIcon(this.getClass().getResource("2.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img2));
		lblNewLabel_1.setBounds(0, 8, 466, 44);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_4 = new JButton("SURVIVAL MODE");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerSingle ps= new PlayerSingle();
				ps.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_4.setFont(new Font("Lucida Calligraphy", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_4.setBackground(new Color(0, 0, 0));
		btnNewButton_4.setForeground(new Color(153, 51, 102));
		btnNewButton_4.setBounds(114, 156, 224, 37);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("VERSUS MODE");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MultiPlayer mp= new MultiPlayer();
				mp.setVisible(true);
				frame.dispose();

			}
		});
		btnNewButton_5.setBackground(new Color(51, 51, 51));
		btnNewButton_5.setForeground(new Color(105, 105, 105));
		btnNewButton_5.setFont(new Font("Lucida Calligraphy", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_5.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
			}
		});
		btnNewButton_5.setBounds(114, 204, 224, 37);
		frame.getContentPane().add(btnNewButton_5);
		
		JLabel lblNewLabel = new JLabel("CHOOSE: ");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("Curlz MT", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setBounds(114, 91, 178, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		Image img3= new ImageIcon(this.getClass().getResource("2.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(img3));
		lblNewLabel_2.setBounds(0, 48, 438, 201);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		Image img4= new ImageIcon(this.getClass().getResource("2.png")).getImage();
		lblNewLabel_4.setIcon(new ImageIcon(img4));
		lblNewLabel_4.setBounds(0, 241, 448, 172);
		frame.getContentPane().add(lblNewLabel_4);
		
		
		
		
		
	}
}
