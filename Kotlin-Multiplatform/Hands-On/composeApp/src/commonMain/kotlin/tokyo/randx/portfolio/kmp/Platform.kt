package tokyo.randx.portfolio.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform