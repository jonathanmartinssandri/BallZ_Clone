package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Point
import ballzclone.copetti.com.ballzclone.BZVector2f

/**
 * Created by Pichau on 27/07/2017.
 */
class GameRound : GameObject(1.0f) {

    enum class GameRoundState {
        DORMANT, RUNNING, DEAD
    }

    var ballCannon = BallCannon()
    var ballGrid = SquareGrid(Point(7, 10), 5.0f)
    var state = GameRoundState.DORMANT


    init {
        ballCannon.getPosition().set(240.0f, 500.0f)
   }

    fun initialize() {
        parent?.add(ballCannon)
        parent?.add(ballGrid)

        ballGrid.advance()
    }

    override fun update(delta: Float) {

        if (state != GameRoundState.RUNNING)
            return

        if (ballCannon.state != BallCannon.BallCannonState.DORMANT)
            return

        /* Lost the game, blocks reached the minimum height */
        if (!ballGrid.advance()) {
            state = GameRoundState.DEAD
            return
        }

        state = GameRoundState.DORMANT
    }

    override fun draw(canvas: Canvas) {
    }

    override fun handleInput(p: BZVector2f) {

        if (state != GameRoundState.DORMANT)
            return

        state = GameRoundState.RUNNING
        ballCannon.fireAt(p, 1)
    }
}