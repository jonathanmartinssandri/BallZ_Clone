package ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import ballzclone.copetti.com.ballzclone.BZVector2f
import ballzclone.copetti.com.ballzclone.collision.CollisionManager
import ballzclone.copetti.com.ballzclone.objects.*

/**
 * Created by LuisCopetti on 02/07/2017.
 */
class GameManager : GameLoop {


    var collisionManager = CollisionManager()
    var rgbValue = 0.0f
    var balls = arrayListOf<GameObject>()

    init {
        for (i in 1..1) {
            balls.add(Ball(10.0f).apply {
                getPosition().set(20.0f * i, 40.0f * i)
                getVelocity().set(2f, 5f)
            })
        }

        balls.add(Ball(10.0f).apply { getPosition().set(480.0f, 640.0f); moving = false })

        balls.add(HorizontalWall(BZVector2f(0.0f, 640.0f), 480.0f))
        balls.add(Square(50.0f).apply { getPosition().set(200.0f, 200.0f) })
    }

    override fun update(delta: Float) {
        rgbValue += delta * 100;
        balls.forEach { it.update(delta) }
        collisionManager.update(balls)
    }

    override fun draw(canvas: Canvas) {
        if (rgbValue > 255.0f)
            rgbValue = 0.0f
        val intRGBValue :Int = rgbValue.toInt()
        canvas.drawColor(Color.rgb(intRGBValue, intRGBValue, intRGBValue))

        var paint = Paint()
        paint.strokeMiter = 1.0f
        paint.color = Color.rgb(240, 240, 240)

        canvas.drawCircle(canvas.width / 2.0f, 0.0f, 25.0f, paint)
        canvas.drawCircle(canvas.width / 2.0f, canvas.height.toFloat(), 25.0f, paint)

        balls.forEach { it.draw(canvas) }

    }
}