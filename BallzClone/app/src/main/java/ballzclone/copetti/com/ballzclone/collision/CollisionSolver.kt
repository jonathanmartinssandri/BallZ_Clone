package ballzclone.copetti.com.ballzclone.collision

import ballzclone.copetti.com.ballzclone.BZRect
import ballzclone.copetti.com.ballzclone.BZVector2f

/**
 * Created by Pichau on 13/07/2017.
 */
class CollisionSolver {


    fun getCollision(lhs: BZRect, rhs: BZRect) : CollisionManifold {

        val xCollision = getCollisionOnX(lhs, rhs)
        if (!xCollision.collided) return noCollision()

        val yCollision = getCollisionOnY(lhs, rhs)
        if (!yCollision.collided) return noCollision()

        return minOf(xCollision,
                yCollision,
                Comparator { a, b -> (a.penetration - b.penetration).toInt() })
    }

    fun getCollisionOnY(lhs: BZRect, rhs: BZRect) = getCollisionOnAxis(lhs, rhs, BZVector2f(0f, 1f))
    fun  getCollisionOnX(lhs: BZRect, rhs: BZRect) = getCollisionOnAxis(lhs, rhs, BZVector2f(1f, 0f))

    fun getCollisionOnAxis(lhs: BZRect, rhs: BZRect, axis: BZVector2f) : CollisionManifold{
        val lhsCenter = lhs.centerPoint()
        val rhsCenter = rhs.centerPoint()
        val distanceVector = (lhs.centerPoint() - rhs.centerPoint())

        val hwFactorOnX = if (lhsCenter.x < rhsCenter.x) 1.0f else - 1.0f
        val hwFactorOnY = if (lhsCenter.y < rhsCenter.y) 1.0f else - 1.0f

        val hwVectorA = BZVector2f(lhs.halfWidth() * hwFactorOnX, lhs.halfHeight() * hwFactorOnY)
        val hwVectorB = BZVector2f(rhs.halfWidth() * hwFactorOnX, rhs.halfHeight() * hwFactorOnY) * -1.0f


        val centerProjection = Math.abs(distanceVector.dot(axis))
        val lhsProjection = Math.abs(hwVectorA.dot(axis))
        val rhsProjection = Math.abs(hwVectorB.dot(axis))

        val overlap = centerProjection - lhsProjection - rhsProjection
        return if(overlap > 0f) noCollision() else collision(axis, overlap)
    }


    fun collision(direction: BZVector2f, penetration: Float) = CollisionManifold(true, direction, Math.abs(penetration))
    fun noCollision() = CollisionManifold(false, BZVector2f(0f, 0f), 0f)
}