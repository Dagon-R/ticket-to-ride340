package States;

import android.graphics.PointF;
import android.util.Log;

import Models.MainModel;
import Presenters.UtilPresenter;

public interface IMapPresenter {
    void selectCity(float x, float y, PointF size);

}
