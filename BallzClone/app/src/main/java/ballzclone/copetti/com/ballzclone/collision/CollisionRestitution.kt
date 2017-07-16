package ballzclone.copetti.com.ballzclone.collision

import ballzclone.copetti.com.ballzclone.objects.GameObject

/**
 * Created by Pichau on 16/07/2017.
 */
class CollisionRestitution {


    fun handleRestituition(lhs: GameObject, manifold: CollisionManifold) {

        if (manifold.penetration > 0f)
            repositionOutOfCollision(lhs, manifold)

        reflectVelocityAlongAxis(lhs, manifold)
    }

    private fun  repositionOutOfCollision(lhs: GameObject, manifold: CollisionManifold) {
        var position = lhs.getPosition()
        position += manifold.collisionNormal * manifold.penetration
    }

    private fun  reflectVelocityAlongAxis(lhs: GameObject, manifold: CollisionManifold) {
        val v = lhs.getVelocity()
        val n = manifold.collisionNormal
        val newVelocity = v - (n * v.dot(manifold.collisionNormal)) * 2f /* Reflection formula */
        lhs.getVelocity().set(newVelocity.x, newVelocity.y)
    }

}
