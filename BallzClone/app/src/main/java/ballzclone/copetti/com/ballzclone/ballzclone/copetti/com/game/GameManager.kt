package ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import ballzclone.copetti.com.ballzclone.BZVector2f
import ballzclone.copetti.com.ballzclone.GameDefine
import ballzclone.copetti.com.ballzclone.GameObjectManager
import ballzclone.copetti.com.ballzclone.collision.CollisionManager
import ballzclone.copetti.com.ballzclone.objects.*

/**
 * Created by LuisCopetti on 02/07/2017.
 */
class GameManager : GameLoop {

    var rgbValue = 0.0f
    var gameObjectManager = GameObjectManager()
    var ballCannon = BallCannon()
    var ballGrid = SquareGrid(Point(7, 10), 5.0f)

    init {
        for (i in 1..10) {
            gameObjectManager.add(Ball(10.0f).apply {
                getPosition().set(20.0f * i, 40.0f * i)
                getVelocity().set(1f, GameDefine.ball_velocity)
            })
        }

        ballCannon.getPosition().set(240.0f, 300.0f)
        gameObjectManager.add(Ball(10.0f).apply { getPosition().set(480.0f, 640.0f); moving = false })
        gameObjectManager.add(HorizontalWall(BZVector2f(0.0f, 640.0f), 480.0f))
        gameObjectManager.add(HorizontalWall(BZVector2f(0.0f, 0.0f), 480.0f))
        gameObjectManager.add(VerticalWall(BZVector2f(0.0f, 0.0f), 640.0f))
        gameObjectManager.add(VerticalWall(BZVector2f(480.0f, 0.0f), 640.0f))
        gameObjectManager.add(ballCannon)
        gameObjectManager.add(ballGrid)
        gameObjectManager.add(BallPowerUp().apply { getPosition().set(240.0f, 550.0f) })
        ballCannon.fire(30.0f, 5)
    }

    override fun update(delta: Float) {
        rgbValue += delta * 100;
        gameObjectManager.update(delta)
        ballGrid.advance()
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

        gameObjectManager.draw(canvas)
    }
}