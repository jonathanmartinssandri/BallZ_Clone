package ballzclone.copetti.com.ballzclone

import ballzclone.copetti.com.ballzclone.collision.CollisionManifold
import ballzclone.copetti.com.ballzclone.collision.CollisionSolver
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Pichau on 13/07/2017.
 */
class CollisionSolverTest {

    @Test
    fun testCollisionForSquaresWithShadowOnX() {
        val lhs = BZRect(BZVector2f(5f, 5f), 5f, 5f)
        val rhs = BZRect(BZVector2f(7.5f, 20f), 5f, 5f)
        assertTrue(CollisionSolver().areCollidingOnAxisX(lhs, rhs))
        assertFalse(CollisionSolver().areCollidingOnAxisY(lhs, rhs))
    }

    @Test
    fun testCollisionIsCommutative() {
        val lhs = BZRect(BZVector2f(5f, 5f), 5f, 5f)
        val rhs = BZRect(BZVector2f(7.5f, 20f), 5f, 5f)
        assertTrue(CollisionSolver().areCollidingOnAxisX(lhs, rhs))
        assertFalse(CollisionSolver().areCollidingOnAxisY(lhs, rhs))

        assertTrue(CollisionSolver().areCollidingOnAxisX(rhs, lhs))
        assertFalse(CollisionSolver().areCollidingOnAxisY(rhs, lhs))
    }

    @Test
    fun testCollisionShadowingFor_Y_Axis() {
        val lhs = BZRect(BZVector2f(5f, 5f), 5f, 5f)
        val rhs = BZRect(BZVector2f(25f, 7.5f), 5f, 5f)
        assertFalse(CollisionSolver().areCollidingOnAxisX(lhs, rhs))
        assertTrue(CollisionSolver().areCollidingOnAxisY(lhs, rhs))
    }

    @Test
    fun testATouchIsAlsoCollision() {
        val lhs = BZRect(BZVector2f(5f, 5f), 5f, 5f)
        val rhs = BZRect(BZVector2f(15f, 5f), 5f, 5f)
        assertTrue(CollisionSolver().areCollidingOnAxisX(lhs, rhs))
        assertTrue(CollisionSolver().areCollidingOnAxisY(lhs, rhs))
    }

    @Test
    fun testManifoldDirectionOn_X_DominantCollision() {
        val lhs = BZRect(BZVector2f(5f, 5f), 5f, 5f)
        val rhs = BZRect(BZVector2f(13f, 5f), 5f, 5f)
        val coll = CollisionSolver().getCollision()
        assertTrue(coll.)
    }

}