package TGCT.TGM.Objects;

import java.awt.*;

public class HitBox extends Rectangle implements Shape {

    // Constructor:
    public HitBox(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2 - x1, y2 - y1);
    }

    // Override method:
    @Override
    public boolean intersects(Rectangle rect) {
        return super.intersects(rect);
    }
}