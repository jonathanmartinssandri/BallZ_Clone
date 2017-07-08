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


    fun add(gameObject: GameObject) {
        gameObject.parent = this

        if (gameObject is Ball)
            balls.add(gameObject)
        else
            objects.add(gameObject)
    }

    fun  remove(gameObject: GameObject) = objects.remove(gameObject)

    fun  update(delta: Float) {
        objects.forEach { it.update(delta) }
        balls.forEach { it.update(delta) }

        collisionManager.checkCollisionForGroups(balls, objects)
    }

    fun  draw(canvas: Canvas) {
        objects.forEach { it.draw(canvas) }
        balls.forEach { it.draw(canvas) }
    }
}