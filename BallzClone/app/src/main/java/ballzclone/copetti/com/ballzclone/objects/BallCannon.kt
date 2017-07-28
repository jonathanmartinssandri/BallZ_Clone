package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import ballzclone.copetti.com.ballzclone.BZVector2f
import ballzclone.copetti.com.ballzclone.GameDefine

/**
 * Created by Pichau on 08/07/2017.
 */
class BallCannon : GameObject(1.0f) {

    enum class BallCannonState {
        DORMANT, SHOOTING, RELOADING
    }

    private var remainingBalls: Int = 0
    private var shootingTarget = BZVector2f(0f, 0f)

    private var countingInterval = .0f

    var state = BallCannonState.DORMANT
        private set

    private var ballsShot = mutableListOf<Ball>()

    init {
        collidable = false
    }

    fun fireAt(target: BZVector2f, numberOfBalls: Int) {

        if (state != BallCannonState.DORMANT) return

        remainingBalls = numberOfBalls
        shootingTarget = target
        state = BallCannonState.SHOOTING
        countingInterval = GameDefine.cannon_shooting_interval
    }

    override fun update(delta: Float) {

        if (state == BallCannonState.DORMANT) return

        if (state == BallCannonState.RELOADING)
        {
            checkIfDoneReloading()
            return
        }

        countingInterval += delta

        if (countingInterval < GameDefine.cannon_shooting_interval)
            return

        val velVector : BZVector2f = computeVelocityVector()

        val newBall = Ball(GameDefine.ball_radius).apply { getVelocity().set(velVector.x, velVector.y); getPosition().set(this@BallCannon.pos.x, this@BallCannon.pos.y) }
        ballsShot.add(newBall)
        parent?.add(newBall)

        countingInterval = 0.0f
        if (--remainingBalls <= 0) state = BallCannonState.RELOADING
    }

    private fun checkIfDoneReloading() {
        if (ballsShot.any{ !it.isDead() })
            return

        ballsShot.clear()
        state = BallCannonState.DORMANT
    }

    private fun  computeVelocityVector(): BZVector2f {
        val targetVector = shootingTarget - pos
        return targetVector.normalize() * GameDefine.ball_velocity
    }

    override fun draw(canvas: Canvas) {
    }

    override fun collidedWith(gameObject: GameObject) {
    }
}