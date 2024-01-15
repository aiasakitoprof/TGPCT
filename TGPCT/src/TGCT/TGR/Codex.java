package TGCT.TGR;

import TGCT.TGM.Objects.Ball;
import TGCT.TGM.Objects.Bound;
import TGCT.TGM.Objects.VOD;
import TGCT.TGR.Rules.BallBall;
import TGCT.TGR.Rules.BallBound;
import TGCT.TGR.Rules.Situation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Codex {

    // Attributes:
    private Map<RuleKey, Situation> codex;

    // Constructor:
    public Codex() {
        codex = new HashMap<>();
        setCodex();
    }

    // Public methods:
    public String getRule(VOD objctA, VOD objctB) {
        RuleKey key = new RuleKey(objctA, objctB);

        Situation rule = codex.get(key);

        if (rule != null) {
            BiFunction<?, ?, String> function = rule.getFunction();
            return ((BiFunction<VOD, VOD, String>) function).apply(objctA, objctB);
        }
        return null;
    }

    // Private methods:
    private void setCodex() {
        codex.put(new RuleKey(new Ball(), new Ball()), new BallBall());
        codex.put(new RuleKey(new Ball(), new Bound()), new BallBound());
    }
}