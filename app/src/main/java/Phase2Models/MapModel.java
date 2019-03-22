package Phase2Models;

import java.util.EnumMap;
import java.util.Observable;

import Models.Player;
import Models.MainModel;

public class MapModel extends Observable {
    private City selectedCity;
    private Route selectedRoute;
    public MapModel(){
        selectedCity = City.Kansas_City;
        selectedRoute = null;
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

    public void setSelectedRoute(Route route){
        selectedRoute = route;
    }

    public boolean hasRoute(){
        return selectedRoute == null;
    }

    public Route getSelectedRoute(){
        return selectedRoute;
    }

    public EnumMap<Route, Player[]> getClaimedRoutes(){
        return MainModel.get().getGame().getActiveGame().getRouteOwners();
    }

    @Override
    public String toString() {
        return "MapModel{" +
                "selectedCity=" + selectedCity +
                '}';
    }
}
