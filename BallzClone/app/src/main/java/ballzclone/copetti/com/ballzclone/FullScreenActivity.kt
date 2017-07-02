package ballzclone.copetti.com.ballzclone

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

open class FullScreenActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}
