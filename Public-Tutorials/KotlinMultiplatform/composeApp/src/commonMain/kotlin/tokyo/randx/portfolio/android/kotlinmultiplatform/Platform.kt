package tokyo.randx.portfolio.android.kotlinmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform