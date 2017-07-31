package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import ballzclone.copetti.com.ballzclone.GameDefine

/**
 * Created by Pichau on 09/07/2017.
 */
class BallPowerUp : GameObject(GameDefine.ball_powerup_radius) {

    val minSinAnimation = 0.6f
    val maxSinAnimation = 0.9f

    var elapsedTime: Double = 0.0

    init {
        collidable = false
    }

    override fun update(delta: Float) {
        elapsedTime += delta * 3
    }

    override fun draw(canvas: Canvas) {

        drawTo(canvas, 0.5f, createDefaultPaint())
        drawTo(canvas, getSinRadius(), createDefaultPaint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 2.0f
        })
    }

    private fun getSinRadius() : Float {
        val result = Math.pow(Math.sin(elapsedTime), 2.0)
        return result.toFloat() * (maxSinAnimation - minSinAnimation) + minSinAnimation
    }

    private fun createDefaultPaint() : Paint {
        return Paint().apply {
            color = Color.rgb(180, 180, 180)
        }
    }

    private fun drawTo(canvas: Canvas, radius: Float, paint: Paint) {
        val center = getBZRect().centerPoint()
        canvas.drawCircle(center.x, center.y, GameDefine.ball_powerup_radius * radius, paint)
    }

    override fun collidedWith(gameObject: GameObject) {
        if (!(gameObject is Ball))
                return

        gameObject.cannonParent.addTotalBalls(+1)
        die()
    }
}