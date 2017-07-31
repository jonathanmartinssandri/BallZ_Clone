package ballzclone.copetti.com.ballzclone

import android.app.Activity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView

class AboutActivity : Activity() {

    private var gitHubRepoLink : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        gitHubRepoLink = findViewById(R.id.txtGitHubRepo) as TextView
        gitHubRepoLink!!.movementMethod = LinkMovementMethod.getInstance()
    }
}
