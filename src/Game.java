import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class Game extends JFrame {
     Board board;

     int positionX, positionY;
     long moment;
     boolean mouseClicked = false;

    public Game() {
        setTitle("Shapey Shapes");
        setResizable(false);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new Board(this);

        add(board);

        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){

                super.mouseEntered(e);
                setCursor(getToolkit().createCustomCursor(new BufferedImage(3,3,2),new Point(0,0), "null"));
            }
        });


        addMouseMotionListener(new MouseMotionAdapter(){
            @Override
     public void mouseMoved(MouseEvent e) {
               super.mouseMoved(e);
                positionX = e.getX();
                positionY = e.getY();
            }


        });
        addMouseListener(new MouseAdapter() {
                             @Override

                             public void mouseClicked(MouseEvent e) {
                                 super.mouseClicked(e);
                                 mouseClicked = true;
                                 moment = System.currentTimeMillis();
                             }
                         });
        pack();
        board.setUp();
        setLocationRelativeTo(null);

    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }
    public boolean getisClicked(){
        return mouseClicked;
    }
    public void notClicked(){
        mouseClicked = false;
    }
    public long getMoment(){
        return moment;
    }
    public static void main(String[] args){

      Game game =  new Game();

    }

}