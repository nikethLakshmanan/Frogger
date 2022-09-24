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
import java.util.*;
//original-516
//new-800
	public class frogger extends Application implements EventHandler<InputEvent>{
		GraphicsContext gc;
		AnimateObjects animate;
		int x = 390;
		int y= 750;
		int scoreLocation = 750;
		Image frog;
		Canvas canvas;
		Image background;
		ArrayList<MovingObject> cars = new ArrayList<MovingObject>();
		ArrayList<MovingObject> waters = new ArrayList<MovingObject>();
		ArrayList<Rectangle2D> lilyBounds = new ArrayList<Rectangle2D>();
		ArrayList<Rectangle2D> lilypad = new ArrayList<Rectangle2D>();
		ArrayList<MovingObject> winFrogs = new ArrayList<MovingObject>();
		ArrayList<Rectangle2D> alreadyGot = new ArrayList<Rectangle2D>();
		int lives = 3;
		Rectangle2D waterOutline;
		URL resource = getClass().getResource("hop.wav");
		AudioClip clip = new AudioClip(resource.toString());
		URL resource2 = getClass().getResource("waterDie.wav");
		AudioClip wDie = new AudioClip(resource2.toString());
		URL resource3 = getClass().getResource("truckDie.wav");
		AudioClip tDie = new AudioClip(resource3.toString());
		URL resource4 = getClass().getResource("1up.wav");
		AudioClip power = new AudioClip(resource4.toString());
		URL resource5 = getClass().getResource("winOne.wav");
		AudioClip anotherFrog = new AudioClip(resource5.toString());
		int totalScore=0;
		boolean gotExtra = false;
		boolean winGame = false;
		int winCounter = 0;
		MovingObject OneUp = new MovingObject(150,270,new Image("1up.png"),3);

		boolean winAll;
		public static void main(String[] args){
			launch();
		}
		public void start(Stage stage){
			stage.setTitle("Frogger");

			Group root = new Group();
			canvas = new Canvas(800, 781);
			root.getChildren().add(canvas);
			Scene scene = new Scene(root);
			scene.addEventHandler(KeyEvent.KEY_PRESSED,this);
			stage.setScene(scene);
		    gc = canvas.getGraphicsContext2D();
		    background = new Image ("fBackground.png");
		    waterOutline = new Rectangle2D(0,90,800,290);
			frog = new Image("frog.png");
			cars.add(new MovingObject(500,445,new Image("car1.png"),5));
			cars.add(new MovingObject(700,445,new Image("car1.png"),5));
			cars.add(new MovingObject(600,445,new Image("car1.png"),5));
			cars.add(new MovingObject(300,445,new Image("car1.png"),5));
//
			cars.add(new MovingObject(700,500,new Image("car2.png"),-2));
			cars.add(new MovingObject(500,500,new Image("car2.png"),-2));
			cars.add(new MovingObject(100,500,new Image("car2.png"),-2));
//
			cars.add(new MovingObject(600,610,new Image("car3.png"),-2));
			cars.add(new MovingObject(750,610,new Image("car3.png"),-2));
			cars.add(new MovingObject(300,610,new Image("car3.png"),-2));
//
			cars.add(new MovingObject(750,555, new Image("car4.png"),1));
			cars.add(new MovingObject(500,555, new Image("car4.png"),1));
			cars.add(new MovingObject(150,555, new Image("car4.png"),1));
			cars.add(new MovingObject(600,675, new Image("truck.png"),-3));
			cars.add(new MovingObject(100,675, new Image("truck.png"),-3));
//
			waters.add(new MovingObject(100, 330, new Image("turtles.png"),-4));
			waters.add(new MovingObject(150, 330, new Image("turtles.png"),-4));
			waters.add(new MovingObject(200, 330, new Image("turtles.png"),-4));
			waters.add(new MovingObject(400, 330, new Image("turtles.png"),-4));
			waters.add(new MovingObject(450, 330, new Image("turtles.png"),-4));
			waters.add(new MovingObject(500, 330, new Image("turtles.png"),-4));
//
			waters.add(new MovingObject(150, 270, new Image("log.png"),3));
			waters.add(new MovingObject(200, 270, new Image("log.png"),3));
			waters.add(new MovingObject(250, 270, new Image("log.png"),3));
			waters.add(new MovingObject(500, 270, new Image("log.png"),3));
			waters.add(new MovingObject(550, 270, new Image("log.png"),3));
			waters.add(new MovingObject(600, 270, new Image("log.png"),3));
//330,390
			waters.add(new MovingObject(50, 210, new Image("turtles.png"),-3));
			waters.add(new MovingObject(100, 210, new Image("turtles.png"),-3));
			waters.add(new MovingObject(150, 210, new Image("turtles.png"),-3));
			waters.add(new MovingObject(550, 210, new Image("turtles.png"),-3));
			waters.add(new MovingObject(600, 210, new Image("turtles.png"),-3));
			waters.add(new MovingObject(650, 210, new Image("turtles.png"),-3));
//
			waters.add(new MovingObject(150, 150, new Image("log.png"),1));
			waters.add(new MovingObject(200, 150, new Image("log.png"),1));
			waters.add(new MovingObject(250, 150, new Image("log.png"),1));
			waters.add(new MovingObject(300, 150, new Image("log.png"),1));
			waters.add(new MovingObject(500, 150, new Image("log.png"),1));
			waters.add(new MovingObject(550, 150, new Image("log.png"),1));
			waters.add(new MovingObject(600, 150, new Image("log.png"),1));
			waters.add(new MovingObject(650, 150, new Image("log.png"),1));
			waters.add(new MovingObject(700, 150, new Image("log.png"),1));


			lilyBounds.add(new Rectangle2D(0,0,20,92));
			lilyBounds.add(new Rectangle2D(94,0,100,92));
			lilyBounds.add(new Rectangle2D(265,0,100,92));
			lilyBounds.add(new Rectangle2D(438,0,100,92));
			lilyBounds.add(new Rectangle2D(610,0,100,92));
			lilyBounds.add(new Rectangle2D(780,0,20,92));

			lilypad.add(new Rectangle2D(42,53,30,30));
			lilypad.add(new Rectangle2D(215,53,30,30));
			lilypad.add(new Rectangle2D(386,53,30,30));
			lilypad.add(new Rectangle2D(557,53,30,30));
			lilypad.add(new Rectangle2D(729,53,30,30));

			animate = new AnimateObjects();
			animate.start();
			stage.show();
		}
		public class AnimateObjects extends AnimationTimer{
			public void handle(long now){
				if(lives>0 && winGame ==false){
					gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
					gc.drawImage(background, 0, 0);
					gc.setFill( Color.RED); //Fills the text in yellow
					gc.setStroke( Color.RED ); //Changes the outline the black
					gc.setLineWidth(1); //How big the black lines will be
					Font font = Font.font("Courier New", FontWeight.NORMAL, 20);
					gc.setFont(font);
					gc.fillText("Score: "+totalScore, 650,15);
					gc.strokeText("Score: "+totalScore, 650,15);
					//System.out.println("("+x+","+y+")");
					Rectangle2D frogOutline = new Rectangle2D(x,y,frog.getWidth(), frog.getHeight());
					gc.drawImage(frog, x, y);
					for(int i=0; i<cars.size(); i++){
						cars.get(i).draw(gc);
						cars.get(i).move();
						if(cars.get(i).getRect().intersects(frogOutline)){
							x=390;
							y=750;
							tDie.play();
							lives--;
							gotExtra = false;
						}
					}
					for(int i=0; i<waters.size(); i++){
						waters.get(i).draw(gc);
						waters.get(i).move();
						if(waters.get(i).getRect().intersects(frogOutline)){
							x = waters.get(i).getX();
							y = waters.get(i).getY();
						}

					}
					if(x<0){
						x = 390;
						y=750;
						wDie.play();
						lives--;
						gotExtra = false;
					}
					if(x>780){
						x = 390;
						y=750;
						wDie.play();
						lives--;
						gotExtra = false;
					}
					OneUp.move();
					if(gotExtra == false)
						OneUp.draw(gc);
					if(frogOutline.intersects(OneUp.getRect()) && gotExtra==false){
						lives++;
						power.play();
						gotExtra = true;
					}

					boolean waterDie = true;
					for(int i=0; i<waters.size(); i++){
						if((frogOutline.intersects(waters.get(i).getRect()))){
							waterDie = false;
						}
					}
					if(waterOutline.intersects(frogOutline) && waterDie == true){
						x = 390;
						y=750;
						wDie.play();
						lives--;
						gotExtra = false;
					}



					boolean boundsDie = false;
					for(int i=0; i<lilyBounds.size(); i++){
						if(frogOutline.intersects(lilyBounds.get(i))){
							boundsDie = true;
						}
					}
					if(boundsDie == true){
						x = 390;
						y=750;
						lives--;
						gotExtra = false;
					}

					boolean winOne = false;
					winAll = false;
					int winAll2 = 0;
					for(int i=0; i<lilypad.size(); i++){
						if(frogOutline.intersects(lilypad.get(i)))
							winOne = true;
					}
					boolean alreadyWon = false;
					if(winOne == true && boundsDie == false && y<150){
						for(int i=0; i<alreadyGot.size(); i++){
							if(frogOutline.intersects(alreadyGot.get(i))){
								x = 390;
								y=750;
								wDie.play();
								lives--;
								gotExtra = false;

							}
						}
					}
					if(winOne == true && boundsDie == false && y<150){
						winCounter++;
						for(int i=0; i<lilypad.size(); i++){
							if(frogOutline.intersects(lilypad.get(i))){
								alreadyGot.add(lilypad.get(i));
								anotherFrog.play();
								winFrogs.add(new MovingObject((int)lilypad.get(i).getMinX(), (int)lilypad.get(i).getMinY(), new Image("frog.png"), 0));
								x = 390;
								y = 750;
								totalScore+=200;
								scoreLocation = 750;
							}
						}
					}
					if(winCounter >=5)
						winGame =true;
					for(int i=0; i<winFrogs.size(); i++){
						winFrogs.get(i).draw(gc);
					}
					Font font2 = Font.font("Courier New", FontWeight.NORMAL, 30);
					gc.setFont(font2);
					gc.fillText("Lives: " + lives, 0, 25 ); //draws the yellow part of the text
					gc.strokeText( "Lives: " + lives, 0, 25 ); //draws the outline part

				}
				else if (lives>=0){
					totalScore = 0;
					gc.setFill( Color.YELLOW); //Fills the text in yellow
					gc.setStroke( Color.RED ); //Changes the outline the black
					gc.setLineWidth(1); //How big the black lines will be
					Font font = Font.font("Courier New", FontWeight.NORMAL, 48 );
					gc.setFont( font );
					gc.fillText("Game Over", 275, 300 ); //draws the yellow part of the text
					gc.strokeText( "Game Over", 275, 300 ); //draws the outline part of the text
					gc.fillText("Press Space to Play Again",45,350 );
					gc.strokeText("Press Space to Play Again",45,350 );
					for(int i=0;i<winFrogs.size();i++){
						winFrogs.remove(i);
					}

				}
				else if (winGame==true){
					totalScore = 0;
					gc.setFill( Color.YELLOW); //Fills the text in yellow
					gc.setStroke( Color.RED ); //Changes the outline the black
					gc.setLineWidth(1); //How big the black lines will be
					Font font = Font.font("Courier New", FontWeight.NORMAL, 48 );
					gc.setFont( font );
					gc.fillText("GAME Win", 275, 300 ); //draws the yellow part of the text
					gc.strokeText( "You Win", 275, 300 ); //draws the outline part of the text
					gc.fillText("Press Space to Play Again",45,350 );
					gc.strokeText("Press Space to Play Again",45,350 );
					for(int i=0;i<winFrogs.size();i++){
						winFrogs.remove(i);
					}

				}

			}

		}
		public void handle(final InputEvent event){
			if (event instanceof KeyEvent){
				//780,150
				if (((KeyEvent)event).getCode() == KeyCode.LEFT && x>30){
					x-=60;
					clip.play();
				}
				if(((KeyEvent)event).getCode() == KeyCode.RIGHT && x<750){
					x+=60;
					clip.play();
				}
				if(((KeyEvent)event).getCode() == KeyCode.UP && y>30 && y>150){
					y-=60;
					clip.play();
				}
				else if(((KeyEvent)event).getCode() == KeyCode.UP && y<=150 && y>55){
					y-=95;
					clip.play();
				}
				if(((KeyEvent)event).getCode() == KeyCode.DOWN && y<750){
					y+=60;
					clip.play();
				}
				if(((KeyEvent)event).getCode() == KeyCode.SPACE && lives<=0){
					lives =3;
					winCounter = 0;
					x=390;
					y=750;
				}
				if(((KeyEvent)event).getCode() == KeyCode.SPACE && winGame == true){
					lives =3;
					x=390;
					y=750;
					winCounter = 0;
					winGame=false;
				}
				if(scoreLocation>y && y>55){
					scoreLocation = y;
					totalScore+=10;
				}
			}
		}
}






