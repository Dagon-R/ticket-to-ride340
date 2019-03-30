package Phase2Models;

import Models.MainModel;

import static Phase2Models.City.*;
import static Phase2Models.TrainCardColor.*;

public enum Route {
    VANC_CALV(Vancouver, Calgary, RAINBOW,3),VANC_SEAT(Vancouver,Seattle,RAINBOW, RAINBOW,1),
    SEAT_CALG(Seattle,Calgary,RAINBOW,4), SEAT_HELENA(Seattle,Helena,YELLOW,6),
    SEAT_PORT(Seattle,Portland,RAINBOW,RAINBOW,1), CALG_HELENA(Calgary,Helena,RAINBOW,4),
    CALG_WINN(Calgary,Winnipeg,WHITE,6),HEL_WINN(Helena,Winnipeg,BLUE,4),
    HEL_DULUTH(Helena,Duluth,ORANGE,6),HEL_OMAHA(Helena,Omaha,RED,5),
    HEL_DENVER(Helena,Denver,GREEN,4),HEL_SLC(Helena,Salt_Lake_City,PURPLE,3),
    PORT_SLC(Portland,Salt_Lake_City,BLUE,6),PORT_SF(Portland,San_Francisco,PURPLE,GREEN,5),
    WINN_SAULT(Winnipeg,Sault_St_Marie,RAINBOW,6), WINN_DULUTH(Winnipeg,Duluth,BLACK,4),
    DUL_SAULT(Duluth,Sault_St_Marie,RAINBOW,3), DUL_TORONTO(Duluth,Toronto,PURPLE,6),
    DUL_CHICAGO(Duluth,Chicago,RED,3), DUL_OMAHA(Duluth,Omaha,RAINBOW,RAINBOW,2),
    OMAHA_CHICAGO(Omaha,Chicago,BLUE,4),OMAHA_KC(Omaha,Kansas_City,RAINBOW,RAINBOW,1),
    OMAHA_DEN(Omaha,Denver,PURPLE,4),DENV_KC(Denver,Kansas_City,BLACK,ORANGE,4),
    DENV_OKC(Denver,Oklahoma_City,RED,4),DENV_SANTA(Denver,Santa_Fe,RAINBOW,2),
    DENV_PHX(Denver,Phoenix,WHITE,5),DENV_SLC(Denver,Salt_Lake_City,RED,YELLOW,3),
    SLC_LAS(Salt_Lake_City,Las_Vegas,ORANGE,3),SLC_SFRA(Salt_Lake_City,San_Francisco,ORANGE,WHITE,5),
    SFRA_LAX(San_Francisco,Los_Angeles,PURPLE,YELLOW,3),SAULT_MONT(Sault_St_Marie,Montreal,BLACK,5),
    SAULT_TOR(Sault_St_Marie,Toronto,RAINBOW,2), TOR_MONT(Toronto,Montreal,RAINBOW,3),
    TOR_PITT(Toronto,Pittsburgh,RAINBOW,2), TOR_CHICAGO(Toronto,Chicago,WHITE,4),
    CHICAGO_PITT(Chicago,Pittsburgh,ORANGE,BLACK,3),CHICAGO_STLOU(Chicago,Saint_Louis,GREEN,WHITE,2),
    KC_STLOU(Kansas_City,Saint_Louis,BLUE,PURPLE,2),KC_OKC(Kansas_City,Oklahoma_City,RAINBOW,RAINBOW,2),
    OKC_LROCK(Oklahoma_City,Little_Rock,RAINBOW,2), OKC_DALLAS(Oklahoma_City,Dallas,RAINBOW,RAINBOW,2),
    OKC_ELPASO(Oklahoma_City,El_Paso,YELLOW,5),OKC_SANTA(Oklahoma_City,Santa_Fe,BLUE,3),
    SANTA_ELPASO(Santa_Fe,El_Paso,RAINBOW,2),SANTA_PHX(Santa_Fe,Phoenix,RAINBOW,3),
    PHX_ELPASO(Phoenix,El_Paso,RAINBOW,3),PHX_LAX(Phoenix,Los_Angeles,RAINBOW,3),
    LAX_VEGAS(Los_Angeles,Las_Vegas,RAINBOW,2),LAX_ELPASO(Los_Angeles,El_Paso,BLACK,6),
    MONT_BOSTON(Montreal,Boston,RAINBOW,RAINBOW,2),MONT_NYC(Montreal,New_York,BLUE,3),
    PITT_NYC(Pittsburgh,New_York,WHITE,GREEN,2),PITT_DC(Pittsburgh,Washington,RAINBOW,2),
    PITT_RAL(Pittsburgh,Raleigh,RAINBOW,2),PITT_NASH(Pittsburgh,Nashville,YELLOW,4),
    PITT_STLOU(Pittsburgh,Saint_Louis,GREEN,5),STLOU_NASH(Saint_Louis,Nashville,RAINBOW,2),
    STLOU_LROCK(Saint_Louis,Little_Rock,RAINBOW,2),LROCK_NASH(Little_Rock,Nashville,WHITE,3),
    LROCK_ORLEANS(Little_Rock,New_Orleans,GREEN,3),LROCK_DALLAS(Little_Rock,Dallas,RAINBOW,2),
    DALLAS_HOUSTON(Dallas,Houston,RAINBOW,RAINBOW,1),DALLAS_ELPASO(Dallas,El_Paso,RED,4),
    ELPASO_HOUSTON(El_Paso,Houston,GREEN,6),BOSTON_NYC(Boston,New_York,YELLOW,RED,2),
    NYC_DC(New_York,Washington,ORANGE,BLACK,2),DC_RAL(Washington,Raleigh,RAINBOW,RAINBOW,2),
    RAL_CHAR(Raleigh,Charleston,RAINBOW,2),RAL_ATL(Raleigh,Atlanta,RAINBOW,RAINBOW,2),
    NASH_ATL(Nashville,Atlanta,RAINBOW,1),ORLEANS_HOUSTON(New_Orleans,Houston,RAINBOW,2),
    ORLEANS_ATL(New_Orleans,Atlanta,YELLOW,ORANGE,4),OLEANS_MIAMI(New_Orleans,Miami,RED,6),
    CHAR_MIAMI(Charleston,Miami,PURPLE,4),CHAR_ATL(Charleston,Atlanta,RAINBOW,2),
    ATL_MIAMI(Atlanta,Miami,BLUE,5);

