package ballzclone.copetti.com.ballzclone

import android.graphics.Rect

/**
 * Created by Pichau on 08/07/2017.
 */
class BZRect (left: Float, top: Float, right: Float, bottom: Float) {

    val left: Float = left
    val top: Float = top
    val right: Float = right
    val bottom: Float = bottom

    fun asRect() : Rect = Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
    fun centerPoint()  = BZVector2f(left + (right - left) / 2.0f, top + (bottom - top) / 2.0f)
}