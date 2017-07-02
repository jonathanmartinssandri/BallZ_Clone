package ballzclone.copetti.com.ballzclone

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainMenuActivity : AppCompatActivity() {

    private var bestOverall : TextView? = null
    private var currentScore: TextView? = null
    private var currentCoinValue: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }

    fun btnOpenGameClicked(v: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun btnOpenHighestScoresClicked(v: View) {
        val intent = Intent(this, HighestScoreActivity::class.java)
        startActivity(intent)
    }

    fun exitGame(v : View) {
        finish();
    }
}
