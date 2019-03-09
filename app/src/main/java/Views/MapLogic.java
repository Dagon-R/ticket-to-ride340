package Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import Phase2Models.City;
import Phase2Models.MapModel;
import Phase2Models.Route;
import Phase2Models.TrainCardColor;
import Presenters.MapEquations;
import Views.ViewInterfaces.IMap;

public class MapLogic extends View {
    static String TAG = "MapLogic";
    Paint paint;
    Canvas canvas;
    PointF size;
    float radius;
    float selectedRadius;
    float routeThickness;
    IMap mapActivity;

    public MapLogic(Context context, AttributeSet attrs, Canvas canvas) { //TODO: fix!
        super(context, attrs);
        this.canvas = canvas;
        this.mapActivity = mapActivity;
        paint = new Paint();
        radius =15;
        selectedRadius = 20;
        routeThickness =15;
//        this.invalidate();
    }

    public void setIMap(IMap map){
        mapActivity = map;
    }


    public MapLogic(Context context, IMap mapActivity){
        super(context);

    }

    public void updateMap(MapModel model){

        drawMap(model);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        this.size = new PointF(canvas.getWidth(),canvas.getHeight());
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
        PointF point = MapEquations.getPoint(city,size);
        setPaintParams(getResources().getColor(R.color.lightBlue),null,null);
        canvas.drawCircle(point.x,point.y,radius,paint);
        setPaintParams(getResources().getColor(R.color.red), Paint.Style.STROKE,null);
        paint.setStrokeWidth(5);
        canvas.drawCircle(point.x,point.y,radius*1.5f,paint);
    }

    private void setPaintParams(Integer color, Paint.Style style, PathEffect pathEffect){
        if(style == null){
            paint.setStyle(Paint.Style.FILL);
        }else{
            paint.setStyle(style);
        }
        if(color == null){
            paint.setColor(Color.BLACK);
        } else{
            paint.setColor(color);
        }

        if(pathEffect == null){
            float[] intervals = new float[2];
            intervals[0] =1;
            paint.setPathEffect(new DashPathEffect(intervals,0));
        }else{
            paint.setPathEffect(pathEffect);
        }


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

        PointF point1 = MapEquations.getPoint(route.getCity1(),size);
        PointF point2 = MapEquations.getPoint(route.getCity2(),size);
        float dist = (float)Math.pow(Math.pow(point2.x-point1.x,2) + Math.pow(point1.y-point2.y,2),.5);
        float[] intervals = getIntervals(point1,point2,route.getLength(),dist);



        paint.setStyle(Paint.Style.STROKE);
        colorMeBlack(point1,point2,dist,route,intervals,route.getColor1());
//        if(route.getColor1() == TrainCardColor.BLACK){
//            intervals[0] +=4f;
//            intervals[1] -=4f;
//            paint.setStrokeWidth(20);
//            paint.setPathEffect(new DashPathEffect(intervals, dist/(route.getLength() * 2 +1) +2));
//            paint.setColor(getResources().getColor(R.color.grey));
//            paint.setStyle(Paint.Style.STROKE);
//            canvas.drawLine(point1.x,point1.y,point2.x,point2.y,paint);
//            intervals[0] -=4f;
//            intervals[1] +=4f;
//        }
        paint.setPathEffect(new DashPathEffect(intervals, dist/(route.getLength() * 2 +1)));
        setColor(route.getColor1());
        paint.setStrokeWidth(15);
        canvas.drawLine(point1.x,point1.y,point2.x,point2.y,paint);
    }

    private void colorMeBlack(PointF point1, PointF point2,float dist,Route route,float[] intervals , TrainCardColor color){
        if(color == TrainCardColor.BLACK){
            intervals[0] +=4f;
            intervals[1] -=4f;
            paint.setStrokeWidth(20);
            paint.setPathEffect(new DashPathEffect(intervals, dist/(route.getLength() * 2 +1) +2));
            paint.setColor(getResources().getColor(R.color.grey));
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(point1.x,point1.y,point2.x,point2.y,paint);
            intervals[0] -=4f;
            intervals[1] +=4f;
        }
    }

