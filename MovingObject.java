import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
public class MovingObject{
	private int x;
	private int y;
	private int speed;
	Image pic;
	public MovingObject(int x, int y, Image pic, int speed){
		this.x = x;
		this.y = y;
		this.pic = pic;
		this.speed=speed;
	}
	public void moveHorizontal(){
		x++;
	}
	public void moveVertical(){
		y++;
	}
	public Rectangle2D getRect(){
		Rectangle2D outline = new Rectangle2D(x,y,pic.getWidth(), pic.getHeight());
		return outline;
	}
	public Image getImage(){
		return pic;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void move(){
		x+=speed;
		if(x>800)
			x=0;
		if(x<-100)
			x=780;
	}
	public void addY(int add){
		y+=add;
	}
	public void setX(int set){
		x = set;
	}
	public void setY(int set){
		y = set;
	}

	public void draw(GraphicsContext gc){
		gc.drawImage(getImage(), x, y);
	}
}

