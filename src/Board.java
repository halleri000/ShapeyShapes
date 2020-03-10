import com.sun.deploy.security.SelectableSecurityManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    Timer timer;
    ArrayList<Sprite> actors;

    int paddingNum = 25;
    Game game;
    long moment;
    long nextMoment;
    Player player;
    int score = 0;


    public Board(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(600, 800));
        setBackground(Color.BLACK);

    }

    public void setUp() {
        actors = new ArrayList<>();

        actors.add(new Player(Color.GREEN, getWidth() / 2, getHeight() / 2, 50, 50, this, game));
        for (int i = 0; i < STATS.getNumFood(); i++) {
            actors.add(new Food(Color.ORANGE, (int) (Math.random() * (getWidth() - paddingNum) + paddingNum), (int) (Math.random() * (getHeight() - paddingNum) + paddingNum), 20, 20, this));
        }

        for (int i = 0; i < STATS.getNumEnemies(); i++) {
        
                actors.add(new Enemy(Color.RED, (int) (Math.random() * (getWidth() - paddingNum) + paddingNum), (int) (Math.random() * (getHeight() - paddingNum) + paddingNum), 50, 50, this));


        }




        timer = new Timer(1000 / 60, this);
        timer.start();


    }


    public void paintComponent(Graphics g) {


       /* if(Gamestates.isMENU()){
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.PLAIN, 50));
            printSimpleString("Weird Shape Thing", getWidth(), 0, 100, g);
            g.setFont(new Font("Arial", Font.PLAIN, 32));
            printSimpleString("CLICK ANYWHERE TO PLAY", getWidth(), 0, 300, g);
       }*/
       // if(game.getisClicked()) {

            super.paintComponent(g);
            for (Sprite thisGuy : actors) {

                thisGuy.paint(g);
            }
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.PLAIN, 50));
            printSimpleString("Score: " + score, getWidth(), 10, 100, g);
       // }



   }


    public void checkCollision() {

        for (int i = 1; i < actors.size(); i++) {

            if (actors.get(0).collidesWith(actors.get(i))) {

                if (actors.get(i) instanceof Enemy) {
                    game.notClicked();
                } else
                    actors.get(i).setRemove();
            }

        }
        for (int i = actors.size() - 1; i >= 0; i--) {
            if (actors.get(i).isRemove()) {
                actors.remove(i);
                score += 10;
                actors.add(new Food(Color.ORANGE, (int) (Math.random() * (getWidth() - paddingNum) + paddingNum), (int) (Math.random() * (getHeight() - paddingNum) + paddingNum), 20, 20, this));



            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nextMoment = System.currentTimeMillis();
        if(nextMoment - game.getMoment() >= 1500){
            checkCollision();


        }

        if (game.getisClicked()) {

            for (Sprite thisGuy : actors) {
                thisGuy.move();
            }
        }

        if (actors.size() <= STATS.getNumEnemies() + 1) {
            System.out.println("Killed em all");
            game.notClicked();
        }
            repaint();

    }
    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g){
        int stringLen = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();
        int start = width/2 - stringLen/2;
        g.drawString(s, start + XPos, YPos);
    }
}