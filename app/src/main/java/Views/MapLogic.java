package Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import Models.CityLoc;

public class MapLogic extends View {
    Paint paint;
    int i=0;
    Canvas canvas;
    CityLoc[] cityLocs;
    public MapLogic(Context context, CityLoc[] cityLocs){
        super(context);
//        new View(context);
        paint = new Paint();
        this.cityLocs = cityLocs;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        int x = getWidth();
        int y = getHeight();
        int radius;
        radius = 4;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        System.out.println(x +" " + y);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor("#CD5C5C"));
        for(CityLoc cityLoc : cityLocs){
//            System.out.println(cityLoc.getLat()*x +" "+ cityLoc.getLon()*y);
            canvas.drawCircle(cityLoc.getLat()*x, cityLoc.getLon()*y, radius, paint);
        }


    }
}
