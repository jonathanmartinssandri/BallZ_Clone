package ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game

import android.graphics.Canvas

/**
 * Created by LuisCopetti on 02/07/2017.
 */
interface GameLoop : UpdatableFromDeltaTime {

    fun draw(canvas: Canvas)
}