import java.awt.*;

public class Food {
	
	private double x;
	private double y;
	private int r;
	
	private int health;
	private Color c1;
	private int type;
	
	private boolean ready;
	private boolean dead;
	
	public Food(int type){
		this.type=type;
		if(type==1){
			c1=Color.MAGENTA;
		}
		if(type==2){
			c1=Color.darkGray;
		}
		if(type==3){
			c1=Color.PINK;
		}
		
		x=Math.random()*GamePanel.WIDTH;
		y=Math.random()*GamePanel.HEIGHT;
		
		ready =false;
		dead=false;
		
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
	 
	 public void hit(){
		 health--;
		 if(health<=0)
		 dead=true;
	 }
	 
	 public boolean isDead(){
		 return dead;
	 }
	 
	 public void update(){
		 if(!ready){
			 if(x>r&& x<GamePanel.WIDTH-r&& y>r&&y<GamePanel.HEIGHT-r){
				 ready=true;
			 }
		 }
		 
	 }
	 
	 public void draw(Graphics2D g){
		 g.setColor(c1);
		 g.fillOval((int)(x-r),(int) (y-r), 5, 5);
		 g.drawOval((int)(x-r),(int) (y-r), 5, 5);
		 g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		 
		 
		 
	 }
	 public Rectangle getBounds(){
		 return new Rectangle((int)(x-r),(int) (y-r), 5, 5);
			
		}
		

}
