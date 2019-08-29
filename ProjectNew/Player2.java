
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class Player2 {
	
	private int x;
	private int y;
	private int r;
	private int s1=20;
	private int s2=20;
	public boolean hit=false;
	
	private Color c1;
	
	private int dx;
	private int dy; 
	private int speed;
	private int points=0;
	
	private boolean left;
	private boolean  right;
	private boolean  up;
	private boolean  down;
	
    static MultiPlayer p=new MultiPlayer();
	
	public static String name=p.getName2();
	
	public Player2(){
		
		x=GamePanel.WIDTH/4;
		y=GamePanel.HEIGHT/4;
		r=5;
		
		dx=0;
		dy=0;
		speed=7;
		
		c1=Color.RED;
		
		
	}
	public void setx(int x){
		this.x=x;
	}
	public void sety(int y){
		this.x=y;
	}
	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}
	
	public int getr(){
		return r;
	}
	
	public int getpoints(){
		return points;
	}
	
	public int getS1(){
		return s1;
	}
	
	public int getS2(){
		return s2;
	}
	
	public boolean gethit(){
		return hit;
	}
	
	public void setihit(boolean hit){
		this.hit=hit;
	}
	
	
	public void setPoint(int reduce){
		points=points-reduce;
	}
	
	public void setS1(int reduce){
		s1=s1-reduce;
	}
	
	public void setS2(int reduce){
		s2=s2-reduce;
	}
	
	
	public void setLeft(boolean b){
		left =b;
	}
	public void setRight(boolean b){
		right =b;
	}
	
	public void setUp(boolean b){
		up =b;
	}
	
	public void setDown(boolean b){
		down =b;
	}
	public void earnpoints(){
		points++;
		if(s1<=100 && s2<=100){
			s1+=1;
			s2+=1;
		}
		//speed=(int) (speed);
		
		
	}
	
	
	public void udate(){
		if(left)
			dx=-speed;
		if(right)
			dx=speed;
		if(up)
			dy=-speed;
		if(down)
			dy=speed;
		
		x+=dx;
		y+=dy;
		
		dx=0;
		dy=0;
		
		if(x<r)x=r;
		if(y<r)y=r;
		if(x>GamePanel.WIDTH-r)x=GamePanel.WIDTH-r;
		if(y>GamePanel.HEIGHT-r)y=GamePanel.HEIGHT-r;
		
		
		
	}
	
	public void draw(Graphics2D g){
		g.setColor(c1);
		g.fillOval(x-r, y-r, s1, s2);
		g.setStroke(new BasicStroke(3));
		g.setColor(c1.darker());
		g.drawOval(x-r, y-r, s1, s2);
		g.drawString(name+" "+points, x-r,y-r );
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(points<5){
			g.setColor(c1);
			g.fillOval(x-r, y-r, s1, s2);
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.green);
			g.drawOval(x-r, y-r, s1, s2);
			g.drawString(name+" "+points, x-r, y-r);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		}
		
	}
	 public Rectangle getBounds(){
		 return new Rectangle(x-r, y-r, s1, s2);
	 }

}
