import TGCT.LC;

public class TGPCT {

    // Attributes:
    private final LC lc;

    // Constructor:
    public TGPCT() {
        this.lc = new LC(600, 600);
    }

    // Getter:
    public LC getLc() {
        return lc;
    }

    // Main:
    public static void main(String[] args) {
        TGPCT game = new TGPCT();
    }
}