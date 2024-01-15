package TGCT.TGM.Objects;

import java.awt.*;

public class Bound implements VOD{

    // Attributes:
    private final int x1, y1;
    private final int x2, y2;

    // Constructor:
    public Bound() {
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
    }
    public Bound(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    // Override methods:
    @Override
    public HitBox getHitbox() {
        return new HitBox(x1, y1, x2, y2);
    }
    @Override
    public HitBox getFutureHitbox(int[] coords){
        return new HitBox(x1, y1, x2, y2);
    }
    @Override
    public void move(int x, int y) {
    }
    @Override
    public void bounce(String action, int[] coords) {
    }
    @Override
    public void kill() {
    }
    @Override
    public void switchState(int newState) {
    }
    @Override
    public void explode() {
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x1, y1 , x2-x1, y2-y1);
    }
}