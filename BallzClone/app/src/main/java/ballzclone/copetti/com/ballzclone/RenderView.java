package ballzclone.copetti.com.ballzclone;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by LuisCopetti on 29/06/2017.
 */

public class RenderView extends View {

    private Rect src = new Rect();
    private Rect dst = new Rect();
    private Bitmap bird;

    public RenderView(Context context) {
        super(context);
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open("bird.png");
            bird = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erro ao carregar asset: bird.png", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap image = Bitmap.createBitmap(100, 100, Bitmap.Config.RGB_565);
        Canvas imageCanvas = new Canvas(image);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        imageCanvas.drawCircle(image.getWidth() / 2, image.getHeight() / 2, 50, paint);
        canvas.drawBitmap(bird, 100, 100, null);
        canvas.drawBitmap(image, 300, 300, null);
        //dst.set(300, 100, 700, 900);
        dst.set(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(bird, null, dst, null);

        src.set(0, 0, 75, 75);
        dst.set(canvas.getWidth() - 150, canvas.getHeight() - 150, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(bird, src, dst, null);
    }

}
