
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;


public class GamePanel extends JPanel implements Runnable,KeyListener {
	
	
	//////V A R I A B L E S 
	
	private static final int KeyCode = 0;
	public static int WIDTH=1350;
	public static int HEIGHT=700;
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS=30;
	private double avgFPS;
	
	public static Player player;
	public static Player2 player2;
	public static MultiPlayer m=new MultiPlayer();
	public static ArrayList<Enemy> enemies;
	public String name1=m.getName1();
	public String name2=m.getName2();
	
	public static ArrayList<Food> food;
	private long fStarrtTimer;
	private long fStartTimerDif;
	private int foodNo;
	private boolean foodStart;
	private int fDelay=1000;
	
	private long phaseStartTimer;
	private long phaseStartTimerDiff;
	private int phaseNumber;
	private boolean phaseStart;
	private int phaseDelay=1500;
	
	String s=null;
	
	           /////G A M  E W I N D O W 
	public GamePanel(){
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	
			//// T H R E A D SS T A R T
	
	public void addNotify(){
		super.addNotify();
		if(thread==null){
			thread=new Thread(this);
			thread.start();
		}
		addKeyListener(this);
	}
			
	public void run(){
		
		running = true;
		image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g=(Graphics2D) image.getGraphics();
		
		player =new Player();
		player2 =new Player2();
		food =new ArrayList<Food>();
		enemies= new ArrayList<Enemy>();
		
					///// E N E M Y  T I M E R S ////////
		phaseStartTimer=0;
		phaseStartTimerDiff=0;
		phaseStart=true;
		phaseNumber=0;
		
				////F O O D  T I M E R S ///////
		
		fStarrtTimer=0;
		fStartTimerDif=0;
		foodStart=true;
		foodNo=0;
		
				//////F P S  V A R I A B L E S///////
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long targetTime=1000/FPS;
		int totalTime=0;
		int frameCount=0;
		int maxFrameCount=30;
		
		
						//////////G A M E   L O O P//////////
		while(running){
			startTime = System.nanoTime();
			
			gameUpdate();
			gameRender();
			gameDraw();
			
			URDTimeMillis=(System.nanoTime()-startTime)/1000000;
			waitTime=targetTime-URDTimeMillis;
			
			try{
				Thread.sleep(waitTime);
			}
			catch(Exception e){
			
				
			}
			totalTime+=System.nanoTime()-startTime;
			frameCount++;
			if(frameCount==maxFrameCount){
				avgFPS= (1000.0/((totalTime/frameCount)/1000000));
				frameCount=0;
				totalTime=0;
			}
		}
		
		
		
		if(player.gethit()==true){
			g.setColor(new Color(0,100,255));
			//g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setBackground(Color.BLACK);
			g.setColor(Color.WHITE);
			g.setFont(new Font ("Century Gothic",Font.BOLD,30));
			s= "G A M E  O V E R !!";
			g.drawString(s, WIDTH/2, HEIGHT/2);
			g.setFont(new Font ("Century Gothic",Font.BOLD,30));
			s=name2+" won";
			g.drawString(s, WIDTH-500, HEIGHT-500);
			
			
			
		
		}
		
		else if(player2.gethit()==true){
			g.setColor(new Color(0,100,255));
			//g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setBackground(Color.BLACK);
			g.setColor(Color.WHITE);
			g.setFont(new Font ("Century Gothic",Font.BOLD,30));
			s= "G A M E  O V E R !!";
			g.drawString(s, WIDTH/2, HEIGHT/2);
			g.setFont(new Font ("Century Gothic",Font.BOLD,30));
			s=name1+" won";
			g.drawString(s, WIDTH-500, HEIGHT-500);
		}
		
	
		
		gameDraw();
		
	}

	private void gameDraw() {
		// TODO Auto-generated method stub
		Graphics2D g2=(Graphics2D) this.getGraphics();
		g2.drawImage(image,0,0,null);
		g2.dispose();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
	}

	private void gameRender() {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.blue);
		g.drawString("FPS: "+avgFPS, 10, 10);
		player.draw(g);
		
	
		player2.draw(g);
		
				////////// S C O R E    B O A R D///////////
		g.setColor(Color.BLACK);
		g.setFont(new Font("Century Gothic",Font.BOLD,15));
		g.setPaint(Color.white);
		g.drawString(name1+" " + " SCORE "+player.getpoints(),WIDTH-150, 10);
		g.drawString(name2+" " + " SCORE "+player2.getpoints(),WIDTH-150, 30);
		
		
			//////// F O O D   D R A W    L O O P/////
		for(int i=0;i<food.size();i++){
			food.get(i).draw(g);
		}
		
		
			////////E N E M Y   D R A W    L O O P/////
		for(int i=0;i<enemies.size();i++){
			enemies.get(i).draw(g);
		}
		
		if(phaseStartTimer!=0){
			g.setFont(new Font("Century Gothic",Font.BOLD,40));
			String s= "S T A R T ";
			int length= (int) g.getFontMetrics().getStringBounds(s,g).getWidth();
			int alpha= (int) (255*Math.sin(3.14*phaseStartTimerDiff/phaseDelay));
			if (alpha>255){
				alpha=255;
			}
			g.setColor(new Color(255,255,255, alpha));
			g.drawString(s,WIDTH/2-length/2,HEIGHT/2);
			
		}
	
		
	}
	
