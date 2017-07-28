package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Point

/**
 * Created by Pichau on 27/07/2017.
 */
class GameRound : GameObject(1.0f) {

    var ballCannon = BallCannon()
    var ballGrid = SquareGrid(Point(7, 10), 5.0f)

    init {
        ballCannon.getPosition().set(240.0f, 500.0f)
   }

    fun initialize() {
        parent?.add(ballCannon)
        parent?.add(ballGrid)

        ballGrid.advance()
    }

    override fun update(delta: Float) {

    }

    override fun draw(canvas: Canvas) {
    }
}