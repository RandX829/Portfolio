package tokyo.randx.portfolio.android.composeinviews

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val full = this.findViewById<TextView>(R.id.full)
        full.setOnClickListener {
            val intent = Intent(this, FullComposeActivity::class.java)
            startActivity(intent)
        }

        val partial = this.findViewById<TextView>(R.id.partial)
        partial.setOnClickListener {
            val intent = Intent(this, PartialComposeActivity::class.java)
            startActivity(intent)
        }
    }
}