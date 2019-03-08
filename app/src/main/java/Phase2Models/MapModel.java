package Phase2Models;

import java.util.Observable;

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


}
