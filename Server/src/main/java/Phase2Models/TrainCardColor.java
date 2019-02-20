package Phase2Models;

public enum TrainCardColor {
    //Eventually will hold all sprites of these colors as well

    BLUE("Blue"), RED("Red"), YELLOW("Yellow"), GREEN("Green"), PURPLE("Purple"), ORANGE("Orange"),
    BLACK("Black"), WHITE("White"), RAINBOW("Rainbow");

    private final String name;

    TrainCardColor(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
}
