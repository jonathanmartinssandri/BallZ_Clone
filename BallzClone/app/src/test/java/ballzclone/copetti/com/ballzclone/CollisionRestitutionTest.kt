package ballzclone.copetti.com.ballzclone

import ballzclone.copetti.com.ballzclone.collision.CollisionManifold
import ballzclone.copetti.com.ballzclone.collision.CollisionRestitution
import ballzclone.copetti.com.ballzclone.objects.GameObject
import org.junit.Assert
import org.junit.Test

/**
 * Created by Pichau on 16/07/2017.
 */
class CollisionRestitutionTest {

    val gameObject = TestingGameObject(BZRect(BZVector2f(5f, 5f), 5f, 5f))
    val collision = CollisionRestitution()

    @Test
    fun shouldRepositionTheObjectOutOfHorizontalCollision() {
        val manifold = CollisionManifold(true, BZVector2f(0f, 1f), 1f)
        collision.handleRestituition(gameObject, manifold)
        Assert.assertEquals(BZVector2f(0f, 1f), gameObject.getPosition())
    }

    @Test
    fun shouldReflectVelocityOnHorizontalCollision() {
        val manifold = CollisionManifold(true, BZVector2f(0f, 1f), 1f)
        gameObject.getVelocity().set(5f, -5f)
        collision.handleRestituition(gameObject, manifold)
        Assert.assertEquals(BZVector2f(0f, 1f), gameObject.getPosition())
        Assert.assertEquals(BZVector2f(5f, 5f), gameObject.getVelocity())
    }

    @Test
    fun shouldRepositionOnVerticalCollision() {
        val manifold = CollisionManifold(true, BZVector2f(1f, 0f), 2f)
        collision.handleRestituition(gameObject, manifold)
        Assert.assertEquals(BZVector2f(2f, 0f), gameObject.getPosition())
    }

    @Test
    fun shouldReflectVelocityOnVerticalCollision() {
        val manifold = CollisionManifold(true, BZVector2f(1f, 0f), 2f)
        gameObject.getVelocity().set(-5f, 5f)
        collision.handleRestituition(gameObject, manifold)
        Assert.assertEquals(BZVector2f(5f, 5f), gameObject.getVelocity())
    }

    @Test
    fun shouldNotRepositionOnZeroPenetrationCollision() {
        val manifold = CollisionManifold(true, BZVector2f(1f, 0f), 0f)
        gameObject.getVelocity().set(-5f, 5f)
        collision.handleRestituition(gameObject, manifold)
        Assert.assertEquals(BZVector2f(0f, 0f), gameObject.getPosition())
        Assert.assertEquals(BZVector2f(5f, 5f), gameObject.getVelocity())
    }

}