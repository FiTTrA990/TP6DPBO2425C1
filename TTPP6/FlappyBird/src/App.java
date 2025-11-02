import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JLabel scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(scoreLabel, BorderLayout.NORTH);

        Logic logic = new Logic(scoreLabel);
        View view = new View(logic);

        logic.setView(view);
        view.addKeyListener(logic);
        view.setFocusable(true);
        frame.add(view, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