			////////  U P D A T E  C L A S S //////////

	private void gameUpdate() {
		// TODO Auto-generated method stub
		
		if(phaseStartTimer==0 && enemies.size()==0){
			phaseNumber++;
			phaseStart=false;
			phaseStartTimer= System.nanoTime();	
		}
		
		else{
			phaseStartTimerDiff= (System.nanoTime()-phaseStartTimer)/1000000;
			if(phaseStartTimerDiff>phaseDelay){
				phaseStart=true;
				phaseStartTimer=0;
				phaseStartTimerDiff=0;
				
			}
		}
		////// N E W  E N E M Y  C R E A T I O N///////////
		if(phaseStart && enemies.size()<5){
			createNewEnemies();
		}
		
		  
		int px=player.getx();
		int py=player.gety();
		int pr=player.getr();
		for(int i=0;i<food.size();i++){
			
			Food f=food.get(i);
			double fx=f.getx();
			double fy=f.gety();
			double fr=f.getr();
			
			double dx=px-fx;
			double dy=py-fy;
			double dist=Math.sqrt(dx*dx+dy*dy);
			
			
			/////////P L A Y E R    F O O D   C O L L I S I O N /////////
			if(player.getBounds().intersects(food.get(i).getBounds())){
				player.earnpoints();
				food.remove(i);
				i--;
				break;
			}
				
	/////////P L A Y E R 2    F O O D   C O L L I S I O N /////////

			if(player2.getBounds().intersects(food.get(i).getBounds())){
				player2.earnpoints();
				food.remove(i);
				i--;
				break;		
		}
		}
		
			/////////P L A Y E R     E N E M Y    C O L L I S I O N /////////
		for(int i=0;i<enemies.size();i++){
				if(player.getBounds().intersects(enemies.get(i).getBounds())){
					if(player.getS1()>enemies.get(i).gets1()){
						enemies.remove(i);
						player.earnpoints();
					}
					else if(player.getS1()<enemies.get(i).gets1()&&player.getpoints()>=5){
						running=false;
						player.setihit(true);
					
					}
				}
				else if(player2.getBounds().intersects(enemies.get(i).getBounds())){
					if(player2.getS1()>enemies.get(i).gets1()){
						enemies.remove(i);
						player2.earnpoints();
					}
					else if(player2.getS1()<enemies.get(i).gets1()&&player2.getpoints()>=5){
						running =false;
						player2.setihit(true);
						
					}
				}
					
				
		}
		
			
			
			
		///////P L A Y E R   P L A Y E R 2    C O L L I S I O N//////////
				if(player.getBounds().intersects(player2.getBounds())){
					if(player.getS1()>=50 || player2.getS1()>=50){
						if(player.getpoints()>player2.getpoints()){
							if(player2.getS1()>=30 && player2.getpoints()>=0){
								player2.setS1(10);
								player2.setS2(10);
								player2.setPoint(10);	
								player2.setx(GamePanel.WIDTH-50);
								player2.sety(GamePanel.HEIGHT-50);
							}
						}
						
						else if (player.getpoints()<player2.getpoints()){
							if(player.getS1()>=30 && player.getpoints()>=0){
								player.setS1(10);
								player.setS2(10);
								player.setPoint(10);	
								player.setx(GamePanel.WIDTH-50);
								player.sety(GamePanel.HEIGHT);}}}
					}
				
			
		
		
		
		///////F O O D    C R E A T I O N////////////
		if(fStartTimerDif==0&& food.size()==0){
			foodNo++;
			foodStart=false;
			fStartTimerDif=System.nanoTime();
		}
		else{
			fStartTimerDif=(System.nanoTime()-fStarrtTimer)/1000000;
			if(fStartTimerDif>fDelay){
				foodStart=true;
				fStarrtTimer=0;
				fStartTimerDif=0;
			}
		}
		
		//FOOd
		if(foodStart && food.size()<20){
			createNewFood();
		}
		
	
	player.udate();
	player2.udate();
	
	for(int i=0;i<food.size();i++){
		food.get(i).update();
	}
	
	for(int i=0;i<enemies.size();i++){
		enemies.get(i).update();
	}
	
	
	
	}
		
	
	private void createNewEnemies(){
		enemies.clear();
		Enemy e;
		if(phaseNumber==1){
			for(int i=0;i<3;i++){
				enemies.add(new Enemy(1,1));
				enemies.add(new Enemy (2,2));
				enemies.add(new Enemy(3,3));
				enemies.add(new Enemy(4,4));
				
			}
		}
	}


