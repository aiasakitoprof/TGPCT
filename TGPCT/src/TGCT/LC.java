package TGCT;

import TGCT.TGM.LM;
import TGCT.TGM.Objects.Bound;
import TGCT.TGM.Objects.VOD;
import TGCT.TGR.Codex;
import TGCT.TGV.LV;

import java.util.ArrayList;

public class LC {

    // Attributes:
    private final int width, height;

    private final LM lm;
    private final LV lv;

    private final Codex codex;

    // Constructor:
    public LC(int width, int height) {
        this.width = width;
        this.height = height;

        this.lm = new LM(width, height, this);
        this.lv = new LV(width, height, this);

        this.codex = new Codex();
    }

    // Getters:
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    // Public methods:
    public String solveSituation(VOD objectA, VOD objectB) {
        return codex.getRule(objectA, objectB);
    }
    public void createBall(int x, int y) {
        lm.createBall(x, y);
    }
    public ArrayList<VOD> getDynamicObjects() {
        return lm.getDynamicObjects();
    }
}