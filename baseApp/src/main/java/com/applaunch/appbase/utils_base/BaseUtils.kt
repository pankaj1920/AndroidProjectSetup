import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.applaunch.appbase.R
import com.applaunch.appbase.utils_base.NetworkConnection
import java.util.regex.Pattern


fun shareOnAllApps(context: Context, body: String?) {
    val intent = Intent(Intent.ACTION_SEND)
    val sub = "Issue in " + context.getString(R.string.app_name)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_SUBJECT, sub)
    intent.putExtra(Intent.EXTRA_TEXT, body)
    context.startActivity(Intent.createChooser(intent, "Share using"))
}

fun doNothing() = Unit


fun postDelayed(action: Runnable, delayMillis: Long) {
    Handler(Looper.getMainLooper()).postDelayed(action, delayMillis)
}


fun String.regexChecker(pattern: String): Boolean {
    return Pattern.compile(pattern).matcher(this).matches()
}

fun Context.checkInternetConnection(): Boolean {
    return NetworkConnection(this).hasNetworkConnection()
}