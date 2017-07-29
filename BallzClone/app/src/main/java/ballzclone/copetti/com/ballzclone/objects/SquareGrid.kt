package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Point
import ballzclone.copetti.com.ballzclone.BZVector2f
import ballzclone.copetti.com.ballzclone.GameDefine
import java.util.*

/**
 * Created by Pichau on 08/07/2017.
 */
class SquareGrid(width: Int, height: Int, gridSize: Point, margin: Float) : GameObject(1.0f) {

    enum class SquareGridState
    {
        ADVANCING, DORMANT
    }

    val margin: Float = margin

    val width = (width - gridSize.x * margin) / gridSize.x
    val height = (height - gridSize.y * margin) / gridSize.y
    val gridSize = gridSize
    val grid = Array<Array<GameObject?>>(gridSize.y, { Array<GameObject?>(gridSize.x, { null }) })

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

        for (i in grid.size - 2 downTo 0)
            for (j in 0 until grid[0].size) {
                val currentSquare = grid[i][j]
                if (currentSquare != null) {
                    grid[i + 1][j] = currentSquare
                    grid[i][j] = null
                }
            }
    }

    private fun updateAllBlocksPosition(delta: Float) : Boolean {

        var finished: Boolean? = null

        for (i in 0 until grid.size)
            for (j in 0 until grid[0].size) {
                val square = grid[i][j]
                if (square == null)
                    continue

                if (square.getPosition().y >= getGridPosition(i, j).y) {
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

        for (x in 0 until gridSize.x) {
            if (grid[gridSize.y - 1][x] != null)
                return true
        }
        return false
    }

    private fun putRandomBlocksOnTop() {
        /*
        var numberOfBlocks = (Math.random() * grid[0].size).toInt()
        val list = IntRange(0, grid[0].size - 1).toList().toMutableList()
        Collections.shuffle(list)
        for (i in list.take(numberOfBlocks)) {
            createSquareAt(i, 0)
        }
        */
        for (j in 0 until grid[0].size) {
            createSquareAt(0, j)
        }
    }

    fun createSquareAt(x: Int, y: Int) {
        val square = Square(50.0f, 10)
        grid[x][y] = square
        val squareInGridPosition = getGridPosition(x, y)
        square.getPosition().set(squareInGridPosition.x, squareInGridPosition.y)
        parent?.add(square)
    }

    fun getGridPosition(i: Int, j: Int): BZVector2f {
        val marginOffset = BZVector2f(margin * (j + 0.5f), margin * (i + 0.5f))
        val gridDistance = BZVector2f((j * width).toFloat(), (i * height).toFloat())
        return marginOffset + gridDistance
    }

    override fun draw(canvas: Canvas) {
    }

    override fun collidedWith(gameObject: GameObject) = Unit
}