package ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import ballzclone.copetti.com.ballzclone.BZRect
import ballzclone.copetti.com.ballzclone.BZVector2f
import ballzclone.copetti.com.ballzclone.GameDefine
import ballzclone.copetti.com.ballzclone.GameObjectManager
import ballzclone.copetti.com.ballzclone.objects.*

/**
 * Created by LuisCopetti on 02/07/2017.
 */
class GameManager : GameLoop {

    var gameObjectManager = GameObjectManager()
    var gameRound : GameRound

    init {
        val margin = 1f
        val offRectPadding = 100.0f
        val horizontalTop = Wall(BZRect(0f, -offRectPadding, 480f, 0f + margin))
        val horizontalBottom = Wall(BZRect(0f, 520f, 480f, 640f + offRectPadding), deadly = true)
        val verticalLeft = Wall(BZRect(0f - offRectPadding, 0f, 0f + margin, 640f))
        val verticalRight = Wall(BZRect(480f - margin, 0f, 480f + offRectPadding, 640f))

        gameRound = GameRound()
        gameObjectManager.add(gameRound)
        gameObjectManager.add(horizontalTop)
        gameObjectManager.add(horizontalBottom)
        gameObjectManager.add(verticalLeft)
        gameObjectManager.add(verticalRight)

        gameRound.initialize()
   }

    override fun update(delta: Float) {
        gameObjectManager.update(delta)
    }

    fun handleInput(p: BZVector2f) {
        gameObjectManager.handleInput(p)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawColor(Color.rgb(0xEE, 0xEE, 0xEE))
        gameObjectManager.draw(canvas)
    }
}