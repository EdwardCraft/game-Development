package CL;
import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.io.IOException;
import EN.EntityA;
import EN.EntityB;
import java.util.LinkedList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;



public class SpaceGame extends Canvas implements Runnable{

	public static final int WIDTH  = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE  = 2;
	public final  String    TITLE  = "2D Space game";
    private boolean running = false;
    private Thread thread;
	//buffer image is to load the image  before  you see it
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background  = null;
    private BufferedImage background1  = null;
    private player p;
    private Controller c; 
    private boolean is_shooting=false;
    private Textures tex;
    private int  enemy_count   = 1;
    private int  enemy_killed  = 0;
    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;
    private Menu menu;
    public static int  HEALTH = 100*2;


    public static enum STATE{
        MENU,
        GAME   

    };

    public static STATE State = STATE.MENU;


    public void init(){

        requestFocus();
        BufferImg loader =  new BufferImg();

        try{

            spriteSheet = loader.loadImage("/IMG/SpriteBeta.png");
            background  = loader.loadImage("/IMG/fondo1.png");
            background1  = loader.loadImage("/IMG/fondo.png");
        }catch(IOException e){
        e.printStackTrace();    
        }

        
        tex = new Textures(this);
        c = new Controller(tex,this);
        p = new player(200,200,tex,this,c);
        
        menu = new Menu();
        ea=c.getEntityA();
        eb=c.getEntityB();


        this.addKeyListener(new keyImput(this));
        this.addMouseListener(new MouseInput());
        c.createEnemy(enemy_count);
      
    }




    public synchronized void start(){
    	if(running)
    		return;

    	running = true;
     	thread   = new Thread(this);
     	thread.start(); 
    }

    public synchronized void stop(){
    	if(!running)
    		return;

    	running=false;
    	try{
    		thread.join();
    	}catch(InterruptedException e){
    		e.printStackTrace();
    	}
    	
    	System.exit(1);

    }
    

    // the hart of the game : )

    public void run(){
        init();
    	long LastTime = System.nanoTime();
    	final double  amountOfTicks =60.0;
    	double ns= 1000000000 / amountOfTicks;
    	double delta =0;
    	int updates  =0;
    	int frames   =0;
    	long timer   =System.currentTimeMillis();


    	while(running){
    		long now=System.nanoTime();
    		delta +=(now - LastTime )/ns;
    		LastTime =now;
    		if(delta >=1){
    			tick();
    			updates++;
    			delta--;
    		}
    		render();
    		frames++;
    		if(System.currentTimeMillis() - timer > 1000){
    			timer += 1000;
    			System.out.println(updates + "ticks, FPS" + frames);
    			updates=0;
    			frames =0;
    		}


    		
    	}
    	stop();
    }
     // the hart of the game : )



    //everithing that uptades 
    private  void tick(){
        if(State == STATE.GAME){
            p.tick();
            c.tick();
        }
        if(enemy_killed >= enemy_count){
            enemy_count+=1;
            enemy_killed=0;
            c.createEnemy(enemy_count);
        }


    }
    
    //everithing that renders 
    private void render(){
    	BufferStrategy  bs= this.getBufferStrategy();
    	if(bs==null){
    		createBufferStrategy(3); //  the  3 mean that we are  to create  3  buffers
    		return;
    	}
    	Graphics g = bs.getDrawGraphics();
    	////////////////////////////////////////////////////////////
    	//this is were we are goig to draw  

    	g.drawImage(image,0,0,getWidth(),getHeight(), this);
        g.drawImage(background1,0,0,null);
        if(State == STATE.GAME){
            if(HEALTH != 0){
            g.drawImage(background,0,0,null);
            p.render(g);
            c.render(g);

            g.setColor(Color.green);
            g.fillRect(5,5,HEALTH,20);

            g.setColor(Color.white);
            g.drawRect(5,5,200,20);
            }else{
                State = STATE.MENU;
                HEALTH = 100 * 2;
            }
         

        }else if(State == STATE.MENU){
            menu.render(g);
        }
    	////////////////////////////////////////////////////////////
    	g.dispose();
    	bs.show();




    }

    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();
        if(State == STATE.GAME){
            if(key == KeyEvent.VK_RIGHT){
                    p.setVelX(5);
             }else if(key == KeyEvent.VK_LEFT){
                    p.setVelX(-5);
             }else if(key == KeyEvent.VK_DOWN){
                    p.setVelY(5);
            }else if(key == KeyEvent.VK_UP){
                    p.setVelY(-5);
            }if(key == KeyEvent.VK_SPACE && !is_shooting){
                    // to make the spaceship shoot bullet 
                    
                    c.addEntity(new Bullet(p.getX(),p.getY(),tex,this));
                    is_shooting=true;
            }
        }
    }

    public void keyReleased(KeyEvent e){

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT){
            p.setVelX(0);
        }else if(key == KeyEvent.VK_LEFT){
            p.setVelX(0);
        }else if(key == KeyEvent.VK_DOWN){
            p.setVelY(0);
        }else if(key == KeyEvent.VK_UP){
            p.setVelY(0);
        }else if(key == KeyEvent.VK_SPACE){
            is_shooting=false;
        }
      

    }


   public BufferedImage getSpriteSheet(){
    return spriteSheet;
   }


   public int getEnemy_count(){
    return enemy_count;
   }

   public void setEnemy_count(int enemy_count){
    this.enemy_count= enemy_count;
   }

    public int getEnemy_killed(){
    return enemy_killed;
   }

   public void setEnemy_killed(int enemy_killed){
    this.enemy_killed= enemy_killed;
   }


 
  

}