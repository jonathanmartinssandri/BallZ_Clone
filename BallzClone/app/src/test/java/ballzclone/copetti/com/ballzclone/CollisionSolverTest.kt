package ballzclone.copetti.com.ballzclone

import ballzclone.copetti.com.ballzclone.collision.CollisionManifold
import ballzclone.copetti.com.ballzclone.collision.CollisionSolver
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Pichau on 13/07/2017.
 */
class CollisionSolverTest {


    val collidingOnXlhs = BZRect(BZVector2f(5f, 5f), 5f, 3f)
    val collidingOnXrhs= BZRect(BZVector2f(17.5f, 15f), 10f, 4f)

    val collidingOnYlhs = BZRect(BZVector2f(3f, 3f), 1f, 1f)
    val collidingOnYrhs = BZRect(BZVector2f(6f, 5f) ,1f, 1f)

    val collidingLargeXlhs = BZRect(BZVector2f(5f, 5f), 2f, 2f)
    val collidingLargeXrhs = BZRect(BZVector2f(7f, 5f), 1f, 1f)

    val collidingLargeYlhs = BZRect(BZVector2f(10f, 10f), 5f, 5f)
    val collidingLargeYrhs = BZRect(BZVector2f(10f, 15f), 2.5f, 2.5f)
//    @Test
//    fun testCollisionForSquaresWithShadowOnX() {
//        val lhs = BZRect(BZVector2f(5f, 5f), 5f, 5f)
//        val rhs = BZRect(BZVector2f(7.5f, 20f), 5f, 5f)
//        assertTrue(CollisionSolver().areCollidingOnAxisX(lhs, rhs))
//        assertFalse(CollisionSolver().areCollidingOnAxisY(lhs, rhs))
//    }
//
//    @Test
//    fun testCollisionIsCommutative() {
//        val lhs = BZRect(BZVector2f(5f, 5f), 5f, 5f)
//        val rhs = BZRect(BZVector2f(7.5f, 20f), 5f, 5f)
//        assertTrue(CollisionSolver().areCollidingOnAxisX(lhs, rhs))
//        assertFalse(CollisionSolver().areCollidingOnAxisY(lhs, rhs))
//
//        assertTrue(CollisionSolver().areCollidingOnAxisX(rhs, lhs))
//        assertFalse(CollisionSolver().areCollidingOnAxisY(rhs, lhs))
//    }
//
//    @Test
//    fun testCollisionShadowingFor_Y_Axis() {
//        val lhs = BZRect(BZVector2f(5f, 5f), 5f, 5f)
//        val rhs = BZRect(BZVector2f(25f, 7.5f), 5f, 5f)
//        assertFalse(CollisionSolver().areCollidingOnAxisX(lhs, rhs))
//        assertTrue(CollisionSolver().areCollidingOnAxisY(lhs, rhs))
//    }
//
//    @Test
//    fun testATouchIsAlsoCollision() {
//        val lhs = BZRect(BZVector2f(5f, 5f), 5f, 5f)
//        val rhs = BZRect(BZVector2f(15f, 5f), 5f, 5f)
//        assertTrue(CollisionSolver().areCollidingOnAxisX(lhs, rhs))
//        assertTrue(CollisionSolver().areCollidingOnAxisY(lhs, rhs))
//    }

//    @Test
//    fun testManifoldDirectionOn_X_DominantCollision() {
//        val lhs = BZRect(BZVector2f(5f, 5f), 5f, 5f)
//        val rhs = BZRect(BZVector2f(13f, 5f), 5f, 5f)
//        val coll = CollisionSolver().getCollision()
////        assertTrue(coll.)
//    }




    @Test
    fun testCollisionOnX() {
        val coll = CollisionSolver().getCollisionOnX(collidingOnYlhs, collidingOnYrhs)
        assertFalse(coll.collided)
    }

    @Test
    fun testCollisionOnY() {
        val coll = CollisionSolver().getCollisionOnY(collidingOnYlhs, collidingOnYrhs)
        assertTrue(coll.collided)
        assertEquals(coll.collisionNormal, BZVector2f(0f, 1f))
        assertEquals(coll.penetration, 0f)
    }


    @Test
    fun testCollisionOnXAnotherVector() {
        val coll = CollisionSolver().getCollisionOnX(collidingOnXlhs, collidingOnXrhs)
        assertTrue(coll.collided)
        assertEquals(coll.collisionNormal, BZVector2f(1f, 0f))
        assertEquals(coll.penetration, 2.5f)
    }

    @Test
    fun testCollisionOnYAnotherVector() {
        val coll = CollisionSolver().getCollisionOnY(collidingOnXlhs, collidingOnXrhs)
        assertFalse(coll.collided)
    }

    @Test
    fun testCollisionAABB_XlargerPenetration() {
        val coll = CollisionSolver().getCollision(collidingLargeXlhs, collidingLargeXrhs)
        assertTrue(coll.collided)
        assertEquals(coll.collisionNormal, BZVector2f(1f, 0f))
        assertEquals(coll.penetration, 1f)
    }

    @Test
    fun testCollisionAABB_YlargerPenetration() {
        val coll = CollisionSolver().getCollision(collidingLargeYlhs, collidingLargeYrhs)
        assertTrue(coll.collided)
        assertEquals(coll.collisionNormal, BZVector2f(0f, 1f))
        assertEquals(2.5f, coll.penetration)
    }
}