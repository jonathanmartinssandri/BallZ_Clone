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

    var totalBalls: Int = 1
    private var remainingBalls: Int = totalBalls
    private var shootingTarget = BZVector2f(0f, 0f)

    private var countingInterval = .0f

    var state = BallCannonState.DORMANT
        private set

    var nextCannonPosition : Float? = null

    private var ballsShot = mutableListOf<Ball>()

    /*
        There should be at least one main ball to actually
        represent where the balls will come from
     */
    private var mainBall : Ball

    init {
        collidable = false
        mainBall = Ball(this, 1.0f)
    }


    fun initialize() {
        mainBall = Ball(this, GameDefine.ball_radius)
        mainBall.getPosition().set(pos.x, pos.y)
        parent?.add(mainBall)
    }

    fun addTotalBalls(n: Int) {
        totalBalls += n
    }

    fun fireAt(target: BZVector2f) {

        if (state != BallCannonState.DORMANT) return

        val newCannonPos = nextCannonPosition
        if (newCannonPos != null) {
            pos = BZVector2f(newCannonPos, pos.y)
            nextCannonPosition = null
        }

        remainingBalls = totalBalls
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

        val newBall = Ball(this, GameDefine.ball_radius).apply { getVelocity().set(velVector.x, velVector.y); getPosition().set(this@BallCannon.pos.x, this@BallCannon.pos.y) }
        ballsShot.add(newBall)
        parent?.add(newBall)

        countingInterval = 0.0f
        if (--remainingBalls <= 0) {
            state = BallCannonState.RELOADING
            if (nextCannonPosition == null)
                mainBall.visible = false
        }
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

    fun  notifyDeadEvent(ballPosition: BZVector2f) {

        if (nextCannonPosition != null)
            return

        nextCannonPosition = ballPosition.x
        mainBall.getPosition().set(ballPosition.x, getPosition().y)
        mainBall.visible = true
    }
}