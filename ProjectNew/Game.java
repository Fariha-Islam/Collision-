import javax.swing.*;

public class Game  {
	
	public static void GAMESTART(){
		JFrame window =new JFrame("getFAT()");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setContentPane(new GamePanel());
		
		window.pack();
		window.setVisible(true);
	}
	
	public static void GAMESTART2(){
		JFrame window =new JFrame("getFAT()");
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setContentPane(new GamePanel2());
		
		window.pack();
		window.setVisible(true);
	}

}
