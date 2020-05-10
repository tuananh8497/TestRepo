package SnakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

  private final int B_WIDTH = 500;
  private final int B_HEIGHT = 500;
  private final int DOT_SIZE = 10;
  private final int ALL_DOTS = 2500;
  private final int RAND_POS = 29;
  private final int DELAY = 140;

  private SnakeLinkedList snake = new SnakeLinkedList();
  private Color[] colours = {Color.BLUE, Color.YELLOW, Color.GREEN};

  private int dots;
  private int apple_x;
  private int apple_y;

  public static boolean leftDirection = false;
  public static boolean rightDirection = true;
  public static boolean upDirection = false;
  public static boolean downDirection = false;
  public static boolean inGame = true;

  private Timer timer;
  private Image ball;
  private Image apple;
  private Image head;

  public Board() {

    initBoard();
  }

  private void initBoard() {

    addKeyListener(new TAdapter());
    setBackground(Color.BLACK);
    setFocusable(true);

    setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
    loadImages();
    initGame();
  }

  private void loadImages() {

    ImageIcon iid = new ImageIcon("src/resources/dot.png");
    ball = iid.getImage();

    ImageIcon iia = new ImageIcon("src/resources/apple.png");
    apple = iia.getImage();

    ImageIcon iih = new ImageIcon("src/resources/head.png");
    head = iih.getImage();
  }

  private void initGame() {

    dots = 3;
    for (int z = 0; z < dots; z++) {
      if (z == 0) {
        snake.addHead(50, 50, Color.RED);
      } else {
        snake.addJoint(50 - z * DOT_SIZE, 50, colours[z]);
      }
    }
    locateApple();

    timer = new Timer(DELAY, this);
    timer.start();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    doDrawing(g);
  }

  private void doDrawing(Graphics g) {

    if (inGame) {

      g.drawImage(apple, apple_x, apple_y, this);

      for (int z = 0; z < dots; z++) {
        if (z == 0) {
          SnakeNode head = snake.getHead();
          g.setColor(head.getColor());
          g.fillOval(head.getX(), head.getY(), DOT_SIZE, DOT_SIZE);
          snake.addTail(colours[z]);
        } else {
          SnakeNode joint = snake.getJoint(z);
          g.setColor(joint.getColor());
          g.fillOval(joint.getX(), joint.getY(), DOT_SIZE, DOT_SIZE);
          snake.addTail(colours[z % 3]);
        }
      }

      Toolkit.getDefaultToolkit().sync();

    } else {

      gameOver(g);
    }
  }

  private void gameOver(Graphics g) {

    String msg = "Game Over";
    Font small = new Font("Helvetica", Font.BOLD, 14);
    FontMetrics metr = getFontMetrics(small);

    g.setColor(Color.white);
    g.setFont(small);
    g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
  }

  private void checkApple() {

    if ((snake.getHead().getX() == apple_x) && (snake.getHead().getY() == apple_y)) {
      dots++;
      locateApple();
    }
  }



  private void checkCollision() {

    for (int z = dots; z > 0; z--) {

      if ((z > 3) && (snake.getHead().getX() == snake.getJoint(z).getX()
          && snake.getHead().getY() == snake.getJoint(z).getY())) {
        inGame = false;
      }
    }


    if (snake.getHead().getY() >= B_HEIGHT) {
      inGame = false;
    }
    if (snake.getHead().getY() < 0) {
      inGame = false;
    }
    if (snake.getHead().getX() >= B_WIDTH) {
      inGame = false;
    }
    if (snake.getHead().getX() < 0) {
      inGame = false;
    }
    if (!inGame) {
      timer.stop();
    }
  }

  private void locateApple() {

    int r = (int) (Math.random() * RAND_POS);
    apple_x = ((r * DOT_SIZE));

    r = (int) (Math.random() * RAND_POS);
    apple_y = ((r * DOT_SIZE));
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (inGame) {
      checkApple();
      checkCollision();
      snake.snakeMove(dots, leftDirection, rightDirection, upDirection, downDirection, DOT_SIZE);
    }
    repaint();
  }

  private class TAdapter extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

      int key = e.getKeyCode();

      if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
        leftDirection = true;
        upDirection = false;
        downDirection = false;
      }

      if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
        rightDirection = true;
        upDirection = false;
        downDirection = false;
      }

      if ((key == KeyEvent.VK_UP) && (!downDirection)) {
        upDirection = true;
        rightDirection = false;
        leftDirection = false;
      }

      if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
        downDirection = true;
        rightDirection = false;
        leftDirection = false;
      }
    }
  }
}
