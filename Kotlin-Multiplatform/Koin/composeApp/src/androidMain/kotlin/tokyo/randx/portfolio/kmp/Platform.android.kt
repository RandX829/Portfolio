package tokyo.randx.portfolio.kmp

import android.os.Build
import org.koin.core.scope.Scope

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(scope: Scope): Platform = AndroidPlatform()