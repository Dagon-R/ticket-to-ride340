package Presenters;

import android.graphics.PointF;

import Phase2Models.City;

public class MapEquations {

    public static PointF getPoint(City city, PointF canvas){

        float x = (float) city.x()*canvas.x;
        float y = (float) city.y()*canvas.y;
        return new PointF(x,y);

    }
}
