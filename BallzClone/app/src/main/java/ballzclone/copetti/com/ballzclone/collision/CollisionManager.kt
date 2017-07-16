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

    fun checkBallOnAABBCollision(b: Ball, s: GameObject) : Boolean {

        val collision = CollisionSolver().getCollision(s.getBZRect(), b.getBZRect())

        if (!collision.collided)
            return false

        val restitution = CollisionRestitution();
        restitution.handleRestituition(b, collision)
        return true;
    }

    fun <T : GameObject> checkCollisionForGroups(lhsGroup: ArrayList<Ball>, rhsGroup: ArrayList<out T>)
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

    private fun checkCollision(lhs: Ball, rhs: GameObject) : Boolean {

        return checkBallOnAABBCollision(lhs, rhs)
    }
}