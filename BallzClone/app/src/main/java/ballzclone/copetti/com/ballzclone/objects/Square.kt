package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect

/**
 * Created by Pichau on 06/07/2017.
 */
class Square(sideSize: Int) : GameObject() {

    private val sideSize = sideSize

    override fun update(delta: Float) {
    }

    override fun draw(canvas: Canvas) {

        val drawingRect = Rect(pos.x.toInt(), pos.y.toInt(), pos.x.toInt() + sideSize, pos.y.toInt() + sideSize)
        val paint = Paint().apply { color = Color.rgb(0, 255, 0); }
        canvas.drawRect(drawingRect, paint)
    }

    override fun collidedWith(gameObject: GameObject) {
    }
}