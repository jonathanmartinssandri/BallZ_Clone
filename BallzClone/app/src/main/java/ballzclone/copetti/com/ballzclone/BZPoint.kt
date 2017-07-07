package ballzclone.copetti.com.ballzclone

/**
 * Created by Pichau on 06/07/2017.
 */
class BZVector2f(x: Float, y: Float) {
    var x: Float = x
    var y: Float = y


    public operator fun plus(rhs: BZVector2f) : BZVector2f
    {
        x += rhs.x
        y += rhs.y
        return this
    }

    fun add(x: Float, y: Float) {
        this.x += x
        this.y += y
    }

    fun set(x: Float, y: Float) {
        this.x = x
        this.y = y
    }
}