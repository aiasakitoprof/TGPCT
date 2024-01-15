package TGCT.TGM.Objects;

import TGCT.TGM.LM;

import java.awt.*;
import java.util.Random;

public class Ball implements Runnable, VOD {

    // Attributes:
    private int x, y;
    private double vx, vy;
    private double ax, ay;
    private final double maxSpeed;
    private final int diameter;

    private String state;

    private final LM lm;

    // Constructors:
    public Ball() {
        maxSpeed = 0;
        diameter = 0;
        lm = null;
    }
    public Ball(int x, int y, LM lm) {
        this.lm = lm;
        this.x = x;
        this.y = y;
        maxSpeed = 10;
        diameter = 10;
        vx = new Random().nextDouble(-maxSpeed, maxSpeed);
        vy = new Random().nextDouble(-maxSpeed, maxSpeed);
        state = "Waiting";

    }

    // Setter:
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setVx(double vx) {
        this.vx = vx;
    }
    public void setVy(double vy) {
        this.vy = vy;
    }
    public void setAx(double ax) {
        this.ax = ax;
    }
    public void setAy(double ay) {
        this.ay = ay;
    }
    public void setState(String state) {
        this.state = state;
    }

    // Getter:
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public double getVx() {
        return vx;
    }
    public double getVy() {
        return vy;
    }
    public double getAx() {
        return ax;
    }
    public double getAy() {
        return ay;
    }
    public double getMaxSpeed() {
        return maxSpeed;
    }
    public int getDiameter() {
        return diameter;
    }

    // Override methods:
    @Override
    public HitBox getHitbox() {
        return new HitBox(x - (diameter/2), y - (diameter/2), x + (diameter/2), y + (diameter/2));
    }
    @Override
    public HitBox getFutureHitbox(int[] coords){
        return new HitBox(coords[0] - (diameter/2), coords[1] - (diameter/2), coords[0] + (diameter/2), coords[1] + (diameter/2));
    }
    @Override
    public void move(int x, int y) {
        setX(x);
        setY(y);
    }
    @Override
    public void bounce(String action, int[] coords) {
        switch (action) {
            case "Ball" -> {
                calcBounceBall();
            }
            case "Bounds" -> {
                calcBounceBounds(coords);
            }
        }
    }
    @Override
    public void kill() {

    }
    @Override
    public void switchState(int newState) {
        switch (newState) {
            case 0 -> state = "alive";
            case 1 -> state = "terminated";
        }
    }
    @Override
    public void explode() {

    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x - diameter / 2, y - diameter / 2, diameter, diameter);

        g.setColor(Color.WHITE);
        g.fillOval(x - diameter / 2, y - diameter / 2, diameter, diameter);
    }

    // Run:
    @Override
    public void run() {
        switchState(0);
        while (!state.equals("terminated")) {
            nextMove();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    // Private methods:
    private int[] calcCoords() {
        int newX = (int) (x + vx);
        int newY = (int) (y + vy);

        return new int[]{newX, newY};
    }
    private void nextMove() {
        lm.collideDetection(this, calcCoords());
    }
    private void calcBounceBall() {
        vx = -vx;
        vy = -vy;

        setX((int)(x + vx));
        setY((int)(y + vy));
    }
    private void calcBounceBounds(int[] coords) {
        int coordX = coords[0];
        int coordY = coords[1];

        if (coordX - diameter <= 2) {
            vx = -vx;
            setX(2 + diameter);
        }
        if (coordX + diameter >= lm.getWidth() - 2) {
            vx = -vx;
            setX(lm.getWidth() - 2 - diameter);
        }

        if (coordY - diameter <= 2) {
            vy = -vy;
            setY(2 + diameter);
        }
        if (coordY + diameter >= lm.getHeight() - 2) {
            vy = -vy;
            setY(lm.getHeight() - 2 - diameter);
        }
    }
}