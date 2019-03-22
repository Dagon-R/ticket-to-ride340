package Phase2Models;

import java.util.Arrays;

public enum City {
    Atlanta("Atlanta",800,430),Boston("Boston",970,140),Calgary("Calgary",240,86),
    Charleston("Charleston",893,439),Chicago("Chicago",700,275),Dallas("Dallas",567,532),
    Denver("Denver",400,375),Duluth("Duluth",575,215),El_Paso("El Paso",386,575),
    Helena("Helena",340,220),Houston("Houston",608,572),Kansas_City("Kansas City",568,357),
    Las_Vegas("Las Vegas",212,453),Little_Rock("Little Rock",638,447),
    Los_Angeles("Los Angeles",147,512),Miami("Miami",925,596),Montreal("Montreal",896,82),
    Nashville("Nashville",748,397),New_Orleans("New Orleans",702,560),New_York("New York",915,215),
    Oklahoma_City("Oklahoma City",547,443),Omaha("Omaha",546,306),Phoenix("Phoenix",267,518),
    Pittsburgh("Pittsburgh",831,261),Portland("Portland",84,210), Raleigh("Raleigh",865,375),
    Saint_Louis("Saint Louis",655,358),Salt_Lake_City("Salt Lake City",268,343),
    San_Francisco("San Francisco",70,405),Santa_Fe("Santa Fe",392,466),
    Sault_St_Marie("Sault St Marie",704,147),Seattle("Seattle",105,159),
    Toronto("Toronto",813,169),Vancouver("Vancouver",109,107),
    Washington("Washington",923,306),Winnipeg("Winnipeg",465,98);
    String name;
    float x;
    float y;
    static final float EDGE_X = 1024;
    static final float EDGE_Y = 683;
    City(String name, float x, float y)
    {
        this.name = name;
        this.x = x/EDGE_X;
        this.y = (y/EDGE_Y);
    }

    public float x() {
        return x;
    }
    public float y()
    {
        return y;
    }

    @Override
    public String toString() {
        return this.name + ": (" + x() + "," + y() + ")";
    }

    public String getName(){
        return this.name;
    }

    public static void main(String args[])
    {
        System.out.println(Arrays.toString(City.values()));
    }

}
