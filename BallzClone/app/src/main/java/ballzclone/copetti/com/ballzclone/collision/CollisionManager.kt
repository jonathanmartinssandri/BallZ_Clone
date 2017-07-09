package ballzclone.copetti.com.ballzclone.collision

import android.util.Log
import ballzclone.copetti.com.ballzclone.AABBCollision
import ballzclone.copetti.com.ballzclone.objects.GameObject
import ballzclone.copetti.com.ballzclone.objects.Wall

/**
 * Created by Pichau on 04/07/2017.
 */
class CollisionManager {


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