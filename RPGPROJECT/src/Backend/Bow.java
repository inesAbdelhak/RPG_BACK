package Backend;



public class Bow extends Weapon{

    private static final double DAMAGE = 15;
    private static final double PRICE = 10;

    public Bow() {
        super("Bow", DAMAGE, PRICE);
    }

    @Override
    public String ascii_art() {
        return
        "   (         \n" +
        "    \\       \n" +
        "     )       \n" +
        "##-------->  \n" +
        "     )\n" +
        "    /        \n" +
        "   (         \n";
    }
}

