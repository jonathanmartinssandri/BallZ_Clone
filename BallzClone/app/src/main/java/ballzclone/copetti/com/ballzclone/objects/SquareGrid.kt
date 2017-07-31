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

    val ballPowerUpOffset = 12.0f

    enum class SquareGridState
    {
        ADVANCING, DORMANT
    }

    val margin: Float = margin

    val width = (width - gridSize.x * margin) / gridSize.x
    val height = (height - gridSize.y * margin) / gridSize.y
    val gridSize = gridSize
    val grid = Array<Array<GameObject?>>(gridSize.y, { Array<GameObject?>(gridSize.x, { null }) })

    var squareValue : Int = 1

    var state = SquareGridState.DORMANT
        private set

    override fun update(delta: Float) {

        cleanUpTheDead()

        if (state == SquareGridState.DORMANT)
            return

        updateBlocks(delta)
    }

    private fun updateBlocks(delta: Float) {

        if (!doneUpdatingBlocksPosition(delta))
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

    private fun doneUpdatingBlocksPosition(delta: Float) : Boolean {

        var finished = true

        for (i in 0 until grid.size)
            for (j in 0 until grid[0].size) {
                val square = grid[i][j]
                if (square == null)
                    continue

                if (square.getPosition().y >= getGridPosition(square, i, j).y) {
                    finished = true
                    continue
                }

                finished = false
                val newPosition = square.getPosition() + (GameDefine.grid_velocity * delta)
                square.getPosition().set(newPosition.x, newPosition.y)
        }

        return finished
    }

    private fun anyBlockReachedTheMinimumHeight() : Boolean {

        for (x in 0 until gridSize.x) {
            if (grid[gridSize.y - 1][x] != null)
                return true
        }
        return false
    }

    private fun putRandomBlocksOnTop() {
        var numberOfBlocks = (Math.random() * grid[0].size).toInt() + grid[0].size / 2
        val list = IntRange(0, grid[0].size - 1).toList().toMutableList()
        Collections.shuffle(list)
        for (i in list.take(numberOfBlocks)) {
            createElementInGridAt(0, i)
        }
//        for (j in 0 until grid[0].size) {
//            createElementInGridAt(0, j)
//        }
    }

    fun createElementInGridAt(i: Int, j: Int) {
        val element = getNewGridElement(i, j)
        grid[i][j] = element
        val gridPosition = getGridPosition(element, i, j)
        element.getPosition().set(gridPosition.x, gridPosition.y)
        parent?.add(element)
    }

    fun getNewGridElement(i: Int, j: Int) : GameObject {
        val randomValue = Random().nextInt(20)

        return when (randomValue < 18) {
             true -> {
                val squareScore = Random().nextInt(squareValue) + squareValue
                Square(50.0f, squareScore)
            }
            else -> BallPowerUp()
        }
    }

    fun getGridPosition(element: GameObject, i: Int, j: Int): BZVector2f {
        val pos = getGridPosition(i, j)
        val offset = if (element is BallPowerUp) ballPowerUpOffset else 0f
        return BZVector2f(pos.x + offset, pos.y + offset)
    }

    fun getGridPosition(i: Int, j: Int): BZVector2f {
        val marginOffset = BZVector2f(margin * (j + 0.5f), margin * (i + 0.5f))
        val gridDistance = BZVector2f((j * width), (i * height))
        return marginOffset + gridDistance
    }


    override fun draw(canvas: Canvas) = Unit
    override fun collidedWith(gameObject: GameObject) = Unit
}