package ballzclone.copetti.com.ballzclone.objects

import android.graphics.Canvas
import android.graphics.Point

/**
 * Created by Pichau on 08/07/2017.
 */
class SquareGrid(gridSize: Point, margin: Float) : GameObject(1.0f) {

    val margin: Float = margin
    val grid = Array<Array<Square?>>(gridSize.y, { Array<Square?>(gridSize.x, { null }) })

    override fun update(delta: Float) {

    }

    fun advance() : Boolean {

        putSquareAt(1, 1, Square(50.0f, 1000))
        return true
    }

    fun putSquareAt(i: Int, j: Int, square: Square) {
        grid[i][j] = square
        square.getPosition().set(i * (margin + square.getRectSize().x), j * (margin + square.getRectSize().y))
        parent?.add(square)
    }

    override fun draw(canvas: Canvas) {
    }
    override fun collidedWith(gameObject: GameObject) = Unit
}