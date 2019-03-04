package Phase2Models;

import java.util.Arrays;

public enum City {
    Atlanta(800,430),Boston(970,140),Calgary(240,86),Charleston(893,439),Chicago(700,275),
    Dallas(567,532),Denver(400,375), Duluth(575,215),El_Paso(386,575), Helena(340,220),
    Houston(608,572),Kansas_City(568,357),Las_Vegas(212,453), Little_Rock(638,447),
    Los_Angeles(147,512),Miami(925,596),Montreal(896,82), Nashville(748,397),New_Orleans(702,560),
    New_York(915,215),Oklahoma_City(547,443),Omaha(546,306),Phoenix(267,518),Pittsburgh(831,261),
    Portland(84,210), Raleigh(865,375),Saint_Louis(655,358),Salt_Lake_City(268,343),
    San_Francisco(70,405),Santa_Fe(392,466), Sault_St_Marie(704,147),Seattle(105,159),
    Toronto(813,169),Vancouver(109,107),Washington(923,306),Winnipeg(465,98);
    double x;
    double y;
    public static final double EDGE_X = 1024;
    public static final double EDGE_Y = 683;
    City(double x, double y)
    {
        this.x = x/EDGE_X;
        this.y = (y/EDGE_Y);
    }

    public double x() {
        return x;
    }
    public double y()
    {
        return y;
    }

    @Override
    public String toString() {
        return this.name() + ": (" + x() + "," + y() + ")";
    }

    public static void main(String args[])
    {
        System.out.println(Arrays.toString(City.values()));
    }
}
