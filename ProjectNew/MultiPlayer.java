import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.print.DocFlavor.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class MultiPlayer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public static String name;
	public static String name2;

	/**
	 * Launch the application.
	 */
	public static void MultiPlayer(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultiPlayer frame = new MultiPlayer();
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
	public MultiPlayer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 428, 398);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterYourName = new JLabel("Enter your name (Player 1):");
		lblEnterYourName.setForeground(new Color(153, 51, 0));
		lblEnterYourName.setFont(new Font("Cooper Std Black", Font.BOLD | Font.ITALIC, 19));
		lblEnterYourName.setBounds(10, 52, 285, 29);
		contentPane.add(lblEnterYourName);
		
		textField = new JTextField();
		
		textField.setBackground(new Color(211, 211, 211));
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 20));
		textField.setBounds(10, 92, 317, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				    
				   name=textField.getText();
				   }
				  });
		
		JLabel lblEnterYourName_1 = new JLabel("Enter your name (Player 2):");
		lblEnterYourName_1.setForeground(new Color(153, 51, 0));
		lblEnterYourName_1.setFont(new Font("Cooper Std Black", Font.BOLD | Font.ITALIC, 19));
		lblEnterYourName_1.setBounds(10, 144, 285, 34);
		contentPane.add(lblEnterYourName_1);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(211, 211, 211));
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 20));
		textField_1.setBounds(10, 189, 317, 34);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				    
				   name2=textField_1.getText();
				   }
				  });
		
		
		JButton btnNewButton_1 = new JButton("FIGHT!! ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Game.GAMESTART();
			}
		});
		btnNewButton_1.setForeground(new Color(102, 0, 0));
		btnNewButton_1.setBackground(new Color(211, 211, 211));
		btnNewButton_1.setFont(new Font("Cooper Std Black", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_1.setBounds(253, 313, 159, 46);
		contentPane.add(btnNewButton_1);
		Image img= new ImageIcon(this.getClass().getResource("i4.jpg")).getImage();
		Image img4= new ImageIcon(this.getClass().getResource("i9.jpg")).getImage();
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setBackground(SystemColor.scrollbar);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameWindow fw=new FrameWindow();
				fw.frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Cooper Std Black", Font.BOLD | Font.ITALIC, 18));
		btnNewButton.setForeground(new Color(102, 0, 0));
		btnNewButton.setBounds(0, 313, 126, 46);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		Image img5= new ImageIcon(this.getClass().getResource("i9.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img5));
		lblNewLabel.setBounds(0, 0, 402, 359);
		contentPane.add(lblNewLabel);
	}
	 public String getName1(){
		return name;
	}
	
	 public String getName2(){
		return name2;
	}
}
