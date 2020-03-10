import java.awt.*;

public class Player extends Sprite {

    Game game;
    public Player(Color color, int x, int y, int width, int height, Board board, Game game){
        super(color, x, y, width, height, board);
        this.game = game;

    }

    public void grow(){
        width *= 5;
        height *= 5;
    }
    @Override
    public void move(){
        x = game.getPositionX() - this.getWidth()/2;
        y = game.getPositionY() - this.getHeight()/2;
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
