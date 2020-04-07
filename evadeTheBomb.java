import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
 * Chasing bombs game
 *
 * @author (Tiberiu Paharnicu tp371)
 * @version Final 06/04/2020
 */
public class evadeTheBomb extends JFrame

{

    /**
     * Variables
     */
    private JFrame window = new JFrame("Chasing-bombs-tp371");
    private JPanel gamePanel = new JPanel(new GridLayout(2, 5));
    private JPanel levelPanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel layoutStructure = new JPanel(new GridLayout(1, 3));
    private JPanel[] bombs = new JPanel[10];
    private Color color1 = Color.BLACK;
    private Color color2 = Color.GRAY;
    private Color color3 = Color.LIGHT_GRAY;
    private Color clickedColor = Color.GREEN;
    private Color clickedBombColor = Color.RED;
    private int bomb = 0;
    private int score = 0;
    private int moves = 5;
    private int newMoves = 0;
    private JButton play = new JButton("Play");
    private JButton exit = new JButton("Exit");
    private JButton easyDifficultyBtn = new JButton("Easy");
    private JButton intermediateDifficultyBtn = new JButton("Medium");
    private JButton hardDifficultyButton = new JButton("Hard");
    private JLabel scoreShow = new JLabel("");
    private boolean clickedButtons[] = new boolean[10];
    private boolean isRunning = false;

    public evadeTheBomb() {
        
        
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        
        manageGame();
    }

    /**
     * Constructor that contains variables for game contrustion and managment
     */

    public void manageGame() {
        gamePanel.setBackground(color1);
        levelPanel.setBackground(color3);
        mainPanel.setBackground(color2);

        scoreShow.setForeground(Color.WHITE);

        bombLayout();

        mainPanel.add(play);
        mainPanel.add(exit);
        mainPanel.add(scoreShow);

        levelPanel.add(easyDifficultyBtn);
        levelPanel.add(intermediateDifficultyBtn);
        levelPanel.add(hardDifficultyButton);

        play.addMouseListener(new MouseAdapter() // start the game
        {
            public void mousePressed(MouseEvent e) {
                if (isRunning) {
                    moves = newMoves;
                }

                isRunning = true;
                score = 0;
                for (int x = 0; x < 10; x++) {
                    bombs[x].setBackground(Color.WHITE);
                    clickedButtons[x] = false;
                }

                Random rand = new Random();
                bomb = rand.nextInt(10);
                scoreShow.setText("Current score: " + score);
            }
        });

        exit.addMouseListener(new MouseAdapter() // close the game
        {
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });

        easyDifficultyBtn.addMouseListener(new MouseAdapter() // close the game
        {
            public void mousePressed(MouseEvent e) {
                if (!isRunning) {
                    moves = 5;
                } else {
                    newMoves = 5;
                }
            }
        });

        intermediateDifficultyBtn.addMouseListener(new MouseAdapter() // close the game
        {
            public void mousePressed(MouseEvent e) {
                if (!isRunning) {
                    moves = 7;
                } else {
                    newMoves = 7;
                }
            }
        });

        hardDifficultyButton.addMouseListener(new MouseAdapter() // close the game
        {
            public void mousePressed(MouseEvent e) {
                if (!isRunning) {
                    moves = 9;
                } else {
                    newMoves = 9;
                }
            }
        });

        
        
        layoutStructure.add(gamePanel);
        layoutStructure.add(mainPanel);
        layoutStructure.add(levelPanel);
        window.setLayout(new GridLayout(1,3));
        window.add(layoutStructure);
        
        
        

    }

    public void bombLayout() {
        for (int x = 0; x < 10; x++) {
            bombs[x] = new JPanel();
            gamePanel.add(bombs[x]);
            bombs[x].setBackground(color1);
            bombs[x].setBorder(BorderFactory.createLineBorder(Color.black));

            bombs[x].addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (isRunning) {
                        JPanel currentPanel = (JPanel) e.getSource();

                        int index = Arrays.asList(bombs).indexOf(currentPanel);

                        if (index == bomb) {
                            gameLost();
                            currentPanel.setBackground(clickedBombColor);
                        } else if (!clickedButtons[index]) {
                            currentPanel.setBackground(clickedColor);
                            score++;
                            clickedButtons[index] = true;
                            scoreShow.setText("Current score: " + score);
                            if (moves == score) {
                                gameWon();
                            }
                        }
                    }

                }
            });

        }
    }

    public void gameWon() {
        scoreShow.setText("You win! You scored " + score + " points");
        isRunning = false;
    }

    public void gameLost() {
        scoreShow.setText("You lose! You scored " + score + " points");
        isRunning = false;
    }

    public static void main(String[] args) {
        evadeTheBomb startGame = new evadeTheBomb();
    }
}