package Phase2Models;

public class MapModel {
    City selectedCity;
    public MapModel(){
        selectedCity = City.Kansas_City;
    }

    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }
}
