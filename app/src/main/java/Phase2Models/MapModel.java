package Phase2Models;

import java.util.EnumMap;
import java.util.Observable;

import Models.MainModel;
import Models.Player;

public class MapModel extends Observable {
    City selectedCity;
    public MapModel(){
        selectedCity = City.Kansas_City;
    }

    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
        setChanged();
        notifyObservers(this);
    }

    public City[] getCities(){
        return City.values();
    }

    public Route[] getRoutes(){
        return Route.values();
    }

    public EnumMap<Route, Player> getClaimedRoutes(){
        return MainModel.get().getGame().getActiveGame().getRouteOwners();
    }

    @Override
    public String toString() {
        return "MapModel{" +
                "selectedCity=" + selectedCity +
                '}';
    }
}
