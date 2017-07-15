package ballzclone.copetti.com.ballzclone.collision

import ballzclone.copetti.com.ballzclone.BZRect
import ballzclone.copetti.com.ballzclone.BZVector2f

/**
 * Created by Pichau on 13/07/2017.
 */
class CollisionSolver {


    fun areColliding(lhs: BZRect, rhs: BZRect) : CollisionManifold {
        return CollisionManifold(false, BZVector2f(0f, 0f))
    }

    fun areCollidingOnAxisY(lhs: BZRect, rhs: BZRect) : Boolean {
        val distance = Math.abs((lhs.centerPoint() - rhs.centerPoint()).y)
        return distance - lhs.halfHeight() - rhs.halfHeight() <= 0
    }

    fun areCollidingOnAxisX(lhs: BZRect, rhs: BZRect) : Boolean {
        val distance = Math.abs((lhs.centerPoint() - rhs.centerPoint()).x)
        return distance - lhs.halfWidth() - rhs.halfWidth() <= 0
    }

    fun getCollisionOnY(lhs: BZRect, rhs: BZRect) : CollisionManifold {
        return CollisionManifold(areCollidingOnAxisY(lhs, rhs), )
    }

    fun  getCollision(lhs: BZRect, rhs: BZRect): CollisionManifold {

        val xCollision = areCollidingOnAxisX(lhs, rhs)

        if (!xCollision.)
    }


}