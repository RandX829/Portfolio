package tokyo.randx.portfolio.kmp

import androidx.compose.ui.window.ComposeUIViewController
import tokyo.randx.portfolio.kmp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}