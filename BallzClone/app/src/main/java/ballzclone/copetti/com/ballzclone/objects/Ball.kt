package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

/**
 * Created by Pichau on 04/07/2017.
 */
class Ball : GameObject() {

    override fun draw(canvas: Canvas) {

        var paint = Paint().apply {
            color = Color.rgb(255, 0, 0)
        }
        canvas.drawCircle(x, y, 10.0f, paint)
    }

    override fun update(delta: Float) {
        x += delta * 100
        y += delta * 125
        if (x >= 500)
            x = 0.0f
        if (y >= 500)
            y = 0.0f
    }


}