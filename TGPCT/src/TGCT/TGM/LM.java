package TGCT.TGM;

import TGCT.LC;
import TGCT.TGM.Objects.Ball;
import TGCT.TGM.Objects.Bound;
import TGCT.TGM.Objects.HitBox;
import TGCT.TGM.Objects.VOD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LM {

    // Attributes:
    private final int width, height;
    private final ArrayList<VOD> dynamicObjects;

    private final LC lc;

    // Constructor:
    public LM(int width, int height, LC lc) {
        this.width = width;
        this.height = height;
        this.dynamicObjects = new ArrayList<>();
        createDefaultBounds();

        this.lc = lc;
    }

    // Getter:
    public ArrayList<VOD> getDynamicObjects() {
        return dynamicObjects;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    // Public methods:
    public void createBall(int x, int y) {
        Ball ball = new Ball(x, y, this);
        dynamicObjects.add(ball);

        Thread thread = new Thread(ball);
        thread.start();
    }
    public void collideDetection(VOD object, int[] coords) {
        VOD vod = isColliding(object, coords);

        if (vod != null) {
            String actions = lc.solveSituation(object, vod);
            executeInstructions(actions, object, vod, coords);
        } else {
            object.move(coords[0], coords[1]);
        }
    }

    // Private methods:
    private void createDefaultBounds() {
        Bound N = new Bound(0, 0, width, 2);
        Bound S = new Bound(0, height - 2, width, height);
        Bound E = new Bound(width - 2, 0, width, height);
        Bound W = new Bound(0, 0, 2, height);

        dynamicObjects.add(N);
        dynamicObjects.add(S);
        dynamicObjects.add(E);
        dynamicObjects.add(W);
    }
    private VOD isColliding(VOD object, int[] coords) {
        for (VOD vod : dynamicObjects) {
            if (vod != object) {
                if (object.getFutureHitbox(coords).intersects(vod.getHitbox())) {
                    return vod;
                }
            }
        }
        return null;
    }
    private void executeInstructions(String actions, VOD objectA, VOD objectB, int[] coords) {
        if (actions != null) {
            Map<String, VOD> objects = new HashMap<>();
            objects.put("objectA", objectA);
            objects.put("objectB", objectB);

            String[] individualActions = actions.split("/");

            for (String action : individualActions) {

                String[] parts = action.split(":");
                VOD object = objects.get(parts[0]);
                String objectAction = parts[1];
                if (object != null) {
                    executeAction(object, objectAction, coords);
                }
            }
        }
    }
    private void executeAction(VOD object, String action, int[] coords) {
        switch (action) {
            case "bounce(Ball)" -> object.bounce("Ball", coords);
            case "bounce(Bound)" -> object.bounce("Bounds", coords);
            case "terminate()" -> {
                object.switchState(1);
                dynamicObjects.remove(object);
            }
        }
    }
}