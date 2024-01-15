package TGCT.TGV;

import TGCT.LC;
import TGCT.TGM.LM;
import TGCT.TGM.Objects.VOD;

import javax.swing.*;
import java.util.ArrayList;

public class LV extends JFrame {

    // Attributes:
    private final Viewer viewer;

    private final LC lc;

    // Constructor:
    public LV(int width, int height, LC lc) {
        this.lc = lc;

        setUI();

        viewer = new Viewer(width ,height, this);
        add(viewer);

        pack();
        setVisible(true);

        Thread thread = new Thread(viewer);
        thread.start();
    }

    // Setter:
    private void setUI() {
        setTitle("Bouncing ball");
        setLocation(660, 240);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Public methods:
    public void createBall(int x, int y) {
        lc.createBall(x,y);
    }
    public ArrayList<VOD> getDynamicObjects() {
        return lc.getDynamicObjects();
    }
}