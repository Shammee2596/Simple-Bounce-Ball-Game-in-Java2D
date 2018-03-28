 package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import menu.Launcher;
import menu.MouseInput;
import entity.Enimy;
import entity.Entity;
import entity.Ball;
import graphics.Sprite;
import graphics.SpriteSheet;
import input.KeyInput;
import tile.Flag;
import tile.Ring;

public class Game extends Canvas implements Runnable {
	
	public static final int WIDTH = 96;
	public static final int HEIGHT = 64;
	public static final int SCALE = 9;
	
	private JFrame frame;
	private static String title = "Ball Game: ";
	
	private Thread thread;
	private boolean running = false;
	public static Handler handler;
	public static boolean playing = false;
	public static SpriteSheet sheet;
	private static BufferedImage[]levels;
	public static int level = 0;
	public static int rings = 0;
	public static int lives = 5 ;
	public static int deathScreenTime = 0;
	public static boolean showDeathScreen = false;
	public static boolean gameOver = false;
	
	public static Sprite brick;
	public static Sprite ball;
	public static Camera camera;
	public static Launcher launcher;
	public static Sprite coin;
	public static Sprite enimy;
	public static Sprite spider; 
	public static Sprite flag;
	public static MouseInput mouse;
	
	public Game() {
		
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		frame = new JFrame();
		
	}
	
	public void init() {
		
		handler = new Handler();
		sheet = new SpriteSheet("/Sheets.png");
		launcher = new Launcher();
		camera = new Camera();
		mouse = new MouseInput();
		levels = new BufferedImage[4];
		addKeyListener(new KeyInput());
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		requestFocus();
		
		brick = new Sprite(sheet, 3, 0);
		ball = new Sprite(sheet, 6, 0);
		enimy = new Sprite(sheet, 1, 1);
		flag = new Sprite(sheet,5 , 2);
		coin = new Sprite(sheet, 0, 1);
		spider = new Sprite(sheet, 3, 1);
		
		try {
			levels[0] = ImageIO.read(getClass().getResource("/level4.png"));
			levels[1] = ImageIO.read(getClass().getResource("/level2.png"));
			levels[2] = ImageIO.read(getClass().getResource("/level3.png"));
			levels[3] = ImageIO.read(getClass().getResource("/level4.png"));
			} catch (IOException e) {
			e.printStackTrace();
			}
		handler.createLevel(levels[level]);
		
	}
	
