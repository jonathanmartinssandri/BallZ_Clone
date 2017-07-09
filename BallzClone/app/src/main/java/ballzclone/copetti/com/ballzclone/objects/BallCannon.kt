package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import ballzclone.copetti.com.ballzclone.BZVector2f

/**
 * Created by Pichau on 08/07/2017.
 */
class BallCannon : GameObject(1.0f) {

    private val SHOOTING_INTERVAL = 0.5f

    private var shooting = false
    private var remainingBalls: Int = 0
    private var shootingAngle = 0.0f

    private var countingInterval = .0f

    fun fire(degreeAngle: Float, numberOfBalls: Int) {
        if (shooting) return

        remainingBalls = numberOfBalls
        shootingAngle = degreeAngle
        shooting = true
        countingInterval = SHOOTING_INTERVAL
    }

    override fun update(delta: Float) {
        if (!shooting) return

        if (remainingBalls <= 0) return

        countingInterval += delta

        if (countingInterval < SHOOTING_INTERVAL)
            return

        val velVector : BZVector2f = computeVelocityVector()

        val newBall = Ball(15.0f).apply { getVelocity().set(velVector.x, velVector.y); getPosition().set(this@BallCannon.pos.x, this@BallCannon.pos.y) }
        parent?.add(newBall)

        countingInterval = 0.0f
        if (--remainingBalls <= 0) shooting = false
    }

    private fun  computeVelocityVector(): BZVector2f {
        val normalizedAngle = if (shootingAngle > 0.0)
            90.0 - shootingAngle else shootingAngle * -1 + 90.0

        val BALL_VELOCITY = 6.0f

        val radiansAngle = Math.toRadians(normalizedAngle)

        return BZVector2f(Math.sin(radiansAngle).toFloat(), - Math.cos(radiansAngle).toFloat()) * BALL_VELOCITY
    }

    override fun draw(canvas: Canvas) {
    }

    override fun collidedWith(gameObject: GameObject) {
    }
}