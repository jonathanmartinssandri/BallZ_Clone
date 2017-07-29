package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log

/**
 * Created by Pichau on 04/07/2017.
 */
class Ball(cannonParent: BallCannon, radius: Float) : GameObject(radius) {
    var  moving: Boolean = true

    var cannonParent = cannonParent
        private set

    override fun draw(canvas: Canvas) {

        var paint = Paint().apply {
            color = Color.rgb(255, 0, 0)
        }

        val center = getBZRect().centerPoint()
        canvas.drawCircle(center.x, center.y, size.y / 2.0f, paint)
    }

    override fun update(delta: Float) {
        if (!moving) return

        pos += vel * delta
    }

    override fun collidedWith(gameObject: GameObject) {
    }

}