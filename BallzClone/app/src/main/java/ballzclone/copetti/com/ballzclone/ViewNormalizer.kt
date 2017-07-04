package ballzclone.copetti.com.ballzclone

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

/**
 * Created by Pichau on 04/07/2017.
 */
class ViewNormalizer {

    private val GAME_SCREEN_WIDTH = 240 * 2
    private val GAME_SCREEN_HEIGHT = 320 * 2

    private var frameBuffer: Bitmap? = null

    fun createNewCanvas() : Canvas {
        frameBuffer = Bitmap.createBitmap(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, Bitmap.Config.RGB_565)
        return Canvas(frameBuffer)
    }

    fun flipTo(canvas: Canvas) {
        val originalRectSize = Rect(0, 0, canvas.width, canvas.height)
        canvas.drawBitmap(frameBuffer, null, originalRectSize, null)
    }

}