    private void drawDoubleRoute(Route route){

        PointF pointOrigin1 = MapEquations.getPoint(route.getCity1(),size);
        PointF pointOrigin2 = MapEquations.getPoint(route.getCity2(),size);
        float dist = (float)Math.pow(Math.pow(pointOrigin2.x-pointOrigin1.x,2) + Math.pow(pointOrigin2.y-pointOrigin1.y,2),.5);
        float angle = (float)Math.atan2(pointOrigin1.y - pointOrigin2.y,pointOrigin1.x - pointOrigin2.x);
        angle+=Math.PI/2;
        float x = (float)Math.cos(angle)*(routeThickness/1.5f);
        float y = (float)Math.sin(angle)*(routeThickness/1.5f);


        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        float[] intervals = getIntervals(pointOrigin1,pointOrigin2,route.getLength(),dist);


        paint.setPathEffect(new DashPathEffect(intervals, dist/(route.getLength() * 2 +1)));
        PointF point1 = new PointF(pointOrigin1.x,pointOrigin1.y);
        point1.x +=x;
        point1.y +=y;
        PointF point2 = new PointF(pointOrigin2.x,pointOrigin2.y);
        point2.x +=x;
        point2.y +=y;
        colorMeBlack(point1,point2,dist,route,intervals,route.getColor1());
        setColor(route.getColor1());
        canvas.drawLine(pointOrigin1.x + x,pointOrigin1.y +y,pointOrigin2.x + x,pointOrigin2.y +y,paint);

        point1 = new PointF(pointOrigin1.x,pointOrigin1.y);
        point1.x -=x;
        point1.y -=y;
        point2 = new PointF(pointOrigin2.x,pointOrigin2.y);
        point2.x -=x;
        point2.y -=y;
        Log.d(TAG, "drawDoubleRoute: color" + route.getColor2());
        colorMeBlack(point1,point2,dist,route,intervals,route.getColor2());
        setColor(route.getColor2());
        canvas.drawLine(pointOrigin1.x - x,pointOrigin1.y -y,pointOrigin2.x - x,pointOrigin2.y -y,paint);


    }

    private float[] getIntervals(PointF point1, PointF point2, int length, float dist){

        float[] intervals = new float[2];
        intervals[0] = dist/(length * 2 +1);
        intervals[1] = intervals[0];
        return intervals;
    }


    private void setColor(TrainCardColor color){

        switch (color){
            case BLUE:
                paint.setColor(getResources().getColor(R.color.blue));
                break;
            case RED:

                paint.setColor(getResources().getColor(R.color.red));
                break;
            case BLACK:
                paint.setColor(Color.BLACK);
                break;
            case GREEN:
                paint.setColor(getResources().getColor(R.color.green));
                break;
            case WHITE:
                paint.setColor(Color.WHITE);
                break;
            case ORANGE:
                paint.setColor(getResources().getColor(R.color.orange));
                break;
            case PURPLE:
                paint.setColor(getResources().getColor(R.color.purple));
                break;
            case YELLOW:
                paint.setColor(getResources().getColor(R.color.yellow));
                break;
            case RAINBOW:
                paint.setColor(getResources().getColor(R.color.rainbow));
                break;
        }
        paint.setAlpha(255);
    }
    private void drawCities(){
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

        for(City city: City.values()){

            PointF point = MapEquations.getPoint(city,size);
            paint.setColor(getResources().getColor(R.color.grey));
            canvas.drawCircle(point.x,point.y,radius,paint);
            paint.setTextSize(24);
            paint.setColor(Color.WHITE);
            paint.setAlpha(255);
            StringBuilder name = new StringBuilder(city.name());
            if(city.name().indexOf('_') != -1){
                name = name.replace(city.name().indexOf('_'),city.name().indexOf('_')," ");
            }


            canvas.drawText(name.toString(),point.x -radius*3,point.y-radius*2,paint);

        }
    }
    private void drawBackground(){
        Drawable d = getResources().getDrawable(R.drawable.cool_stars, null);
        d.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        d.draw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
//        System.out.println(event.getX());
        mapActivity.mapClick(event.getX(),event.getY(),size);
        return false;
    }

}
