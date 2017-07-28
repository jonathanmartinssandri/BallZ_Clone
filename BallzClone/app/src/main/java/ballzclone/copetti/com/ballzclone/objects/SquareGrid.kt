package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Point
import ballzclone.copetti.com.ballzclone.GameDefine
import java.util.*

/**
 * Created by Pichau on 08/07/2017.
 */
class SquareGrid(gridSize: Point, margin: Float) : GameObject(1.0f) {

    enum class SquareGridState
    {
        ADVANCING, DORMANT
    }

    val margin: Float = margin

    val widthSize = gridSize.x
    val heightSize = gridSize.y
    val grid = Array<Array<Square?>>(gridSize.y, { Array<Square?>(gridSize.x, { null }) })

    var state = SquareGridState.DORMANT
        private set

    override fun update(delta: Float) {

        cleanUpTheDead()

        if (state == SquareGridState.DORMANT)
            return

        updateBlocks(delta)
    }

    private fun updateBlocks(delta: Float) {

        if (!updateAllBlocksPosition(delta))
            return

        state = SquareGridState.DORMANT
        putRandomBlocksOnTop()
    }

    private fun cleanUpTheDead()
    {
        for (x in 0 until grid.size)
            for (y in 0 until grid[0].size) {
                val square = grid[x][y]
                if (square != null && square.isDead())
                    grid[x][y] = null
            }
    }

    fun advance() : Boolean {

        if (anyBlockReachedTheMinimumHeight())
            return false

        if (state == SquareGridState.ADVANCING)
            return false

        advanceAllBlocks()
        state = SquareGridState.ADVANCING
        return true
    }

    private fun advanceAllBlocks() {

        for (y in grid[0].size - 2 downTo 0)
            for (x in grid.size - 2 downTo 0) {
                val currentSquare = grid[x][y]
                if (currentSquare != null) {
                    grid[x][y + 1] = currentSquare
                    grid[x][y] = null
                }
            }
    }

    private fun updateAllBlocksPosition(delta: Float) : Boolean {

        var finished: Boolean? = null

        for (x in 0 until grid.size)
            for (y in 0 until grid[0].size) {
                val square = grid[x][y]
                if (square == null)
                    continue

                if (square.getPosition().y >= y * (margin + square.getRectSize().y)) {
                    finished = true
                    continue
                }

                finished = false
                val newPosition = square.getPosition() + (GameDefine.grid_velocity * delta)
                square.getPosition().set(newPosition.x, newPosition.y)
        }

        return if (finished == null) return true else finished
    }

    private fun anyBlockReachedTheMinimumHeight() : Boolean {

        for (line in grid) {
            if (line[line.size - 1] != null)
                return true
        }
        return false
    }

    private fun putRandomBlocksOnTop() {

        var numberOfBlocks = (Math.random() * grid[0].size).toInt()
        val list = IntRange(0, grid[0].size).toList().toMutableList()
        Collections.shuffle(list)

        for (i in list.take(numberOfBlocks)) {
            createSquareAt(i, 0)
        }
    }

    fun createSquareAt(x: Int, y: Int) {
        val square = Square(50.0f, 10)
        grid[x][y] = square
        square.getPosition().set(x * (margin + square.getRectSize().x), y * (margin + square.getRectSize().y))
        parent?.add(square)
    }

    override fun draw(canvas: Canvas) {
    }
    override fun collidedWith(gameObject: GameObject) = Unit
}