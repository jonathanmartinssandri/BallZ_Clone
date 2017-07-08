package ballzclone.copetti.com.ballzclone.collision

import android.util.Log
import ballzclone.copetti.com.ballzclone.AABBCollision
import ballzclone.copetti.com.ballzclone.objects.GameObject
import ballzclone.copetti.com.ballzclone.objects.Wall

/**
 * Created by Pichau on 04/07/2017.
 */
class CollisionManager {

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

    private fun checkCollision(lhs: GameObject, rhs: GameObject) : Boolean {

        if (!(lhs is Wall) && !(rhs is Wall))
            return false

        if (lhs is Wall)
            return wallCheckCollision(lhs, rhs)
        else
            return wallCheckCollision(rhs as Wall, lhs)
    }

    private fun distance(lhs: GameObject, rhs: GameObject) : Float {
        val squaredDistance = Math.pow((lhs.getPosition().x - rhs.getPosition().x).toDouble(), 2.0)
        + Math.pow((lhs.getPosition().y - rhs.getPosition().y).toDouble(), 2.0)
        return squaredDistance.toFloat()
    }

    private fun wallCheckCollision(lhs: Wall, rhs: GameObject) =
            AABBCollision.checkCollision(lhs.getBZRect(), rhs.getBZRect())
}