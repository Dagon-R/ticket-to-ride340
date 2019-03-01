package Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.Arrays;

import Models.CityLoc;
import Phase2Models.City;
import Phase2Models.MapModel;
import Phase2Models.Route;
import Phase2Models.TrainCardColor;

public class MapLogic extends View {
    Paint paint;
    Canvas canvas;
    float radius;
    float selectedRadius;

    public MapLogic(Context context){
        super(context);
        paint = new Paint();
        radius =15;
        selectedRadius = 20;
//        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
//        this.setBackgroundColor(Color.TRANSPARENT);

        drawMap(new MapModel());
    }

    protected void drawMap(MapModel map){
        City selectedCity = map.getSelectedCity();
        drawBackground();

        drawRoutes();
        drawCities();
        drawSelectedCity(selectedCity);



    }

    private void drawSelectedCity(City city){
        if(city == null) return;
        PointF point = getPoint(city);
        paint.setColor(Color.argb(255,0,0,255));
        paint.setStyle(Paint.Style.FILL);
        System.out.println("::::::: "+point);

        canvas.drawCircle(point.x,point.y,radius,paint);
    }

    private void drawRoutes(){
        for(Route route : Route.values()){
            if(route.isDouble()){
                drawDoubleRoute(route);
                continue;
            }
            drawSingleRoute(route);


        }
    }

    private void drawSingleRoute(Route route){
        setColor(route.getColor());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);

        PointF point1 = getPoint(route.getCity1());
        PointF point2 = getPoint(route.getCity2());
        float dist = (float)Math.pow(Math.pow(point2.x-point1.x,2) + Math.pow(point1.y-point2.y,2),.5);
        float[] intervals = getIntervals(point1,point2,route.getLength(),dist);


        paint.setPathEffect(new DashPathEffect(intervals, dist/(route.getLength() * 2 +1)));
        canvas.drawLine(point1.x,point1.y,point2.x,point2.y,paint);
    }

    private void drawDoubleRoute(Route route){
        PointF pointOrigin1 = getPoint(route.getCity1());
        PointF pointOrigin2 = getPoint(route.getCity2());
        float dist = (float)Math.pow(Math.pow(pointOrigin2.x-pointOrigin1.x,2) + Math.pow(pointOrigin2.y-pointOrigin1.y,2),.5);
        PointF normal = new PointF();
        normal.x = pointOrigin1.x - pointOrigin2.x;
        normal.y = pointOrigin1.y - pointOrigin2.y;

        float angle = (float)Math.atan2(normal.y,normal.x);
        angle += Math.PI/2;

        PointF point1L = pointOrigin1;
        PointF point1R = pointOrigin1;
        PointF point2L = pointOrigin2;
        PointF point2R = pointOrigin2;

        PointF leftChange;




//        float[] intervals = getIntervals(point1,point2,route.getLength(),dist);
    }

    private float[] getIntervals(PointF point1, PointF point2, int length, float dist){

        float[] intervals = new float[2];
        intervals[0] = dist/(length * 2 +1);
        intervals[1] = intervals[0];
        return intervals;
    }

    private PointF getPoint(City city){
        float x = (float) (city.x())*canvas.getWidth();
        float y = (float) (city.y())*canvas.getHeight();
        return new PointF(x,y);
    }
    private void setColor(TrainCardColor color){

        switch (color){
            case BLUE:
                paint.setColor(Color.argb(255,255,255,255));
                break;
            case RED:
                paint.setColor(Color.RED);
                break;
            case BLACK:
                paint.setColor(Color.BLACK);
                break;
            case GREEN:
                paint.setColor(Color.GREEN);
                break;
            case WHITE:
                paint.setColor(Color.WHITE);
                break;
            case ORANGE:
                paint.setColor(Color.GRAY);
                break;
            case PURPLE:
                paint.setColor(Color.MAGENTA);
                break;
            case YELLOW:
                paint.setColor(Color.YELLOW);
                break;
            case RAINBOW:
                paint.setColor(Color.LTGRAY);
                break;
        }
    }
    private void drawCities(){
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

        for(City city: City.values()){

            PointF point = getPoint(city);

            canvas.drawCircle(point.x,point.y,radius,paint);
            paint.setTextSize(20);
            canvas.drawText(city.name(),point.x -radius,point.y-radius,paint);

        }
    }
    private void drawBackground(){
        Drawable d = getResources().getDrawable(R.drawable.stars4, null);
        d.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        d.draw(canvas);
    }
}
