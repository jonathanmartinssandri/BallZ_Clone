package ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import ballzclone.copetti.com.ballzclone.BZRect
import ballzclone.copetti.com.ballzclone.BZVector2f
import ballzclone.copetti.com.ballzclone.GameDefine
import ballzclone.copetti.com.ballzclone.GameObjectManager
import ballzclone.copetti.com.ballzclone.collision.CollisionManager
import ballzclone.copetti.com.ballzclone.objects.*

/**
 * Created by LuisCopetti on 02/07/2017.
 */
class GameManager : GameLoop {

    var gameObjectManager = GameObjectManager()
    var ballCannon = BallCannon()
    var ballGrid = SquareGrid(Point(7, 10), 5.0f)

    init {
        for (i in 1..10) {
            gameObjectManager.add(Ball(GameDefine.ball_radius).apply {
                getPosition().set(20.0f * i, 40.0f * i)
                getVelocity().set(1f, GameDefine.ball_velocity)
            })
        }

        val margin = 1f
        val offRectPadding = 25.0f
        val horizontalTop = Wall(BZRect(0f, -offRectPadding, 480f, 0f + margin))
        val horizontalBottom = Wall(BZRect(0f, 640f - margin, 480f, 640f + offRectPadding), deadly = true)
        val verticalLeft = Wall(BZRect(0f - offRectPadding, 0f, 0f + margin, 640f))
        val verticalRight = Wall(BZRect(480f - margin, 0f, 480f + offRectPadding, 640f))

        ballCannon.getPosition().set(240.0f, 620.0f)
        gameObjectManager.add(horizontalTop)
        gameObjectManager.add(horizontalBottom)
        gameObjectManager.add(verticalLeft)
        gameObjectManager.add(verticalRight)

        gameObjectManager.add(ballCannon)
        gameObjectManager.add(ballGrid)
        gameObjectManager.add(BallPowerUp().apply { getPosition().set(240.0f, 550.0f) })
        ballCannon.fire(30.0f, 12)
    }

    override fun update(delta: Float) {
        gameObjectManager.update(delta)
        ballGrid.advance()
    }

    override fun draw(canvas: Canvas) {
        canvas.drawColor(Color.rgb(0xEE, 0xEE, 0xEE))
        gameObjectManager.draw(canvas)
    }
}