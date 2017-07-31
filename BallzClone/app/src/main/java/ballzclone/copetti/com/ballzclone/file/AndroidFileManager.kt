package ballzclone.copetti.com.ballzclone.file

import android.content.Context
import android.os.Environment
import android.widget.Toast
import java.io.*
import java.io.File.separator



/**
 * Created by lhcopetti on 30/07/17.
 */
class AndroidFileManager(fileName: String) {

    private val fileName = fileName
    private val filePath = Environment.getExternalStorageDirectory()
            .absolutePath + File.separator;


    fun writeToFile(ctx: Context, contents: String) : Boolean {
        try {
//            var input = FileOutputStream(File(filePath + fileName))
//            input.write(contents.toByteArray())
            var outputStream = ctx.openFileOutput(fileName, Context.MODE_PRIVATE)
//            outputStream.write(contents.toByteArray())
            var dataOutput = DataOutputStream(outputStream)
            dataOutput.writeUTF(contents)
            outputStream.close()
            dataOutput.close()
            return true

        } catch(ex: Exception) {
            Toast.makeText(ctx, "There was an error while saving contents to file named $fileName", Toast.LENGTH_LONG).show()
            return false
        }
    }

    fun readFromFile(ctx: Context) : String? {
//        var input = FileInputStream(File(filePath + fileName))
//        val contents = input.readBytes().toString()
        var input = ctx.openFileInput(fileName)
        var dataInput = DataInputStream(input)
        val contents = dataInput.readUTF()
        input.close()
        dataInput.close()
        return contents
    }
}