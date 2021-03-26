
package pong;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Mike
 */
public class Pong extends JPanel implements KeyListener {
    
    
    public Pong(){
        addKeyListener(this);
    }
    
    static int x = 400;
    static int y = 250;
    static int xa = 1;
    static int ya = 1;
    static int p1Ya = 0;
    static int p2Ya = 0;
    static boolean clicked = false;
    static int frameWidth = 790;
    static int frameHeight = 485;
    final static int player1X = 730;
    static int player1Y = 250;
    final static int player2X = 50;
    static int player2Y = 250;
    final static int paddleHeight = 50;
    final static int paddleWidth = 10;
    final static int move = 2;
    static int pause = 4;
    static int playingstyle = 0;
    static double multiplier = 1;
    static int playingstate = 1;
    static int player1score = 0;
    static int player2score = 0;
    static int scorelimit = 6;
    static int winner = 0;
    static int choice = 1;
    static int key = 0;
    static String Key = "";
    static int enter = 0;
    static int Choice = 2;
    static int cHoice = 1;
    static int chOice = 1;
    static final int difficulty = 10;
    
    static void reset(){
    x = 400;
    y = 250;
    xa = 1;
    ya = 1;
    p1Ya = 0;
    p2Ya = 0;
    clicked = false;
    player1Y = 250;
    player2Y = 250;
    playingstate = 1;
    //playingstyle = 0;
    multiplier = 1;
    player1score = 0;
    player2score = 0;
    key = 0;
    Key = "";
    enter = 0;
    Choice = 2;
    cHoice = 1;
    choice = 1;
    }
    
    public void moveBall(){
        
        if(y + ya <= 10){
            ya = -ya;
        }
        else if(y + ya >= 455){
            ya = -ya;
        }
        switch(isHittingPaddle()){
            case 1: xa = -ballMove();
                break;
            case 2: xa = ballMove();
                break;
            case 3: xa = -xa;
                    ya = -ya;
                break;
        }
        x += xa;
        y += ya;
        
    }  
    
    public int ballMove(){
        return (int)((r.nextInt(2) + 1)*multiplier);
    }
    
    public void movePaddles(){
        if(player1Y + p1Ya <= 10 || player1Y + p1Ya >= 400){
            p1Ya = 0;
        }
        if(player2Y + p2Ya <= 10 || player2Y + p2Ya >= 400){
            p2Ya = 0;
        }
        player1Y += p1Ya;
        player2Y += p2Ya;
    }
    Random r = new Random();
    public void movePaddle1(){
        if(r.nextInt((int)(difficulty/multiplier)) != 1){
        if(player1Y + ya <= 400 && player1Y + ya >= 10)
            player1Y += ya;
        }
        
        
    }
    public void movePaddle2(){
        if(r.nextInt((int)(difficulty/multiplier)) != 1){
        if(player2Y + ya <= 400 && player2Y + ya >= 10)
            player2Y += ya;
        }
    }
    
