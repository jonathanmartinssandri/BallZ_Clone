package ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game

import android.graphics.Canvas

/**
 * Created by LuisCopetti on 02/07/2017.
 */
class GameLoopManager (gameLoop: GameLoop, timeInSecondsSupplier: () -> Float, frameRate: Float){

    private val fixedTimeStep = 1.0f / frameRate
    private var deltaTime: Float = 0.0f

    private val timeInSecondsSupplier = timeInSecondsSupplier
    private var lastTimeStart: Float = timeInSecondsSupplier()

    private val gameLoop = gameLoop

    public fun update(canvas: Canvas) {

        val timeStart = timeInSecondsSupplier()

        deltaTime += timeStart - lastTimeStart;
        updateSimulation()

        lastTimeStart = timeStart
        gameLoop.draw(canvas)
    }

    public fun updateSimulation() {

        if (deltaTime < fixedTimeStep)
            return

        gameLoop.update(deltaTime)
        deltaTime = 0.0f
    }
}