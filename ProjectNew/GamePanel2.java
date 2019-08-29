
import javax.swing.*;
import javax.swing.Timer;
import java.util.Scanner.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.awt.event.*;
import java.util.*;


public class GamePanel2 extends JPanel implements Runnable,KeyListener {
	
	
	private static final int KeyCode = 0;
	public static int WIDTH=1200;
	public static int HEIGHT=700;
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS=30;
	private double avgFPS;
	private int myTimerDelay;
	private Timer myTimer;
	private int time=0;
	
	public static SinglePlayer player;
	public static ArrayList<EnemySingle> enemies;
	public static int highScore;
	
	public static ArrayList<Food2> food;
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
	
	
	public GamePanel2(){
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		myTimerDelay=1000;
		myTimer=new Timer(myTimerDelay, GameTimer);
		myTimer.start();
	}
	
	ActionListener GameTimer= new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent theEvent){
			time++;
		}
	};
	
	
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
		
		player =new SinglePlayer();
		
		food =new ArrayList<Food2>();
		enemies= new ArrayList<EnemySingle>();
		
		phaseStartTimer=0;
		phaseStartTimerDiff=0;
		phaseStart=true;
		phaseNumber=0;
		
		
		
		fStarrtTimer=0;
		fStartTimerDif=0;
		foodStart=true;
		foodNo=0;
		
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long targetTime=1000/FPS;
		int totalTime=0;
		int frameCount=0;
		int maxFrameCount=30;
		
		
		//Game loop
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
		
		g.setColor(new Color(0,100,255));
		//g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
		g.setFont(new Font ("Century Gothic",Font.BOLD,30));
		String s= "GAME OVER!!";
		g.drawString(s, WIDTH/3, HEIGHT/3);
		g.setFont(new Font ("Century Gothic",Font.BOLD,30));
		s="Survival Time: "+time+" seconds";
		g.drawString(s, WIDTH/2, HEIGHT/2);
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
		g.drawString("HIGH SCORE "+highScore, 20, 20);
		player.draw(g);
		
	
		
		
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Century Gothic",Font.BOLD,15));
		g.setPaint(Color.white);
		g.drawString(" " +PlayerSingle.s+ " SCORE "+player.getpoints(),WIDTH-150, 10);
		
		for(int i=0;i<food.size();i++){
			food.get(i).draw(g);
		}
		
		for(int i=0;i<enemies.size();i++){
			enemies.get(i).draw(g);
		}
		
		if(phaseStartTimer!=0){
			g.setFont(new Font("Century Gothic",Font.BOLD,30));
			String s= "SURVIVE";
			int length= (int) g.getFontMetrics().getStringBounds(s,g).getWidth();
			int alpha= (int) (255*Math.sin(3.14*phaseStartTimerDiff/phaseDelay));
			if (alpha>255){
				alpha=255;
			}
			g.setColor(new Color(255,255,255, alpha));
			g.drawString(s,WIDTH/2-length/2,HEIGHT/2);
			
		}
		
		
	}

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
		
		if(phaseStart && enemies.size()<2){
			createNewEnemies();
		}
		
		//player food colision
		int px=player.getx();
		int py=player.gety();
		int pr=player.getr();
		for(int i=0;i<food.size();i++){
			
			Food2 f=food.get(i);
			double fx=f.getx();
			double fy=f.gety();
			double fr=f.getr();
			
			double dx=px-fx;
			double dy=py-fy;
			double dist=Math.sqrt(dx*dx+dy*dy);
			
			
			//player food collison
			if(player.getBounds().intersects(food.get(i).getBounds())){
				player.earnpoints();
				food.remove(i);
				i--;
				break;
			}		
				
		}
		
			//player enemy collison
		for(int i=0;i<enemies.size();i++){
				if(player.getBounds().intersects(enemies.get(i).getBounds())){
					if(player.getS1()>enemies.get(i).gets1()){
						enemies.remove(i);
						player.earnpoints();
					}
					else if(player.getS1()<enemies.get(i).gets1()){
						running=false;
					
					}
				}				
		}
			
		//new food
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
	
	for(int i=0;i<food.size();i++){
		food.get(i).update();
	}
	
	for(int i=0;i<enemies.size();i++){
		enemies.get(i).update();
	}
	
	
	try{
		Scanner in= new Scanner(new File("Scores.txt"));
		while(in.hasNext()){
			
			highScore=in.nextInt();
		}
		
		}
	
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	
	
	
	try {
		PrintWriter write= new PrintWriter("Scores.txt");
		if(SinglePlayer.getpoints()>highScore){
			highScore=SinglePlayer.getpoints();
			write.print(highScore);
		}
		write.close();
	} 
	
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	}
	
		
	
	private void createNewEnemies(){
		enemies.clear();
		Enemy e;
		if(phaseNumber==1){
			for(int i=0;i<4;i++){
				enemies.add(new EnemySingle(1,1));
				enemies.add(new EnemySingle(2,2));
				enemies.add(new EnemySingle(3,3));
				enemies.add(new EnemySingle(4,4));
				enemies.add(new EnemySingle(4,4));


			}
		}
		
	}
		
		
		
		
	


	private void createNewFood() {
		// TODO Auto-generated method stub
		food.clear();
		if(foodNo==1){
			for(int i=0;i<30;i++){
				food.add(new Food2(1));
				food.add(new Food2(2));
				food.add(new Food2(3));
			}
			if(foodNo==2){
				for(int i=0;i<10;i++){
					food.add(new Food2(1));
					food.add(new Food2(2));
					food.add(new Food2(3));
				}
				if(foodNo==3){
					for(int i=0;i<20;i++){
						food.add(new Food2(1));
						food.add(new Food2(2));
						food.add(new Food2(3));
						
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
	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