    boolean isDouble;
    City city1;
    City city2;
    TrainCardColor color1;
    TrainCardColor color2;
    int length;
    Route(City firstCity, City secondCity, TrainCardColor type, int length)
    {
        isDouble = false;
        city1 = firstCity;
        city2 = secondCity;
        color1 = type;
        this.length = length;
    }
    Route(City firstCity, City secondCity, TrainCardColor type1, TrainCardColor type2, int length)
    {
        isDouble = true;
        city1 = firstCity;
        city2 = secondCity;
        color1 = type1;
        color2 = type2;
        this.length = length;
    }


    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
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
    public TrainCardColor getColor1() {
        return color1;
    }

    public TrainCardColor getColor2() {return color2;}

    public int getLength() {
        return length;
    }

    public boolean isDouble() {return isDouble;}

    @Override
    public String toString() {
        return "[" + city1 + " to " + city2 + ", " + color1.getName() + ", " + length + "]";
    }

    static public Route getRoute(City city1, City city2){
        for(Route route : Route.values()){
//            System.out.println(route.getCity1() +" " + route.getCity2());
            if(((route.getCity1().equals(city1)) || route.getCity2().equals(city1)) &&
                    (route.getCity1().equals(city2) || route.getCity2().equals(city2)) &&
                    !MainModel.get().getGame().getActiveGame().getRouteOwners().containsKey(route)){
                return route;
            }
        }
        return null;
    }
}
