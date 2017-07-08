package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import ballzclone.copetti.com.ballzclone.BZRect

/**
 * Created by Pichau on 04/07/2017.
 */
abstract class Wall(rect: BZRect, orientation: Orientation) : GameObject(rect) {

    enum class Orientation {
        VERTICAL, HORIZONTAL
    }

    private var orientation: Orientation = orientation


    override fun update(delta: Float) { }

    override fun draw(canvas: Canvas) {
        val paint = Paint().apply {
            color = Color.rgb(0, 255, 255)
            strokeMiter = 2.0f
            strokeWidth = 5.0f
        }
        canvas.drawLine(0.0f, canvas.height.toFloat(), canvas.width.toFloat(), canvas.height.toFloat(), paint)
    }


}
