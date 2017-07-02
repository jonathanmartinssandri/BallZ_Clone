package ballzclone.copetti.com.ballzclone

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup

class GameActivity : FullScreenActivity() {

    private var gameView: View? = null
    private var gameLayout: ViewGroup? = null

    private var renderView : RenderView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameView = findViewById(R.id.mainGameView)
        gameLayout = findViewById(R.id.gameViewLayout) as ViewGroup

        var renderView: RenderView = RenderView(this)
        renderView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        gameLayout!!.addView(renderView)
    }

    fun btnPauseGameClicked(v : View)
    {

    }
}
