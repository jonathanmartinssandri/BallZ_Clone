package ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game

import android.graphics.Canvas
import android.graphics.Color
import android.util.Log

/**
 * Created by LuisCopetti on 02/07/2017.
 */
class GameManager : GameLoop {

    var rgbValue = 0.0f

    override fun update(delta: Float) {
        rgbValue += delta * 100;
    }

    override fun draw(canvas: Canvas) {
        if (rgbValue > 255.0f)
            rgbValue = 0.0f
        val intRGBValue :Int = rgbValue.toInt()
        canvas.drawColor(Color.rgb(intRGBValue, intRGBValue, intRGBValue))
    }
}