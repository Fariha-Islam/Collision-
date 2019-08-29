import java.awt.BorderLayout;
import java.awt.EventQueue;
import sun.audio.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.*;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.TargetDataLine;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class PlayerSingle extends JFrame  {

	private JPanel contentPane;
	private JTextField textField;
	public static String s;

	/**
	 * Launch the application.
	 */
	public static void PlayerSIngle(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerSingle frame = new PlayerSingle();
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
	public PlayerSingle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 472, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter your name: ");
		lblNewLabel.setForeground(new Color(153, 0, 0));
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 23));
		lblNewLabel.setBounds(10, 52, 284, 52);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 16));
		textField.setBackground(SystemColor.scrollbar);
		textField.setBounds(10, 134, 307, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				    
				   s=textField.getText();
				   }
				  });
		
		JLabel lblNewLabel_1 = new JLabel("ALL THE BEST !!");
		lblNewLabel_1.setForeground(new Color(153, 0, 0));
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(245, 215, 201, 52);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("START");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Game.GAMESTART2();
			}
		});
		//btnNewButton.addActionListener(new ActionListener() {
			//public void actionPerformed(java.awt.event.ActionEvent e){
				//InputStream in;
				//try{
					//Game.GAMESTART2();
					//in= new FileInputStream((("D:\\Semester 3\\ProjectNew\\music.wav")));
					//AudioInputStream audio= new AudioInputStream((TargetDataLine) in);
		
				//Game.GAMESTART2();
			//}
				//catch(Exception e1){
					//JOptionPane.showMessageDialog(null, e1);
				//}

				
			//}
		//});
		btnNewButton.setForeground(new Color(153, 0, 0));
		btnNewButton.setBackground(SystemColor.scrollbar);
		
			
		btnNewButton.setFont(new Font("Snap ITC", Font.PLAIN, 18));
		btnNewButton.setBounds(331, 280, 125, 44);
		contentPane.add(btnNewButton);
		Image img4= new ImageIcon(this.getClass().getResource("i11.jpg")).getImage();
		
		JButton btnNewButton_2 = new JButton("BACK");
		btnNewButton_2.setBackground(SystemColor.scrollbar);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameWindow fw=new FrameWindow();
				fw.frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_2.setForeground(new Color(153, 0, 0));
		btnNewButton_2.setBounds(0, 280, 139, 44);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("");
		Image img5= new ImageIcon(this.getClass().getResource("i11.jpg")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(img5));
		lblNewLabel_2.setBounds(0, 0, 456, 422);
		contentPane.add(lblNewLabel_2);
		
	}
	public String getName(){
		return s;
	}
}
