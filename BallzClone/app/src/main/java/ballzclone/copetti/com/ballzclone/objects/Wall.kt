package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import ballzclone.copetti.com.ballzclone.BZRect

/**
 * Created by Pichau on 04/07/2017.
 */
class Wall(rect: BZRect) : GameObject(rect) {

    override fun update(delta: Float) { }

    override fun draw(canvas: Canvas) {
        val paint = Paint().apply {
            color = Color.rgb(0, 255, 255)
            strokeMiter = 2.0f
            strokeWidth = 5.0f
        }
        val rect = getBZRect()
        canvas.drawRect(rect.asRect(), paint)
    }


}
