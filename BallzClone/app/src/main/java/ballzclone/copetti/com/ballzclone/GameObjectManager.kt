package ballzclone.copetti.com.ballzclone

import android.graphics.Canvas
import ballzclone.copetti.com.ballzclone.collision.CollisionManager
import ballzclone.copetti.com.ballzclone.objects.GameObject

/**
 * Created by Pichau on 08/07/2017.
 */
class GameObjectManager {

    var objects = arrayListOf<GameObject>()
    var collisionManager = CollisionManager()


    fun add(gameObject: GameObject) {
        gameObject.parent = this
        objects.add(gameObject)
    }

    fun  remove(gameObject: GameObject) = objects.remove(gameObject)

    fun  update(delta: Float) {
        objects.forEach { it.update(delta) }
        collisionManager.update(objects)
    }

    fun  draw(canvas: Canvas) {
        objects.forEach { it.draw(canvas) }
    }
}