package ballzclone.copetti.com.ballzclone.collision

import android.util.Log
import ballzclone.copetti.com.ballzclone.AABBCollision
import ballzclone.copetti.com.ballzclone.BZVector2f
import ballzclone.copetti.com.ballzclone.objects.Ball
import ballzclone.copetti.com.ballzclone.objects.GameObject
import ballzclone.copetti.com.ballzclone.objects.Square
import ballzclone.copetti.com.ballzclone.objects.Wall

/**
 * Created by Pichau on 04/07/2017.
 */
class CollisionManager {

    enum class Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT

    }

    fun checkBallOnAABBCollision(lhsGroup: ArrayList<Ball>, rhsGroup: ArrayList<Square>)
    {
        for (i in 0 until lhsGroup.size)
            for (j in 0 until rhsGroup.size)
            {
                if (checkBallOnAABBCollision(lhsGroup[i], rhsGroup[j])) {
                    lhsGroup[i].collidedWith(rhsGroup[j])
                    rhsGroup[j].collidedWith(lhsGroup[i])
                }
            }
    }

    fun checkBallOnAABBCollision(b: Ball, s: Square) : Boolean {

        val closestPoint = getClosestPoint(b, s)
        val diffVector = closestPoint - b.getPosition()
        return true;
    }

    fun getClosestPoint(b: Ball, s: Square) : BZVector2f {
        val closestX = fitIntoRange(b.getPosition().x, s.getBZRect().left, s.getBZRect().right)
        val closestY = fitIntoRange(b.getPosition().y, s.getBZRect().top, s.getBZRect().bottom)
        return BZVector2f(closestX, closestY)
    }

    fun fitIntoRange(x: Float, minX: Float, maxX: Float) : Float {
        return minOf(maxOf(x, minX), maxX)
    }


    fun <T : GameObject> checkCollisionForGroups(lhsGroup: ArrayList<out T>, rhsGroup: ArrayList<out T>)
    {
        for (i in 0 until lhsGroup.size)
            for (j in 0 until rhsGroup.size)
            {
                if (checkCollision(lhsGroup[i], rhsGroup[j])) {
                    lhsGroup[i].collidedWith(rhsGroup[j])
                    rhsGroup[j].collidedWith(lhsGroup[i])
                }
            }
    }

    fun update(gameObjects: ArrayList<GameObject>) {

        for (i in 0 until gameObjects.size)
            for (j in i + 1 until gameObjects.size)
            {
                if (checkCollision(gameObjects[i], gameObjects[j])) {
                    gameObjects[i].collidedWith(gameObjects[j])
                    gameObjects[j].collidedWith(gameObjects[i])
                }
            }
    }

    private fun checkCollision(lhs: GameObject, rhs: GameObject) =
            AABBCollision.checkCollision(lhs.getBZRect(), rhs.getBZRect())

    private fun distance(lhs: GameObject, rhs: GameObject) : Float {
        val squaredDistance = Math.pow((lhs.getPosition().x - rhs.getPosition().x).toDouble(), 2.0)
        + Math.pow((lhs.getPosition().y - rhs.getPosition().y).toDouble(), 2.0)
        return squaredDistance.toFloat()
    }

}