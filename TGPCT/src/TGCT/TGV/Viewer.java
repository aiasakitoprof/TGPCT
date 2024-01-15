package TGCT.TGV;

import TGCT.TGM.LM;
import TGCT.TGM.Objects.VOD;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

public class Viewer extends Canvas implements Runnable {

    // Attributes:
    private final LV lv;
    private BufferStrategy bs;
    private final int width, height;

    // Constructor:
    public Viewer(int width, int height, LV lv) {
        this.lv = lv;
        bs = null;

        this.width = width;
        this.height = height;

        setUI();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lv.createBall(e.getX(), e.getY());
            }
        });
    }

    // Setter:
    private void setUI() {
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(30, 30, 30));
    }

    // Run:
    @Override
    public void run() {
        while (true) {
            repaintCanvas();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Private methods:
    private void repaintCanvas() {
        checkBufferStrategy();
        Graphics g = bs.getDrawGraphics();

        g.clearRect(0, 0, width, height);

        for (VOD dynamicObject : lv.getDynamicObjects()) {
            dynamicObject.paint(g);
        }

        bs.show();
        g.dispose();
    }
    private void checkBufferStrategy() {
        if (bs == null) {
            createBufferStrategy(2);
            bs = getBufferStrategy();
        }
    }
}