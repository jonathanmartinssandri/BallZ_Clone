package ballzclone.copetti.com.ballzclone

/**
 * Created by Pichau on 08/07/2017.
 */
class AABBCollision {

    companion object {
        fun checkCollision(lhs: BZRect, rhs: BZRect) : Boolean {

            return  lhs.top < rhs.bottom &&
                    lhs.bottom > rhs.top &&
                    lhs.left < rhs.right &&
                    lhs.right > rhs.left
        }
    }


}