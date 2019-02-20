package Phase2Models;

import static Phase2Models.City.*;

public enum DestinationCard {
    LAX_NYC(Los_Angeles, New_York,21),DUL_HOUSTON(Duluth,Houston,8),
    SAULT_NASH(Sault_St_Marie,Nashville,8),NYC_ATL(New_York,Atlanta,6),
    PORT_NASH(Portland,Nashville,17),VAN_MONT(Vancouver,Montreal,20),
    DUL_ELPASO(Duluth,El_Paso,10),TOR_MIAMI(Toronto,Miami,10),
    PORT_PHX(Portland,Phoenix,11),DAL_NYC(Dallas,New_York,11),
    CAL_SLC(Calgary,Salt_Lake_City,7),CAL_PHX(Calgary,Phoenix,13),
    LAX_MIAMI(Los_Angeles,Miami,20),WINN_LROCK(Winnipeg,Little_Rock,11),
    SFRAN_ATL(San_Francisco,Atlanta,17),KC_HOUSTON(Kansas_City,Houston,5),
    LAX_CHICAGO(Los_Angeles,Chicago,16),DENV_PITT(Denver,Pittsburgh,11),
    CHICAGO_SANTA(Chicago,Santa_Fe,9),VAN_SANTA(Vancouver,Santa_Fe,13),
    BOSTON_MIAMI(Boston,Miami,12),CHICAGO_ORLEANS(Chicago,New_Orleans,7),
    MONT_ATL(Montreal,Atlanta,9),SEAT_NYC(Seattle,New_York,22),
    DENV_ELPASO(Denver,El_Paso,4),HEL_LAX(Helena,Los_Angeles,8),
    WINN_HOUSTON(Winnipeg,Houston,12),MONT_ORLEANS(Montreal,New_Orleans,13),
    SAULT_OKC(Sault_St_Marie,Oklahoma_City,9),SEAT_LAX(Seattle,Los_Angeles,9)
    ;
    int value;
    City city1;
    City city2;
    DestinationCard(City firstCity, City secondCity, int points)
    {
        this.city1 = firstCity;
        this.city2 = secondCity;
        this.value = points;
    }

    public boolean has(City city)
    {
        return city1 == city || city2 == city;
    }
    public City otherThan(City city)
    {
        if (city1 == city) {return city2;}
        else if (city2 == city) {return city1;}
        else {System.out.print("City not part of route"); return null;}
    }

    public int getValue() {
        return value;
    }
}
