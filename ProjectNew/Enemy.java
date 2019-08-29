

import java.awt.*;


public class Enemy {

	private double x;
	private double y;
	private int r;
	private int s1=20;
	private int s2=20;
	private double dx;
	private double dy;
	private double speed;
	private double rad;
	private int health;
	private int rank;
	private int type;
	private boolean ready;
	private boolean dead;
	private Color c1;
	
	public Enemy(int type, int rank){
		this.type= type;
		this.rank=rank;
		
		if (type==1){	
			c1=Color.WHITE; 

			if( rank ==1){
				speed=2;
			    s1=20;
			    s2=20;
				health=1;
			}
		}
		
		if (type==2){
			c1=Color.ORANGE;
			if( rank ==2){
				speed=3;
				s1=30;
				s2=30;
				health=1;
			}
		}
		
		if (type==3){
			c1=Color.MAGENTA;
			if( rank ==3){
				speed=6;
				s1=50;
				s2=50;
				health=1;
			}
		}
		if (type==4){
			c1=Color.CYAN;
			if( rank ==4){
				speed=6;
				s1=110;
				s2=110;
				health=1;
			}
		}
		
		x= Math.random()*GamePanel.WIDTH/2 + GamePanel.WIDTH/4;
		y=-r;
		
		double angle= Math.random() * 140 +20;
		rad=Math.toRadians(angle);
		dx=Math.cos(rad)* speed;
		dy= Math.sin(rad)*speed;
		
		ready= false;
		dead= false;		
	}
	
	public double getx(){
		return x;
	}
	public double gety(){
		return y;
	}
	
	public int getr(){
		return r;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public int gets1(){
		return s1;
	}
	public void setS1(int increase){
		if(s1<80)
		s1=s1+increase;
	}
	
	public void setS2(int increase){
		if(s2<80)
		s2=s2+increase;
	}
	
	
	
	public void hit () {
		health--;
		if(health<=0){
			dead=true;
		}
	}
	
	public void update(){
		
		x+=dx;
		y+=dy;
		
	if(!ready){
		if(x>r && x<GamePanel.WIDTH-r && y>r && y<GamePanel.HEIGHT-r){
			ready= true;
		}
	}
	
	if(x<r && dx<0){
		dx=-dx;
	}
	
	if(y<r && dy<0){
		dy=-dy;
	}
	
	if(x>GamePanel.WIDTH-r && dx>0){
		dx=-dx;
	}
	if(y>GamePanel.HEIGHT-r && dy>0){
		dy=-dy;
	}
		
		
	}
	
	public void draw(Graphics2D g){
		
		g.setColor(c1);
        g.fillOval((int)x-r, (int)y-r, s1, s2);
    	g.setStroke(new BasicStroke(4));
		g.setColor(c1.darker());
        g.drawOval((int)x-r, (int)y-r, s1, s2);
    	g.setStroke(new BasicStroke(2));

		
	}
	
	 public Rectangle getBounds(){
		 return new Rectangle((int)(x-r),(int) (y-r), s1, s2);
			
		}
	
}
