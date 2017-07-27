package ballzclone.copetti.com.ballzclone

import android.content.Context
import android.graphics.*
import android.view.View
import ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game.GameLoopManager
import ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game.GameManager

/**
 * Created by LuisCopetti on 29/06/2017.
 */

class RenderView(context: Context) : View(context) {

    private val FRAME_RATE = 60.0f

    private var gameManager = GameManager()
    private var viewManager = ViewNormalizer()
    private var loopManager = GameLoopManager(gameManager, { System.nanoTime() / 1000000000.0f }, FRAME_RATE)


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var normalizedCanvas = viewManager.createNewCanvas()
        loopManager.update(normalizedCanvas)
        viewManager.flipTo(canvas)
        invalidate()
    }

    fun onInput(p: BZVector2f) {
        gameManager.handleInput(p)
    }
}
