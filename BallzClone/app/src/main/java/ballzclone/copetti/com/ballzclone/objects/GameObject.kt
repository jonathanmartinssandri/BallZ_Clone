package ballzclone.copetti.com.ballzclone.objects

import ballzclone.copetti.com.ballzclone.BZVector2f
import ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game.DrawableToCanvas
import ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game.UpdatableFromDeltaTime

/**
 * Created by Pichau on 04/07/2017.
 */
abstract class GameObject : DrawableToCanvas, UpdatableFromDeltaTime {

    protected var pos: BZVector2f = BZVector2f(0f, 0f)

    public fun getPosition() : BZVector2f {
        return pos
    }

    abstract fun  collidedWith(gameObject: GameObject)
}