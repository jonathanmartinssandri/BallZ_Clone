package ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import ballzclone.copetti.com.ballzclone.objects.Ball

/**
 * Created by LuisCopetti on 02/07/2017.
 */
class GameManager : GameLoop {

    var rgbValue = 0.0f
    var ball = Ball()

    override fun update(delta: Float) {
        rgbValue += delta * 100;
        ball.update(delta)
    }

    override fun draw(canvas: Canvas) {
        if (rgbValue > 255.0f)
            rgbValue = 0.0f
        val intRGBValue :Int = rgbValue.toInt()
        canvas.drawColor(Color.rgb(intRGBValue, intRGBValue, intRGBValue))

        var paint = Paint()
        paint.strokeMiter = 1.0f
        paint.color = Color.rgb(240, 240, 240)

        canvas.drawCircle(canvas.width / 2.0f, 0.0f, 25.0f, paint)
        canvas.drawCircle(canvas.width / 2.0f, canvas.height.toFloat(), 25.0f, paint)

        ball.draw(canvas)

    }
}