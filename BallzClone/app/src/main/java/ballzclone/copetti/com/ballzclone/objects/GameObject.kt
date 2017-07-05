package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game.DrawableToCanvas
import ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game.UpdatableFromDeltaTime

/**
 * Created by Pichau on 04/07/2017.
 */
abstract class GameObject : DrawableToCanvas, UpdatableFromDeltaTime {

    var x : Float = 0.0f
    var y : Float = 0.0f


    public fun setPosition(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    abstract fun  collidedWith(gameObject: GameObject)
}