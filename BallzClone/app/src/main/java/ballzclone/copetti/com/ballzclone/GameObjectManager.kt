package ballzclone.copetti.com.ballzclone

import android.graphics.Canvas
import ballzclone.copetti.com.ballzclone.collision.CollisionManager
import ballzclone.copetti.com.ballzclone.objects.Ball
import ballzclone.copetti.com.ballzclone.objects.GameObject

/**
 * Created by Pichau on 08/07/2017.
 */
class GameObjectManager {

    val balls = arrayListOf<Ball>()
    var objects = arrayListOf<GameObject>()
    var collisionManager = CollisionManager()

    var toBeAdded = arrayListOf<GameObject>()

    fun add(gameObject: GameObject) {
        gameObject.parent = this
        toBeAdded.add(gameObject)
    }

    fun safeAdd() {

        toBeAdded.forEach { if (it is Ball) balls.add(it) else objects.add(it) }
        toBeAdded.clear()
    }

    fun  remove(gameObject: GameObject) = objects.remove(gameObject)

    fun  update(delta: Float) {

        safeAdd()

        objects.forEach { it.update(delta) }
        balls.forEach { it.update(delta) }

        collisionManager.checkCollisionForGroups(balls, objects)

        val iter : MutableIterator<GameObject> = objects.iterator()
        for (i in iter)
            if (i.isDead()) iter.remove()

        removeTheDead(balls.iterator())
        removeTheDead(objects.iterator())
    }

    fun  draw(canvas: Canvas) {
        objects.forEach { it.draw(canvas) }
        balls.forEach { it.draw(canvas) }
    }

    fun <T : GameObject> removeTheDead(iterator: MutableIterator<out T>) {
        for (value in iterator) if (value.isDead()) iterator.remove()
    }

    fun handleInput(p: BZVector2f) {
        objects.forEach { it.handleInput(p) }
    }

}