    Color ball = new Color(0,255,0);
    Color paddle1 = new Color(255,0,0);
    Color paddle2 = new Color(0,0,255);
    Color wall = new Color(0,255,255);
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,10000,10000);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,10,455);
        g2d.fillRect(775,0,15,455);
        g2d.setColor(wall);
        g2d.fillRect(0,0,790,10);
        g2d.fillRect(0,455,790,10);
        g2d.setColor(ball);
        g2d.fillOval(x-7, y-7, 14, 14);
        g2d.setColor(paddle1);
        g2d.fillRect(player1X, player1Y, paddleWidth, paddleHeight);
        g2d.fillOval(player1X, player1Y - 5, paddleWidth, paddleWidth);
        g2d.fillOval(player1X, player1Y + paddleHeight - 5, paddleWidth, paddleWidth);
        g2d.setColor(paddle2);
        g2d.fillRect(player2X, player2Y, paddleWidth, paddleHeight);
        g2d.fillOval(player2X, player2Y - 5, paddleWidth, paddleWidth);
        g2d.fillOval(player2X, player2Y + paddleHeight - 5, paddleWidth, paddleWidth);
        /*g2d.setColor(Color.white);
        g2d.drawLine(x, 0, x, 500);
        g2d.drawLine(0, y, 800, y);
         */
        Font f = new Font("SansSerif Bold", Font.BOLD, 20);
        g2d.setFont(f);
        if(playingstate == 2){
            g2d.setColor(Color.white);
            g2d.drawString(String.valueOf(player2score),200,40);
            g2d.drawString(String.valueOf(player1score),600,40);
        }
        if(playingstate == 1){
            if(enter == 0){
                switch (choice) {
                    case 1:
                        g2d.setColor(Color.white);
                        g2d.fillRect(290, 90, 220, 70);
                        break;
                    case 2:
                        g2d.setColor(Color.white);
                        g2d.fillRect(290, 190, 220, 70);
                        break;
                    case 3:
                        g2d.setColor(Color.white);
                        g2d.fillRect(290, 290, 220, 70);
                        break;
                    default:
                }
            g2d.setColor(Color.MAGENTA);
            g2d.fillRect(300, 100, 200, 50);
            g2d.fillRect(300, 200, 200, 50);
            g2d.fillRect(300, 300, 200, 50);
            g2d.setFont(f);
            g2d.setColor(Color.white);
            g2d.drawString("One Player",350,120);
            g2d.drawString("Two Player",350,220);
            g2d.drawString("Zero Player",350,320);
            }
            if(enter == 1){
                switch (choice) {
                    case 1:
                        if(Choice ==  1){
                            g2d.setColor(Color.white);
                            g2d.fillRect(440, 90, 220, 70);
                            playingstyle = 3;
                            g2d.setColor(Color.white);
                            g2d.fillRect(650, 200, 50, 50);
                            g2d.fillRect(650, 300, 50, 50);
                            g2d.setColor(Color.black);
                            g2d.drawLine(675, 210, 675, 240);
                            g2d.drawLine(675, 210, 665, 225);
                            g2d.drawLine(675, 210, 685, 225);
                            g2d.drawLine(675, 310, 675, 340);
                            g2d.drawLine(675, 340, 665, 325);
                            g2d.drawLine(675, 340, 685, 325);
                        }
                        else if(Choice ==  2){
                            g2d.setColor(Color.white);
                            g2d.fillRect(140, 90, 220, 70);
                            playingstyle = 2;
                            g2d.setColor(Color.white);
                            g2d.fillRect(100, 200, 50, 50);
                            g2d.fillRect(100, 300, 50, 50);
                            g2d.setColor(Color.BLACK);
                            g2d.setFont(f);
                            g2d.drawString("W", 115,230);
                            g2d.drawString("S", 115,330);
                        }   g2d.setColor(Color.magenta);
                        g2d.fillRect(150, 100, 200, 50);
                        g2d.fillRect(450, 100, 200, 50);
                        g2d.setColor(Color.white);
                        g2d.setFont(f);
                        g2d.drawString("Play as Blue",190,120);
                        g2d.drawString("Play as Red",490,120);
                        break;
                    case 2:
                        g2d.setColor(Color.white);
                        g2d.fillRect(100, 150, 50, 50);
                        g2d.fillRect(100, 250, 50, 50);
                        g2d.fillRect(650, 150, 50, 50);
                        g2d.fillRect(650, 250, 50, 50);
                        g2d.setColor(Color.BLACK);
                        g2d.setFont(f);
                        g2d.drawString("W", 115,180);
                        g2d.drawString("S", 115,280);
                        g2d.drawLine(675, 160, 675, 190);
                        g2d.drawLine(675, 160, 665, 175);
                        g2d.drawLine(675, 160, 685, 175);
                        g2d.drawLine(675, 260, 675, 290);
                        g2d.drawLine(675, 290, 665, 275);
                        g2d.drawLine(675, 290, 685, 275);
                        g2d.setColor(Color.white);
                        g2d.fillRect(290, 90, 220, 70);
                        g2d.setColor(Color.magenta);
                        g2d.fillRect(300, 100, 200, 50);
                        g2d.setColor(Color.white);
                        g2d.drawString("Ready to Play",335,120);
                        playingstyle = 1;
                        break;
                    case 3:
                        playingstyle = 4;
                        break;
                    default:
                }
            }
        }
        if(playingstate == 3){
            g.setColor(Color.black);
            g.fillRect(x-10, y-10, 30, 30);
            if(winner == 1){
                g2d.setColor(Color.white);
                g2d.drawString("Player 1 Wins!!!",320,70);
            }
            else if(winner == 2){
                g2d.setColor(Color.white);
                g2d.drawString("Player 2 Wins!!!",320,70);
            }
            g2d.setColor(Color.magenta);
            g2d.fillRect(300, 100, 200, 50);
            g2d.fillRect(300, 200, 200, 50);
            g2d.setColor(Color.white);
            g2d.drawString("Play Again",350,120);
            g2d.drawString("Back to Menu",335,220);
            if(cHoice ==  1){
                g2d.setColor(Color.white);
                g2d.fillRect(290, 90, 220, 10);
                g2d.fillRect(290, 150, 220, 10);
                g2d.fillRect(290,90,10,60);
                g2d.fillRect(500,90,10,60);
            }
            else if(cHoice ==  2){
                g2d.setColor(Color.white);
                g2d.fillRect(290, 190, 220, 10);
                g2d.fillRect(290, 250, 220, 10);
                g2d.fillRect(290,190,10,60);
                g2d.fillRect(500,190,10,60);
            }
        }
    }
    
    static int isHittingWall(){
        int wall = 0;
        if(x >= frameWidth-30){
            wall = 1;
            player2score++;
        }
        else if(x <= 5){
            wall = 2;
            player1score++;
        }
        return wall;
    }
    
    
    static int isHittingPaddle(){
        int paddle = 0;
        if(x + 7 >= player1X && x + 7 <= player1X + paddleWidth){
            if(y >= player1Y && y <= player1Y + paddleHeight){
                paddle = 1;
            }
            else{
                int base = Math.abs(player1X + 6 - x);
                int height1 = Math.abs(player1Y - y);
                int height2 = Math.abs(player1Y + paddleHeight - y);
                if(12 >= Math.sqrt((base*base)+(height1*height1)) || 12 >= Math.sqrt((base*base)+(height2*height2))){
                    paddle = 3;
                }
            }
        }
        else if(x - 7 >= player2X && x - 7 <= player2X + paddleWidth){
            if(y >= player2Y && y <= player2Y + paddleHeight){
                paddle = 2;
            }
            else{
                int base = Math.abs(player2X + 6 - x);
                int height1 = Math.abs(player2Y - y);
                int height2 = Math.abs(player2Y + paddleHeight - y);
                if(12 >= Math.sqrt((base*base)+(height1*height1)) || 12 >= Math.sqrt((base*base)+(height2*height2))){
                    paddle = 3;
                }
            }
            
        }
        if(paddle != 0) multiplier += .05;
        return paddle;
    }

    
    static void move(Pong p,int game) throws InterruptedException {
        switch (game) {
            case 1:
                p.moveBall();
                p.movePaddles();
                p.repaint();
                Thread.sleep(pause);
                break;
            case 2:
                p.moveBall();
                p.movePaddle1();
                p.movePaddles();
                p.repaint();
                Thread.sleep(pause);
                break;
            case 3:
                p.moveBall();
                p.movePaddle2();
                p.movePaddles();
                p.repaint();
                Thread.sleep(pause);
                break;
            case 4:
                p.moveBall();
                p.movePaddle1();
                p.movePaddle2();
                p.movePaddles();
                p.repaint();    
                Thread.sleep(pause);
                break;
            default:
        }
    }
    
    //Arrows
    @Override
    public void keyPressed(KeyEvent e){
        key = e.getKeyCode();
        //System.out.println(key);
        switch (key) {
            case 40:
                if(playingstate == 2){
                    if(playingstyle == 1 || playingstyle == 3)
                        p1Ya = move;
                }
                else{
                    if(choice + 1 < 4 && enter == 0)
                        choice++;
                    if(cHoice + 1 < 3 && enter == 0)
                        cHoice++;
                }   break;
            case 38:
                if(playingstate == 2){
                    if(playingstyle == 1 || playingstyle == 3)
                        p1Ya = -move;
                }
                else{
                    if(choice - 1 > 0 && enter == 0)
                        choice--;
                    if(cHoice - 1 > 0 && enter == 0)
                        cHoice--;
                }   break;
            case 10:
                enter++;
                break;
            case 37:
                if(Choice + 1 < 3)
                    Choice++;
                break;
            case 39:
                if(Choice - 1 > 0)
                    Choice--;
                break;
            default:
        }
    }
    
    //Letters
    @Override
    public void keyTyped(KeyEvent e){
        Key = String.valueOf(e.getKeyChar());
        if(Key.equals("w")){
            if(playingstate == 2){
                if(playingstyle == 1 || playingstyle == 2)
            p2Ya = -move;
            }
        }
        else if(Key.equals("s")){
            if(playingstate == 2){
                if(playingstyle == 1 || playingstyle == 2)
            p2Ya = move;
            }
        }
        else if(Key.equals("m")){
            pause++;
        }
        else if(Key.equals("n")){
            if(pause != 1){
                pause--;
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        if(code == 83 || code == 87)p2Ya = 0;
        else if(code == 38 || code == 40)p1Ya = 0;
    }
    
    public static void main (String[] args) throws InterruptedException{
      
    JFrame frame = new JFrame("Pong!!!");
    Pong p = new Pong();  
    p.setFocusable(true);
    p.requestFocusInWindow();
    frame.add(p);
    frame.setSize(frameWidth,frameHeight);
    frame.setLocation(520,185);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    int g = 1;
    while(true){
        if(g == 1){
            playingstate = 1;
            while(playingstate == 1){
                move(p,4);
                if(enter == 2)playingstate = 2;
            }
            choice = 2;
            while(playingstate != 3){
            while(isHittingWall() == 0){
                move(p,playingstyle);
            }
            x = 300;
            y = 250;
            xa = 1;
            ya = 1;
            player1Y = 250;
            player2Y = 250;
            multiplier = 1;
            if(player1score == scorelimit){
                winner = 2;
                playingstate = 3;
                player1score = 0;
                player2score = 0;
            }
            else if(player2score == scorelimit){
                winner = 1;
                playingstate = 3;
                player1score = 0;
                player2score = 0;
            }
            }
            enter = 0;
            while(enter != 1){
                p.repaint();
            }
            playingstate = 4;
            if(cHoice == 1){
                g = 2;
            }
            else if(cHoice == 2){
                g = 1;
                playingstyle = 0;
                reset();
            }
        }
        else if(g == 2){
            int a = player1score;
            int b = player2score;
            reset();
            player1score = a;
            player2score = b;
            playingstate = 2;
            while(playingstate != 3){
            while(isHittingWall() == 0){
                move(p,playingstyle);
            }
            x = 300;
            y = 250;
            xa = 1;
            ya = 1;
            player1Y = 250;
            player2Y = 250;
            multiplier = 1;
            if(player1score == scorelimit){
                winner = 2;
                playingstate = 3;
                player1score = 0;
                player2score = 0;
            }
            else if(player2score == scorelimit){
                winner = 1;
                playingstate = 3;
                player1score = 0;
                player2score = 0;
            }
            }
            enter = 0;
            while(enter != 1){
                p.repaint();
            }
            playingstate = 4;
            if(cHoice == 1){
                g = 2;
            }
            else if(cHoice == 2){
                g = 1;
                playingstyle = 0;
                reset();
            }
        }
    }
    }
}