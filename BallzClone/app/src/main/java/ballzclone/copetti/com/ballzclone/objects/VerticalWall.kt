package ballzclone.copetti.com.ballzclone.objects

import ballzclone.copetti.com.ballzclone.BZRect
import ballzclone.copetti.com.ballzclone.BZVector2f

/**
 * Created by Pichau on 08/07/2017.
 */
class VerticalWall(pos: BZVector2f, height: Float)
    : Wall(BZRect(pos.x, pos.y, pos.x + 1.0f, pos.y + height), Orientation.VERTICAL){


    override fun collidedWith(gameObject: GameObject) {
        gameObject.getVelocity().invertX()
    }
}