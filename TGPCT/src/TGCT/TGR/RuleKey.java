package TGCT.TGR;

import TGCT.TGM.Objects.VOD;

import java.util.Objects;

public class RuleKey {

    // Attributes:
    public Class<?> objectA;
    public Class<?> objectB;

    // Constructor:
    public RuleKey(VOD objectA, VOD objectB) {
        this.objectA = objectA.getClass();
        this.objectB = objectB.getClass();
    }

    // Override methods:
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleKey ruleKey = (RuleKey) o;
        return objectA.equals(ruleKey.objectA) && objectB.equals(ruleKey.objectB);
    }
    @Override
    public int hashCode() {
        return Objects.hash(objectA, objectB);
    }
}
