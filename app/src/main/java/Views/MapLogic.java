package Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

import Models.CityLoc;

public class MapLogic extends View {
    Paint paint;
    Canvas canvas;

    public MapLogic(Context context){
        super(context);
        paint = new Paint();
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        Drawable d = getResources().getDrawable(R.drawable.game_map, null);

        d.draw(canvas);
//        R.drawable.gameMap;
    }
}
