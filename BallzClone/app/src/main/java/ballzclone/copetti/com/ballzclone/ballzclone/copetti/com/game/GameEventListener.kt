package ballzclone.copetti.com.ballzclone.ballzclone.copetti.com.game

/**
 * Created by Pichau on 29/07/2017.
 */

interface GameEventListener {
    fun handleEndGameEvent()
    fun handleUpdateScoreEvent(newScore: Int)
}