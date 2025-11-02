import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Logic implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    int playerStartPosX = frameWidth / 2 - 50;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    // === Tambahan background ===
    Image backgroundImage;

    View view;
    Image birdImage;
    Player player;

    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;

    int gravity = 1;
    int pipeVelocityX = -2;

    boolean gameOver = false;
    int score = 0;
    JLabel scoreLabel;

    // Konstruktor tanpa membuat JFrame
    public Logic(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;

        // === Load semua asset ===
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage(); // ✅ background baru
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<>();

        // Timer spawn pipa
        pipesCooldown = new Timer(1500, e -> placePipes());
        pipesCooldown.start();

        // Game loop
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void setView(View view) {
        this.view = view;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void placePipes() {
        if (gameOver) return;

        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPosY + openingSpace + pipeHeight, pipeWidth, pipeHeight, lowerPipeImage);

        pipes.add(upperPipe);
        pipes.add(lowerPipe);
    }

    public void move() {
        if (gameOver) return;

        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);
        }

        checkCollision();
        updateScore();
    }

    private void checkCollision() {
        Rectangle playerRect = new Rectangle(player.getPosX(), (int) player.getPosY(), player.getWidth(), player.getHeight());

        for (Pipe pipe : pipes) {
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
            if (playerRect.intersects(pipeRect)) {
                gameOver = true;
                pipesCooldown.stop();
                System.out.println("Game Over: Tabrak pipa");
            }
        }

        if (player.getPosY() + player.getHeight() >= frameHeight) {
            gameOver = true;
            pipesCooldown.stop();
            System.out.println("Game Over: Jatuh");
        }
    }

    private void updateScore() {
        for (int i = 0; i < pipes.size(); i += 2) {
            Pipe upperPipe = pipes.get(i);
            if (upperPipe.getPosX() + pipeWidth < player.getPosX() && !upperPipe.isPassed()) {
                upperPipe.setPassed(true);
                score++;
                scoreLabel.setText("Score: " + score);
            }
        }
    }

    public void resetGame() {
        gameOver = false;
        score = 0;
        scoreLabel.setText("Score: 0");
        pipes.clear();

        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        pipesCooldown.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        if (view != null) view.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
            resetGame();
        }
        if (!gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}