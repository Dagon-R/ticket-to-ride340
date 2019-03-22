package Phase3Services;

import Phase2Models.TrainCardColor;
import Services.Service;

public class DrawTrainsService implements Service {
    @Override
    public void connectToProxy(Object... obj) {

    }

    @Override
    public void doService(Object... obj) {
        String ipAddress = (String) obj[0];
        int pos1 = (int) obj[1];
        int pos2 = (int) obj[2];
        TrainCardColor color1 = (TrainCardColor) obj[3];
        TrainCardColor color2 = (TrainCardColor) obj[4];

    }
}
