package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

/**
 * Created by Pichau on 06/07/2017.
 */
class Square(sideSize: Float) : GameObject(sideSize.toFloat()) {

    override fun update(delta: Float) {
    }

    override fun draw(canvas: Canvas) {

        val paint = Paint().apply { color = Color.rgb(0, 255, 0); }
        canvas.drawRect(getBZRect().asRect(), paint)
    }

    override fun collidedWith(gameObject: GameObject) {
    }
}