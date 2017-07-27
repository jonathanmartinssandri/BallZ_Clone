package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Rect
import ballzclone.copetti.com.ballzclone.BZRect
import ballzclone.copetti.com.ballzclone.BZVector2f
import ballzclone.copetti.com.ballzclone.GameObjectManager
import ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game.DrawableToCanvas
import ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game.UpdatableFromDeltaTime
/**
 * Created by Pichau on 04/07/2017.
 */
abstract class GameObject(rect: BZRect) : DrawableToCanvas, UpdatableFromDeltaTime {

    var parent: GameObjectManager? = null
    protected var dead: Boolean = false
    var collidable: Boolean = true

    protected var pos: BZVector2f = BZVector2f(rect.left, rect.top)
    protected var vel: BZVector2f = BZVector2f(0f, 0f)
    protected val size: BZVector2f = BZVector2f(rect.right - rect.left, rect.bottom - rect.top)

    constructor(sizeSize: Float) : this(BZRect(0f, 0f, sizeSize, sizeSize))

    fun getBZRect() : BZRect =  BZRect(pos.x, pos.y, pos.x + size.x, pos.y + size.y)

    fun getPosition() : BZVector2f = pos
    fun getVelocity() : BZVector2f = vel
    fun getRectSize() : BZVector2f = size

    open fun  collidedWith(gameObject: GameObject) { }
    open fun handleInput(p: BZVector2f) { }

    fun isDead() = dead
    fun die() { dead = true }
}