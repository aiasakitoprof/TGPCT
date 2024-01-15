package TGCT.TGM.Objects;

import java.awt.*;

public interface VOD {

    HitBox getHitbox();
    HitBox getFutureHitbox(int[] coords);
    void move(int x, int y);
    void bounce(String action, int[] coords);
    void kill();
    void switchState(int newState);
    void explode();
    void paint(Graphics g);
}