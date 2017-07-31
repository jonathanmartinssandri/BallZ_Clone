package ballzclone.copetti.com.ballzclone

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView

class MainMenuActivity : FullScreenActivity() {

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

    fun btnAboutClicked(v: View) {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    fun exitGame(v : View) {
        finish();
    }
}
