/*/////////////////////////////////////////////////////////////////////
 / Name: Janahan Dhushenthen                                           /
 / Date: 10/04/2016                                                    /
 / Program Description: Animation based on the arcade game "Asteroids" /
 /////////////////////////////////////////////////////////////////////*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Animation {

    public Animation() {
    }

    public static void main(String[] args) throws Exception {
        FunGUI frame = new FunGUI();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.loop();
    }
}

class FunGUI extends JFrame {

    // Declares global variables and constants
    long startTime;
    int x = 400;
    int y = 285;
    int x2 = 785;
    int y2 = 35;
    int moveY;
    int moveX;
    int[] randX = new int[20];
    int[] randY = new int[20];
    boolean drawStars = true;
    int phase = 1;
    int time = 0;
    int frame = 1;
    int rand;
    int x5;
    int y5;
    boolean moveUFO = false;
    boolean drawUFO = false;
    int randA;
    int rand2A;
    int counterA;
    int x3A;
    int y3A;
    boolean moveAsteroidA = false;
    int randB;
    int rand2B;
    int counterB;
    int x3B;
    int y3B;
    boolean moveAsteroidB = false;
    int randC;
    int rand2C;
    int counterC;
    int x3C;
    int y3C;
    boolean moveAsteroidC = false;
    int x4;
    int y4;
    boolean fireLaser = false;
    final int WIDTH = 800;
    final int HEIGHT = 600;
    BufferedImage buffer;
    Font smallFont = new Font("Arial", 0, 36);
    Font mediumFont = new Font("Arial", 0, 48);
    Font largeFont = new Font("Arial", 0, 96);

    public FunGUI() {
        super("Animation by frames");
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        JPanel pane = (JPanel) getContentPane();
        pane.setDoubleBuffered(true);
        pane.setLayout(new BorderLayout(10, 10));
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setIgnoreRepaint(true);
        setResizable(false);
    }

    // All drawing is done here (in memory)
    public void drawBuffer() {
        Graphics2D b = buffer.createGraphics();

        // Clears frame
        b.setColor(Color.black);
        b.fillRect(0, 0, WIDTH, HEIGHT);

        // Starting screen
        if (phase == 1) {

            // Draws 20 randomly-located stars
            if (drawStars) {
                for (int i = 0; i < 20; i++) {
                    randX[i] = 1 + (int) (Math.random() * (WIDTH));
                    randY[i] = 1 + (int) (Math.random() * (HEIGHT));
                }
            }
            drawStars = false;
            for (int i = 0; i < 20; i++) {
                star(b, Color.white, randX[i], randY[i]);
            }

            text(b, Color.white, largeFont, "ASTEROIDS", 130, 150);

            // Draws large rocket
            triangle(b, x, y + 135, x - 15, y + 90, x + 15, y + 90, true, Color.black);
            triangle(b, x, y + 135, x - 15, y + 90, x + 15, y + 90, true, Color.white);
            triangle(b, x, y, x - 30, y + 90, x + 30, y + 90, true, Color.black);
            triangle(b, x, y, x - 30, y + 90, x + 30, y + 90, false, Color.white);

            // Draws flashing text
            if (frame % 2 == 0) {
                text(b, Color.white, smallFont, "Press SPACE to start", 225, 550);
            }

            addKeyListener(
                           new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {

                            // Checks if 'SPACE' key is pressed before proceeding
                        case KeyEvent.VK_SPACE:
                            if (phase == 1) {
                                drawStars = true;
                                startTime = System.currentTimeMillis();
                            }
                            phase = 2;
                            break;
                    }
                }
            });
        }

        // "Game screen" with actual animations
        else if (phase == 2) {

            // Keeps rocket on screen
            if (y < 45) {
                y = HEIGHT;
            }
            if (x < -20) {
                x = WIDTH;
            }
            if (y > HEIGHT) {
                y = 45;
            }
            if (x > WIDTH) {
                x = -20;
            }
            y += moveY;
            x += moveX;

            addKeyListener(
                           new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {

                            // Checks if 'W' key is pressed to move rocket up
                        case KeyEvent.VK_W:
                            moveY = -2;
                            break;

                            // Checks if 'A' key is pressed to move rocket left
                        case KeyEvent.VK_A:
                            moveX = -2;
                            break;

                            // Checks if 'D' key is pressed to move rocket right
                        case KeyEvent.VK_D:
                            moveX = 2;
                            break;

                            // Checks if 'SPACE' key is pressed to fire laser
                        case KeyEvent.VK_SPACE:
                            fireLaser = true;
                            x4 = x;
                            y4 = y;
                            break;
                    }
                }

                public void keyReleased(KeyEvent e) {
                    switch (e.getKeyCode()) {

                            // Checks if 'W' key is released to stop moving rocket up
                        case KeyEvent.VK_W:
                            moveY = 0;
                            break;

                            // Checks if 'A' key is released to stop moving rocket left
                        case KeyEvent.VK_A:
                            moveX = 0;
                            break;

                            // Checks if 'D' key is released to stop moving rocket right
                        case KeyEvent.VK_D:
                            moveX = 0;
                            break;
                    }
                }
            });

            // Draws 20 randomly-located stars
            if (drawStars){
                for (int i = 0; i < 20; i++) {
                    randX[i] = 1 + (int) (Math.random() * (WIDTH));
                    randY[i] = 1 + (int) (Math.random() * (HEIGHT));
                }
            }
            drawStars = false;
            for (int i = 0; i < 20; i++) {
                star(b, Color.white, randX[i], randY[i]);
            }

            // Draws 3 moving asteroids
            moveAsteroidA();
            moveAsteroidB();
            moveAsteroidC();

            // Occasionally draws moving UFO across screen
            if (frame % 750 == 0) {
                drawUFO = true;
            }
            if (drawUFO) {
                moveUFO();
            }

            // Draws moving laser when fired
            if (fireLaser) {
                moveLaser();
            }

            // Draws rocket
            rocket(x, y);

            // Draws bar at top of screen
            rectangle(b, Color.black, true, 0, 0, WIDTH - 1, 75);
            rectangle(b, Color.white, false, 0, 0, WIDTH - 1, 75);

            // Draws life icons (mini-rockets)
            triangle(b, x2, y2, x2 - 10, y2 + 30, x2 + 10, y2 + 30, false, Color.white);
            triangle(b, x2 - 25, y2, x2 - 35, y2 + 30, x2 - 15, y2 + 30, false, Color.white);
            triangle(b, x2 - 50, y2, x2 - 60, y2 + 30, x2 - 40, y2 + 30, false, Color.white);

            // Finds number of seconds played
            long currentTime = System.currentTimeMillis();
            long deltaTime = currentTime - startTime;
            time = (int)(deltaTime/1000);

            // Resets time at 1000 seconds
            if (time == 1000) {
                startTime = System.currentTimeMillis();
            }

            // Draws elapsed time
            text(b, Color.white, mediumFont, String.valueOf(time), 15, 65);

        }

        frame++;
        b.dispose();

    }

    // Takes what is drawn in memory and draws it on screen
    public void drawScreen() {
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.drawImage(buffer, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    // Animation loop
    public void loop() {
        while (true) {
            try {
                drawBuffer();
                drawScreen();

                // 500ms between frames on starting screen
                if (phase == 1) {
                    Thread.sleep(500);
                }

                // 5ms between frames on "game screen"
                else if (phase == 2) {
                    Thread.sleep(5);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Wrapper method to draw a triangle
    public void triangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, boolean fill, Color c) {
        int[] x = new int[]{x1, x2, x3};
        int[] y = new int[]{y1, y2, y3};
        g.setColor(c);
        if (fill) {
            g.fillPolygon(x, y, 3);
        }
        else {
            g.drawPolygon(x, y, 3);
        }
    }

    // Wrapper method to draw a rectangle
    public void rectangle(Graphics g, Color c, boolean fill, int x, int y, int l, int w) {
        g.setColor(c);
        if (fill) {
            g.fillRect(x, y, l, w);
        }
        else {
            g.drawRect(x, y, l, w);
        }
    }

    // Wrapper method to draw text
    public void text(Graphics g, Color c, Font f, String str, int x, int y) {
        g.setColor(c);
        g.setFont(f);
        g.drawString(str, x, y);
    }

    // Wrapper method to draw an asteroid (hexagon)
    public void asteroid(Graphics g, Color c, boolean fill, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5, int x6, int y6) {
        g.setColor(c);
        int[] xVertexPoints = new int[]{x1, x2, x3, x4, x5, x6};
        int[] yVertexPoints = new int[]{y1, y2, y3, y4, y5, y6};
        byte nPoints = 6;
        if (fill) {
            g.fillPolygon(xVertexPoints, yVertexPoints, nPoints);
        }
        else {
            g.drawPolygon(xVertexPoints, yVertexPoints, nPoints);
        }
    }

    // Wrapper method to draw a star (dot)
    public void star (Graphics g, Color c, int x, int y) {
        g.setColor(c);
        g.drawLine(x, y, x, y);
    }

    // Wrapper method to draw a UFO (ovals)
    public void ufo (Graphics g, Color c, int x, int y, int w, int h) {
        g.setColor(c);
        g.fillOval(x, y, w, h);
        g.setColor(Color.black);
        g.drawOval(x+w/2-h/2, y-h/2, h, h);
        g.setColor(c);
        g.drawOval(x+w/2-h/2, y-h/2, h, h);
    }

    // Wrapper method to draw a laser (line)
    public void laser(Graphics g, Color c, int x1, int y1, int x2, int y2) {
        g.setColor(c);
        g.drawLine(x1, y1, x2, y2);
    }

    // Wrapper method to draw a rocket (triangles)
    public void rocket(int x, int y) {
        Graphics2D b = buffer.createGraphics();

        // Draws rocket
        triangle(b, x, y, x - 10, y + 30, x + 10, y + 30, true, Color.black);
        triangle(b, x, y, x - 10, y + 30, x + 10, y + 30, false, Color.white);

        // Draws flame behind rocket when moving
        if (moveY == -2 || moveX == -2 || moveX == 2) {
            triangle(b, x, y + 45, x - 5, y + 30, x + 5, y + 30, true, Color.black);
            triangle(b, x, y + 45, x - 5, y + 30, x + 5, y + 30, true, Color.white);
        }
    }

    // Method to move laser when fired
    public void moveLaser() {
        Graphics2D b = buffer.createGraphics();

        // Moves laser upwards while on screen
        if (y4 + 10 > 0) {
            y4-=6;
        }
        else {
            fireLaser = false;
        }

        // Draws laser
        laser(b, Color.white, x4, y4, x4, y4 + 10);
    }

    // Method to randomly move UFO across screen
    public void moveUFO() {
        Graphics2D b = buffer.createGraphics();
        if (!moveUFO) {

            // Picks random option
            rand = (int) (Math.random() * 2);

            // Randomly positions UFO left of visible frame
            if (rand == 0) {
                y5 = 150 + (int) (Math.random() * 301);
                x5 = -50;
            }

            // Randomly positions UFO right of visible frame
            else {
                y5 = 150 + (int) (Math.random() * 301);
                x5 = WIDTH;
            }

            // Draws UFO
            ufo(b, Color.black, x5, y5, 50, 10);
            ufo(b, Color.white, x5, y5, 50, 10);

            moveUFO = true;
        }
        else {
            if (x5 >= -50 && x5 <= WIDTH) {

                // Moves UFO right while in frame
                if (rand == 0) {
                    x5+=3;
                }

                // Moves UFO left while in frame
                else {
                    x5-=3;
                }

                // Draws UFO
                ufo(b, Color.black, x5, y5, 50, 10);
                ufo(b, Color.white, x5, y5, 50, 10);
            }
            else {
                moveUFO = false;
                drawUFO = false;
            }
        }
    }

    // Method to randomly move first asteroid across screen
    public void moveAsteroidA() {
        Graphics2D b = buffer.createGraphics();
        if (!moveAsteroidA) {
            counterA = 1;

            // Picks random option
            randA = (int) (Math.random() * 4);

            // Randomly positions asteroid left of visible frame
            if (randA == 0) {
                y3A = 150 + (int) (Math.random() * 301);
                x3A = -110;
            }

            // Randomly positions asteroid above visible frame
            else if (randA == 1) {
                x3A = 150 + (int) (Math.random() * 501);
                y3A = 25;
            }

            // Randomly positions asteroid right of visible frame
            else if (randA == 2) {
                y3A = 150 + (int) (Math.random() * 301);
                x3A = WIDTH;
            }

            // Randomly positions asteroid below visible frame
            else {
                x3A = 150 + (int) (Math.random() * 501);
                y3A = HEIGHT + 50;
            }

            // Draws asteroid
            asteroid(b, Color.black, true, x3A, y3A, x3A + 30, y3A + 50, x3A + 80, y3A + 50, x3A + 110, y3A, x3A + 80, y3A - 50, x3A + 30, y3A - 50);
            asteroid(b, Color.white, false, x3A, y3A, x3A + 30, y3A + 50, x3A + 80, y3A + 50, x3A + 110, y3A, x3A + 80, y3A - 50, x3A + 30, y3A - 50);

            moveAsteroidA = true;
        }
        else {
            if (counterA == 1) {

                // Picks random option
                rand2A = (int) (Math.random() * 2);

            }
            if (x3A >= -110 && x3A <= WIDTH && y3A >= 25 && y3A <= HEIGHT + 50) {
                if (randA == 0) {

                    // Moves asteroid down and right while in frame
                    if (rand2A == 0) {
                        x3A++;
                        y3A++;
                    }

                    // Moves asteroid up and right while in frame
                    else {
                        x3A++;
                        y3A--;
                    }

                }
                else if (randA == 1) {

                    // Moves asteroid down and right while in frame
                    if (rand2A == 0) {
                        x3A++;
                        y3A++;
                    }

                    // Moves asteroid down and left while in frame
                    else {
                        x3A--;
                        y3A++;
                    }

                }
                else if (randA == 2) {

                    // Moves asteroid down and left while in frame
                    if (rand2A == 0) {
                        x3A--;
                        y3A++;
                    }

                    // Moves asteroid up and left while in frame
                    else {
                        x3A--;
                        y3A--;
                    }

                }
                else {

                    // Moves asteroid up and right while in frame
                    if (rand2A == 0) {
                        x3A++;
                        y3A--;
                    }

                    // Moves asteroid up and left while in frame
                    else {
                        x3A--;
                        y3A--;
                    }

                }

                // Draws asteroid
                asteroid(b, Color.black, true, x3A, y3A, x3A + 30, y3A + 50, x3A + 80, y3A + 50, x3A + 110, y3A, x3A + 80, y3A - 50, x3A + 30, y3A - 50);
                asteroid(b, Color.white, false, x3A, y3A, x3A + 30, y3A + 50, x3A + 80, y3A + 50, x3A + 110, y3A, x3A + 80, y3A - 50, x3A + 30, y3A - 50);

            }
            else {
                moveAsteroidA = false;
            }
            counterA++;
        }
    }

    // Method to randomly move second asteroid across screen
    public void moveAsteroidB() {
        Graphics2D b = buffer.createGraphics();
        if (!moveAsteroidB) {
            counterB = 1;

            // Picks random option
            randB = (int) (Math.random() * 4);

            // Randomly positions asteroid left of visible frame
            if (randB == 0) {
                y3B = 150 + (int) (Math.random() * 301);
                x3B = -110;
            }

            // Randomly positions asteroid above visible frame
            else if (randB == 1) {
                x3B = 150 + (int) (Math.random() * 501);
                y3B = 25;
            }

            // Randomly positions asteroid right of visible frame
            else if (randB == 2) {
                y3B = 150 + (int) (Math.random() * 301);
                x3B = WIDTH;
            }

            // Randomly positions asteroid below visible frame
            else {
                x3B = 150 + (int) (Math.random() * 501);
                y3B = HEIGHT + 50;
            }

            // Draws asteroid
            asteroid(b, Color.black, true, x3B, y3B, x3B + 30, y3B + 50, x3B + 80, y3B + 50, x3B + 110, y3B, x3B + 80, y3B - 50, x3B + 30, y3B - 50);
            asteroid(b, Color.white, false, x3B, y3B, x3B + 30, y3B + 50, x3B + 80, y3B + 50, x3B + 110, y3B, x3B + 80, y3B - 50, x3B + 30, y3B - 50);

            moveAsteroidB = true;
        }
        else {
            if (counterB == 1) {

                // Picks random option
                rand2B = (int) (Math.random() * 2);

            }

            if (x3B >= -110 && x3B <= WIDTH && y3B >= 25 && y3B <= HEIGHT + 50) {
                if (randB == 0) {

                    // Moves asteroid down and right while in frame
                    if (rand2B == 0) {
                        x3B++;
                        y3B++;
                    }

                    // Moves asteroid up and right while in frame
                    else {
                        x3B++;
                        y3B--;
                    }

                }
                else if (randB == 1) {

                    // Moves asteroid down and right while in frame
                    if (rand2B == 0) {
                        x3B++;
                        y3B++;
                    }

                    // Moves asteroid down and left while in frame
                    else {
                        x3B--;
                        y3B++;
                    }

                }
                else if (randB == 2) {

                    // Moves asteroid down and left while in frame
                    if (rand2B == 0) {
                        x3B--;
                        y3B++;
                    }

                    // Moves asteroid up and left while in frame
                    else {
                        x3B--;
                        y3B--;
                    }

                }
                else {

                    // Moves asteroid up and right while in frame
                    if (rand2B == 0) {
                        x3B++;
                        y3B--;
                    }

                    // Moves asteroid up and left while in frame
                    else {
                        x3B--;
                        y3B--;
                    }

                }

                // Draws asteroid
                asteroid(b, Color.black, true, x3B, y3B, x3B + 30, y3B + 50, x3B + 80, y3B + 50, x3B + 110, y3B, x3B + 80, y3B - 50, x3B + 30, y3B - 50);
                asteroid(b, Color.white, false, x3B, y3B, x3B + 30, y3B + 50, x3B + 80, y3B + 50, x3B + 110, y3B, x3B + 80, y3B - 50, x3B + 30, y3B - 50);

            }
            else {
                moveAsteroidB = false;
            }
            counterB++;
        }
    }

    // Method to randomly move third asteroid across screen
    public void moveAsteroidC() {
        Graphics2D b = buffer.createGraphics();
        if (!moveAsteroidC) {
            counterC = 1;

            // Picks random option
            randC = (int) (Math.random() * 4);

            // Randomly positions asteroid left of visible frame
            if (randC == 0) {
                y3C = 150 + (int) (Math.random() * 301);
                x3C = -110;
            }

            // Randomly positions asteroid above visible frame
            else if (randC == 1) {
                x3C = 150 + (int) (Math.random() * 501);
                y3C = 25;
            }

            // Randomly positions asteroid right of visible frame
            else if (randC == 2) {
                y3C = 150 + (int) (Math.random() * 301);
                x3C = WIDTH;
            }

            // Randomly positions asteroid below visible frame
            else {
                x3C = 150 + (int) (Math.random() * 501);
                y3C = HEIGHT + 50;
            }

            // Draws asteroid
            asteroid(b, Color.black, true, x3C, y3C, x3C + 30, y3C + 50, x3C + 80, y3C + 50, x3C + 110, y3C, x3C + 80, y3C - 50, x3C + 30, y3C - 50);
            asteroid(b, Color.white, false, x3C, y3C, x3C + 30, y3C + 50, x3C + 80, y3C + 50, x3C + 110, y3C, x3C + 80, y3C - 50, x3C + 30, y3C - 50);

            moveAsteroidC = true;
        }
        else
        {
            if (counterC == 1) {

                // Picks random option
                rand2C = (int) (Math.random() * 2);

            }

            // Moves asteroid randomly across frame, while in frame
            if (x3C >= -110 && x3C <= WIDTH && y3C >= 25 && y3C <= HEIGHT + 50) {
                if (randC == 0) {

                    // Moves asteroid down and right while in frame
                    if (rand2C == 0) {
                        x3C++;
                        y3C++;
                    }

                    // Moves asteroid up and right while in frame
                    else {
                        x3C++;
                        y3C--;
                    }

                }
                else if (randC == 1) {

                    // Moves asteroid down and right while in frame
                    if (rand2C == 0) {
                        x3C++;
                        y3C++;
                    }

                    // Moves asteroid down and left while in frame
                    else {
                        x3C--;
                        y3C++;
                    }

                }
                else if (randC == 2) {

                    // Moves asteroid down and left while in frame
                    if (rand2C == 0) {
                        x3C--;
                        y3C++;
                    }

                    // Moves asteroid up and left while in frame
                    else {
                        x3C--;
                        y3C--;
                    }

                }
                else {

                    // Moves asteroid up and right while in frame
                    if (rand2C == 0) {
                        x3C++;
                        y3C--;
                    }

                    // Moves asteroid up and left while in frame
                    else {
                        x3C--;
                        y3C--;
                    }

                }

                // Draws asteroid
                asteroid(b, Color.black, true, x3C, y3C, x3C + 30, y3C + 50, x3C + 80, y3C + 50, x3C + 110, y3C, x3C + 80, y3C - 50, x3C + 30, y3C - 50);
                asteroid(b, Color.white, false, x3C, y3C, x3C + 30, y3C + 50, x3C + 80, y3C + 50, x3C + 110, y3C, x3C + 80, y3C - 50, x3C + 30, y3C - 50);

            }
            else {
                moveAsteroidC = false;
            }
            counterC++;
        }
    }
}
