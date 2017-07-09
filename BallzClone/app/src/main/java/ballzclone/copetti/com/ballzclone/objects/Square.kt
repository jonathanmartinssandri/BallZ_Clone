package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect

/**
 * Created by Pichau on 06/07/2017.
 */
class Square(sideSize: Float, collisionValue: Int) : GameObject(sideSize.toFloat()) {

    private var collisionValue = collisionValue

    override fun update(delta: Float) {
    }

    override fun draw(canvas: Canvas) {

        val paint = Paint().apply { color = Color.rgb(0, 255, 0); }

        val rectangular = getBZRect().asRect()
        canvas.drawRect(rectangular, paint)

        drawText(canvas)
    }

    fun drawText(canvas: Canvas)
    {
        val paint = Paint().apply {
            color = Color.rgb(255, 0, 0)
            textAlign = Paint.Align.CENTER
            isFakeBoldText = true
            textSize = 35.0f
        }

        var rectWriting = Rect()
        paint.getTextBounds("a", 0, 1, rectWriting)


        val center = getBZRect().centerPoint()
        canvas.drawText(collisionValue.toString(), center.x, center.y + (rectWriting.bottom - rectWriting.top) / 2, paint)
    }

    override fun collidedWith(gameObject: GameObject) {
        collisionValue--

        if (collisionValue < 1)
            dead = true
    }
}