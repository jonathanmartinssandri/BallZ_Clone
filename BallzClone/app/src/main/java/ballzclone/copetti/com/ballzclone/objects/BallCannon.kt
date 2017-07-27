package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import ballzclone.copetti.com.ballzclone.BZVector2f
import ballzclone.copetti.com.ballzclone.GameDefine

/**
 * Created by Pichau on 08/07/2017.
 */
class BallCannon : GameObject(1.0f) {

    private var shooting = false
    private var remainingBalls: Int = 0
    private var shootingTarget = BZVector2f(0f, 0f)

    private var countingInterval = .0f

    init {
        collidable = false
    }

//    fun fire(degreeAngle: Float, numberOfBalls: Int) {
//        if (shooting) return
//
//        remainingBalls = numberOfBalls
//        shootingAngle = degreeAngle
//        shooting = true
//        countingInterval = GameDefine.cannon_shooting_interval
//    }

    fun fireAt(target: BZVector2f, numberOfBalls: Int) {
        if (shooting) return

        remainingBalls = numberOfBalls
        shootingTarget = target
        shooting = true
        countingInterval = GameDefine.cannon_shooting_interval
    }

    override fun update(delta: Float) {
        if (!shooting) return

        if (remainingBalls <= 0) return

        countingInterval += delta

        if (countingInterval < GameDefine.cannon_shooting_interval)
            return

        val velVector : BZVector2f = computeVelocityVector()

        val newBall = Ball(GameDefine.ball_radius).apply { getVelocity().set(velVector.x, velVector.y); getPosition().set(this@BallCannon.pos.x, this@BallCannon.pos.y) }
        parent?.add(newBall)

        countingInterval = 0.0f
        if (--remainingBalls <= 0) shooting = false
    }

    private fun  computeVelocityVector(): BZVector2f {
        val targetVector = shootingTarget - pos
//        val radiansAngle = Math.toRadians(shootingAngle.toDouble())
        return targetVector.normalize() * GameDefine.ball_velocity
//        return BZVector2f(-Math.cos(radiansAngle).toFloat(), Math.sin(radiansAngle).toFloat()) * GameDefine.ball_velocity
    }

    override fun handleInput(p: BZVector2f) {

//        val target = p - pos


//        fire(80f, 5)
        fireAt(p, 1)
    }

    override fun draw(canvas: Canvas) {
    }

    override fun collidedWith(gameObject: GameObject) {
    }
}