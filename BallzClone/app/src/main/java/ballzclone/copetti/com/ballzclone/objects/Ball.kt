package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import ballzclone.copetti.com.ballzclone.BZVector2f

/**
 * Created by Pichau on 04/07/2017.
 */
class Ball : GameObject() {
    var  moving: Boolean = true

    override fun draw(canvas: Canvas) {

        var paint = Paint().apply {
            color = Color.rgb(255, 0, 0)
        }
        canvas.drawCircle(getPosition().x, getPosition().y, 10.0f, paint)
    }

    override fun update(delta: Float) {
        if (!moving) return

        point.add(delta * 50, delta* 120)
    }

    override fun collidedWith(gameObject: GameObject) {
        Log.d("GameDev", "Ball collision")
    }

}