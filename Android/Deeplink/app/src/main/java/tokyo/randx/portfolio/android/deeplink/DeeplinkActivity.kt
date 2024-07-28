package tokyo.randx.portfolio.android.deeplink

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DeeplinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_deeplink)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.deeplink)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        displayDeeplinkInfo()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        displayDeeplinkInfo()
    }

    private fun displayDeeplinkInfo() {
        val action: String? = intent?.action
        val data: Uri? = intent?.data

        findViewById<TextView>(R.id.deeplink_action).text = action
        findViewById<TextView>(R.id.deeplink_data).text = data?.scheme + "://" + data?.host + data?.path
    }
}