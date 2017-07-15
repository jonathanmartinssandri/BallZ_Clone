package ballzclone.copetti.com.ballzclone

import ballzclone.copetti.com.ballzclone.collision.CollisionManager
import ballzclone.copetti.com.ballzclone.objects.Ball
import ballzclone.copetti.com.ballzclone.objects.Square
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Pichau on 10/07/2017.
 */
class CollisionManagerTest {

    var collisionManager = CollisionManager()

    @Before
    public fun setUp() {
        collisionManager = CollisionManager()
    }

    @Test
    public fun assertBallsAreEqual() {
        Assert.assertEquals(BZVector2f(15.0f, 20.0f), BZVector2f(15.0f, 20.0f))
    }
}