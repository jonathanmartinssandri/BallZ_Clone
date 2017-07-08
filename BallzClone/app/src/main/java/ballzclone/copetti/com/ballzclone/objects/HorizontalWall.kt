package ballzclone.copetti.com.ballzclone.objects

import ballzclone.copetti.com.ballzclone.BZRect
import ballzclone.copetti.com.ballzclone.BZVector2f

/**
 * Created by Pichau on 08/07/2017.
 */
class HorizontalWall(pos: BZVector2f, width: Float)
    : Wall(BZRect(pos.x, pos.y, pos.x + width, pos.y + 1.0f), Orientation.HORIZONTAL) {


    override fun collidedWith(gameObject: GameObject)  { gameObject.getVelocity().invertY() }
}