package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

/**
 * Created by Pichau on 04/07/2017.
 */
class Wall : GameObject() {

    override fun update(delta: Float) { }

    override fun collidedWith(gameObject: GameObject) {
        gameObject.y = 0.0f
    }

    override fun draw(canvas: Canvas) {
        val paint = Paint().apply {
            color = Color.rgb(0, 255, 255)
            strokeMiter = 2.0f
            strokeWidth = 5.0f
        }
        canvas.drawLine(0.0f, canvas.height.toFloat(), canvas.width.toFloat(), canvas.height.toFloat(), paint)
    }


}