	private synchronized void start(){
		
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop(){
		
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void run() {
		
		init();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.0;
		double ns = 1000000000.0/60.0;
		int frames = 0;
		int updates = 0;
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000 ){
				timer += 1000;
				frame.setTitle(title + " | " + updates + "ups, " + frames + "fps");
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	private void render(){
		
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		// Frame's color
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(20,239,255));
		g.fillRect(0,  0, getWidth(), getHeight());
		
		// Show the Score on the frame
		if(!showDeathScreen){
		    g.drawImage(Game.coin.getImage(), 140, 130, 40, 50,null);
		    g.setColor(Color.RED);
			g.setFont(new Font("Courier",Font.BOLD,20));
			g.drawString("x"+rings, 185, 150);
		}
		// if the ball intersect with obstacle then show the remaining lives
		if(showDeathScreen){
			if(gameOver == false){
				g.setColor(new Color(63,210,255));
				g.fillRect(0, 0, Game.getFrameWidth()+10, Game.getFrameHeight()+10);
				g.setColor(Color.RED);
				g.setFont(new Font("Verdana",Font.BOLD,30));
				g.drawString("Remaining Lives", 300, 220);
				g.setColor(Color.RED);
				g.setFont(new Font("Courier",Font.BOLD,30));
				g.drawImage(Game.ball.getImage(), 310, 260, 80, 80,null);
				g.drawString("x"+ lives, 398, 300);
			}
			else{
				// Shoow Game Over
				g.setColor(new Color(63,210,255));
				g.fillRect(0, 0, Game.getFrameWidth()+10, Game.getFrameHeight()+10);
				g.setColor(Color.BLACK);
				g.setFont(new Font("Courier",Font.BOLD,50));
				g.drawString("Game Over :(", 290, 250);
				
				// show Score
				g.setColor(Color.BLACK);
				g.setFont(new Font("Courier",Font.BOLD,40));
				g.drawString("Your Score: "+rings,290,315);
				
			}
		}
		if(level > 3){
			g.setColor(new Color(63,210,255));
			g.fillRect(0, 0, Game.getFrameWidth()+10, Game.getFrameHeight()+10);
			g.setColor(Color.RED);
			g.setFont(new Font("Courier",Font.BOLD,50));
			g.drawString("You Win:)", 290, 250);
			
			// show Score
			g.setColor(Color.RED);
			g.setFont(new Font("Courier",Font.BOLD,40));
			g.drawString("Your Score: "+ Game.rings,290,315);
			
		}
		if(playing)
			g.translate(camera.getX(), camera.getY());
		
		//if(playing)
		if(!showDeathScreen && playing)
			handler.render(g);
		
		// Show the launcher
		else if(!playing)
			launcher.render(g);
		g.dispose();
		bs.show();
	}
	
	private void update(){
		if(playing)
			handler.update();
		
		for (Entity e: handler.entity) {
			if (e.getId() == ID.ball) {
				camera.update(e);
			}
		}
		if(level == 0){
			for (Entity e: handler.entity) {
				if (e.getId() == ID.ball) {
					camera.update2(e);
				}
			}
		}
		if(showDeathScreen && !gameOver&& playing)
			deathScreenTime++;
		
		if(deathScreenTime >= 180){
			showDeathScreen = false;
			deathScreenTime = 0;
			handler.clearLevel();
			handler.createLevel(levels[level]);
			//addComponents();
			
		}	
	}
	
	public static int getFrameWidth() {
		return Game.WIDTH * Game.SCALE;
	}
	
	public static int getFrameHeight() {
		return Game.HEIGHT * Game.SCALE;
	}
	public static void nextLevel() {
		Game.level++;
		handler.clearLevel();
		if(level < 4)
		handler.createLevel(levels[level]);
		
	}
	
	public static synchronized void addComponents() {
		
		if(level == 0){
		handler.addEntity(new Ball(200, 200, 50, 50, true, ID.ball, handler));
		handler.addEntity(new Enimy(350, 450, 64, 64, true, ID.enemy, handler));
		//handler.addEntity(new Enimy(960, 350-64, 64, 64, true, ID.enemy, handler));
		handler.addTile(new Flag(1190,450, 64, 64*7, true, ID.flag, handler));
		handler.addTile(new Ring(600, 450, 30, 60, true, ID.rings, handler));
		handler.addTile(new Ring(896, 350-64, 30, 64, true, ID.rings, handler));
		handler.addTile(new Ring(150, 290-64, 30, 64, true, ID.rings, handler));
		handler.addTile(new Ring(100, 450, 30, 64, true, ID.rings, handler));
		}
		if(level == 1){
			handler.addEntity(new Ball(576, 450, 50, 50, true, ID.ball, handler));
			handler.addEntity(new Enimy(832, 1024, 64, 64, true, ID.enemy, handler));
			handler.addEntity(new Enimy(600, 450, 64, 64, true, ID.enemy, handler));
			handler.addTile(new Ring(1408, 286, 50, 60, true, ID.rings, handler));
			handler.addTile(new Flag(800,900, 64, 64, true, ID.flag, handler));
			}
	
	}


	public static void main(String[] args) {
		
		Game game = new Game();
		game.frame.add(game);
		game.frame.pack();
		game.frame.setVisible(true);
		game.frame.setResizable(false);
		game.frame.setLocationRelativeTo(null);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.start();

	}

}
