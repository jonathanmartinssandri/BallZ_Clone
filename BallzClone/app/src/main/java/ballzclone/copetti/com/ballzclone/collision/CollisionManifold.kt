package ballzclone.copetti.com.ballzclone.collision

import ballzclone.copetti.com.ballzclone.BZVector2f

/**
 * Created by Pichau on 14/07/2017.
 */
class CollisionManifold(collided: Boolean, collisionNormal: BZVector2f, penetration: Float) {

    val collided: Boolean = collided
    val collisionNormal: BZVector2f = collisionNormal
    val penetration: Float = penetration

}