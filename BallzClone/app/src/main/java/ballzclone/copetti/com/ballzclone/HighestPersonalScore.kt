package ballzclone.copetti.com.ballzclone

import android.content.Context
import ballzclone.copetti.com.ballzclone.file.AndroidFileManager
import java.io.FileNotFoundException

/**
 * Created by lhcopetti on 31/07/17.
 */
class HighestPersonalScore {

    private val fileName = "bestPersonalScore.txt"
    private val file = AndroidFileManager(fileName)

    fun save(ctx: Context, bestScore: Int) {
        file.writeToFile(ctx, bestScore.toString())
    }

    fun load(ctx: Context) : Int {

        try {
            val string = file.readFromFile(ctx)
            if (string != null && !string.isEmpty())
                return string.toInt()
        }
        catch(ex: FileNotFoundException) {
            save(ctx, 0)
        }

        return 0
    }

}