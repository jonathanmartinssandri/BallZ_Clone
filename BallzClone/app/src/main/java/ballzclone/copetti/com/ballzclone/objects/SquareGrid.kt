package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Point
import java.util.*

/**
 * Created by Pichau on 08/07/2017.
 */
class SquareGrid(gridSize: Point, margin: Float) : GameObject(1.0f) {

    val margin: Float = margin

    val widthSize = gridSize.x
    val heightSize = gridSize.y
    val grid = Array<Array<Square?>>(gridSize.y, { Array<Square?>(gridSize.x, { null }) })

    override fun update(delta: Float) {

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

        advanceAllBlocks()
        putRandomBlocksOnTop()
        return true
    }

    private fun advanceAllBlocks() {
        for (y in grid[0].size - 2 downTo 0)
            for (x in grid.size - 2 downTo 0) {
                val currentSquare = grid[x][y]
                if (currentSquare != null) {
                    setSquarePosition(x, y + 1, currentSquare)
                    grid[x][y] = null
                }
            }
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

    fun setSquarePosition(x: Int, y: Int, square: Square) {
        grid[x][y] = square
        square.getPosition().set(x * (margin + square.getRectSize().x), y * (margin + square.getRectSize().y))
    }

    fun createSquareAt(x: Int, y: Int) {
        val square = Square(50.0f, 10)
        setSquarePosition(x, y, square)
        parent?.add(square)
    }

    override fun draw(canvas: Canvas) {
    }
    override fun collidedWith(gameObject: GameObject) = Unit
}