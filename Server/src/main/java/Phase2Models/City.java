package Phase2Models;

public enum City {
    Atlanta(0,0),Boston(0,0),Calgary(0,0),Charleston(0,0),Chicago(0,0),Dallas(0,0),Denver(0,0),
    Duluth(0,0),El_Paso(0,0), Helena(0,0),Houston(0,0),Kansas_City(0,0),Las_Vegas(0,0),
    Little_Rock(0,0),Los_Angeles(0,0),Miami(0,0),Montreal(0,0), Nashville(0,0),New_Orleans(0,0),
    New_York(0,0),Oklahoma_City(0,0),Omaha(0,0),Phoenix(0,0),Pittsburgh(0,0),Portland(0,0),
    Raleigh(0,0),Saint_Louis(0,0),Salt_Lake_City(0,0),San_Francisco(0,0),Santa_Fe(0,0),
    Sault_St_Marie(0,0),Seattle(0,0), Toronto(0,0),Vancouver(0,0),Washington(0,0),Winnipeg(0,0);
    double x;
    double y;
    City(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }
    public double y()
    {
        return y;
    }
}
