

import android.graphics.Color
import android.view.View
import com.applaunch.appbase.utils_base.BaseCodeSnippet
import com.google.android.material.snackbar.Snackbar

object SnackbarMessage {
    fun showNetworkMessage(view: View, baseCodeSnippet: BaseCodeSnippet) {
        val snackBar = Snackbar.make(view, "No Network found!", Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.RED)
        snackBar.setTextColor(Color.WHITE)
        snackBar.setAction("Settings") { baseCodeSnippet.showNetworkSettings() }
        snackBar.show()
    }

}