	private void createNewFood() {
		// TODO Auto-generated method stub
		food.clear();
		if(foodNo==1){
			for(int i=0;i<30;i++){
				food.add(new Food(1));
				food.add(new Food(2));
				food.add(new Food(3));
			}
			if(foodNo==2){
				for(int i=0;i<10;i++){
					food.add(new Food(1));
					food.add(new Food(2));
					food.add(new Food(3));
				}
				if(foodNo==3){
					for(int i=0;i<20;i++){
						food.add(new Food(1));
						food.add(new Food(2));
						food.add(new Food(3));
						
					}
		}}}
	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		int keyCode=k.getKeyCode();
		if(keyCode==KeyEvent.VK_LEFT){
			player.setLeft(true);
		}
		if(keyCode==KeyEvent.VK_RIGHT){
			player.setRight(true);
		}
		if(keyCode==KeyEvent.VK_UP){
			player.setUp(true);
		}
		if(keyCode==KeyEvent.VK_DOWN){
			player.setDown(true);
		}
		
		
		if(keyCode==KeyEvent.VK_S){
			player2.setDown(true);
		}
		if(keyCode==KeyEvent.VK_W){
			player2.setUp(true);
		}
		if(keyCode==KeyEvent.VK_A){
			player2.setLeft(true);
		}
		if(keyCode==KeyEvent.VK_D){
			player2.setRight(true);
		}
		
	}

	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		int keyCode=k.getKeyCode();
		if(keyCode==KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		if(keyCode==KeyEvent.VK_RIGHT){
			player.setRight(false);
		}
		if(keyCode==KeyEvent.VK_UP){
			player.setUp(false);
		}
		if(keyCode==KeyEvent.VK_DOWN){
			player.setDown(false);
		}
		
		
		if(keyCode==KeyEvent.VK_S){
			player2.setDown(false);
		}
		if(keyCode==KeyEvent.VK_W){
			player2.setUp(false);
		}
		if(keyCode==KeyEvent.VK_A){
			player2.setLeft(false);
		}
		if(keyCode==KeyEvent.VK_D){
			player2.setRight(false);
		}
		
		
	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
