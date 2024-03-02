package tokyo.randx.portfolio.android.navigation4compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tokyo.randx.portfolio.android.navigation4compose.ui.theme.Navigation4ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation4ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Nav4ComposeNavHost()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Navigation4ComposeTheme {
        Nav4ComposeNavHost()
    }
}