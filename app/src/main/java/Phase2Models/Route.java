package Phase2Models;
import Phase2Models.City;
import Phase2Models.TrainCardColor;

import static Phase2Models.City.*;
import static Phase2Models.TrainCardColor.*;

public enum Route {
    VANC_CALV(Vancouver, Calgary, RAINBOW,3),VANC_SEAT1(Vancouver,Seattle, RAINBOW,1),
    VANC_SEAT2(Vancouver,Seattle,RAINBOW,1),SEAT_CALG(Seattle,Calgary,RAINBOW,4),
    SEAT_HELENA(Seattle,Helena,YELLOW,6),SEAT_PORT1(Seattle,Portland,RAINBOW,1),
    SEAT_PORT2(Seattle,Portland,RAINBOW,2),CALG_HELENA(Calgary,Helena,RAINBOW,4),
    CALG_WINN(Calgary,Winnipeg,WHITE,6),HEL_WINN(Helena,Winnipeg,BLUE,4),
    HEL_DULUTH(Helena,Duluth,ORANGE,6),HEL_OMAHA(Helena,Omaha,RED,5),
    HEL_DENVER(Helena,Denver,GREEN,4),HEL_SLC(Helena,Salt_Lake_City,PURPLE,3),
    PORT_SLC(Portland,Salt_Lake_City,BLUE,6),PORT_SF1(Portland,San_Francisco,PURPLE,5),
    PORT_SF2(Portland,San_Francisco,GREEN,5),WINN_SAULT(Winnipeg,Sault_St_Marie,RAINBOW,6),
    WINN_DULUTH(Winnipeg,Duluth,BLACK,4),DUL_SAULT(Duluth,Sault_St_Marie,RAINBOW,3),
    DUL_TORONTO(Duluth,Toronto,PURPLE,6),DUL_CHICAGO(Duluth,Chicago,RED,3),
    DUL_OMAHA1(Duluth,Omaha,RAINBOW,2),DUL_OMAHA2(Duluth,Omaha,RAINBOW,2),
    OMAHA_CHICAGO(Omaha,Chicago,BLUE,4),OMAHA_KC1(Omaha,Kansas_City,RAINBOW,1),
    OMAHA_KC2(Omaha,Kansas_City,RAINBOW,1),OMAHA_DEN(Omaha,Denver,PURPLE,4),
    DENV_KC1(Denver,Kansas_City,BLACK,4),DENV_KC2(Denver,Kansas_City,ORANGE,4),
    DENV_OKC(Denver,Oklahoma_City,RED,4),DENV_SANTA(Denver,Santa_Fe,RAINBOW,2),
    DENV_PHX(Denver,Phoenix,WHITE,5),DENV_SLC1(Denver,Salt_Lake_City,RED,3),
    DENV_SLC2(Denver,Salt_Lake_City,YELLOW,3),SLC_LAS(Salt_Lake_City,Las_Vegas,ORANGE,3),
    SLC_SFRA1(Salt_Lake_City,San_Francisco,ORANGE,5),SLC_SFRA2(Salt_Lake_City,San_Francisco,WHITE,5),
    SFRA_LAX1(San_Francisco,Los_Angeles,PURPLE,3),SFRA_LAX2(San_Francisco,Los_Angeles,YELLOW,3),
    SAULT_MONT(Sault_St_Marie,Montreal,BLACK,5),SAULT_TOR(Sault_St_Marie,Toronto,RAINBOW,2),
    TOR_MONT(Toronto,Montreal,RAINBOW,3),TOR_PITT(Toronto,Pittsburgh,RAINBOW,2),
    TOR_CHICAGO(Toronto,Chicago,WHITE,4),CHICAGO_PITT1(Chicago,Pittsburgh,ORANGE,3),
    CHICAGO_PITT2(Chicago,Pittsburgh,BLACK,3),CHICAGO_STLOU1(Chicago,Saint_Louis,GREEN,2),
    CHICAGO_STLOU2(Chicago,Saint_Louis,WHITE,2),KC_STLOU1(Kansas_City,Saint_Louis,BLUE,2),
    KC_STLOU2(Kansas_City,Saint_Louis,PURPLE,2),KC_OKC1(Kansas_City,Oklahoma_City,RAINBOW,2),
    KC_OKC2(Kansas_City,Oklahoma_City,RAINBOW,2),OKC_LROCK(Oklahoma_City,Little_Rock,RAINBOW,2),
    OKC_DALLAS1(Oklahoma_City,Dallas,RAINBOW,2),OKC_DALLAS2(Oklahoma_City,Dallas,RAINBOW,2),
    OKC_ELPASO(Oklahoma_City,El_Paso,YELLOW,5),OKC_SANTA(Oklahoma_City,Santa_Fe,BLUE,3),
    SANTA_ELPASO(Santa_Fe,El_Paso,RAINBOW,2),SANTA_PHX(Santa_Fe,Phoenix,RAINBOW,3),
    PHX_ELPASO(Phoenix,El_Paso,RAINBOW,3),PHX_LAX(Phoenix,Los_Angeles,RAINBOW,3),
    LAX_VEGAS(Los_Angeles,Las_Vegas,RAINBOW,2),LAX_ELPASO(Los_Angeles,El_Paso,BLACK,6),
    MONT_BOSTON1(Montreal,Boston,RAINBOW,2),MONT_BOSTON2(Montreal,Boston,RAINBOW,2),
    MONT_NYC(Montreal,New_York,BLUE,3),PITT_NYC1(Pittsburgh,New_York,WHITE,2),
    PITT_NYC2(Pittsburgh,New_York,GREEN,2),PITT_DC(Pittsburgh,Washington,RAINBOW,2),
    PITT_RAL(Pittsburgh,Raleigh,RAINBOW,2),PITT_NASH(Pittsburgh,Nashville,YELLOW,4),
    PITT_STLOU(Pittsburgh,Saint_Louis,GREEN,5),STLOU_NASH(Saint_Louis,Nashville,RAINBOW,2),
    STLOU_LROCK(Saint_Louis,Little_Rock,RAINBOW,2),LROCK_NASH(Little_Rock,Nashville,WHITE,3),
    LROCK_ORLEANS(Little_Rock,New_Orleans,GREEN,3),LROCK_DALLAS(Little_Rock,Dallas,RAINBOW,2),
    DALLAS_HOUSTON1(Dallas,Houston,RAINBOW,1),DALLAS_HOUSTON2(Dallas,Houston,RAINBOW,1),
    DALLAS_ELPASO(Dallas,El_Paso,RED,4),ELPASO_HOUSTON(El_Paso,Houston,GREEN,6),
    BOSTON_NYC1(Boston,New_York,YELLOW,2),BOSTON_NYC2(Boston,New_York,RED,2),
    NYC_DC1(New_York,Washington,ORANGE,2),NYC_DC2(New_York,Washington,BLACK,2),
    DC_RAL1(Washington,Raleigh,RAINBOW,2),DC_RAL2(Washington,Raleigh,RAINBOW,2),
    RAL_CHAR(Raleigh,Charleston,RAINBOW,2),RAL_ATL1(Raleigh,Atlanta,RAINBOW,2),
    RAL_ATL2(Raleigh,Atlanta,RAINBOW,2),NASH_ATL(Nashville,Atlanta,RAINBOW,1),
    ORLEANS_HOUSTON(New_Orleans,Houston,RAINBOW,2),ORLEANS_ATL1(New_Orleans,Atlanta,YELLOW,4),
    ORLEANS_ATL2(New_Orleans,Atlanta,ORANGE,4),OLEANS_MIAMI(New_Orleans,Miami,RED,6),
    CHAR_MIAMI(Charleston,Miami,PURPLE,4),CHAR_ATL(Charleston,Atlanta,RAINBOW,2),
    ATL_MIAMI(Atlanta,Miami,BLUE,5);

    City city1;
    City city2;
    TrainCardColor color;
    int length;
    Route(City firstCity, City secondCity, TrainCardColor type, int length)
    {
        city1 = firstCity;
        city2 = secondCity;
        color = type;
        this.length = length;
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
    public TrainCardColor getColor() {
        return color;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "[" + city1 + " to " + city2 + ", " + color.getName() + ", " + length + "]";
    }
}
