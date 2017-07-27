package ballzclone.copetti.com.ballzclone

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

/**
 * Created by Pichau on 04/07/2017.
 */
class ViewNormalizer {

    private var frameBuffer: Bitmap? = null

    fun createNewCanvas() : Canvas {
        frameBuffer = Bitmap.createBitmap(GameDefine.GAME_SCREEN_WIDTH, GameDefine.GAME_SCREEN_HEIGHT, Bitmap.Config.RGB_565)
        return Canvas(frameBuffer)
    }

    fun flipTo(canvas: Canvas) {
        val originalRectSize = Rect(0, 0, canvas.width, canvas.height)
        canvas.drawBitmap(frameBuffer, null, originalRectSize, null)
    }

}