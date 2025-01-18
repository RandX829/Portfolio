package tokyo.randx.portfolio.kmp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hands_on.composeapp.generated.resources.Res
import hands_on.composeapp.generated.resources.eg
import hands_on.composeapp.generated.resources.fr
import hands_on.composeapp.generated.resources.id
import hands_on.composeapp.generated.resources.jp
import hands_on.composeapp.generated.resources.mx
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showCountries by remember { mutableStateOf(false) }
        var localTime by remember { mutableStateOf("No location selected") }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                localTime,
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Row(modifier = Modifier.padding(top = 8.dp)) {
                DropdownMenu(
                    expanded = showCountries,
                    onDismissRequest = { showCountries = false }
                ) {
                    countries().forEach { (name, timeZone, image) ->
                        DropdownMenuItem(
                            onClick = {
                                localTime = localTime(name, timeZone)
                                showCountries = false
                            }
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painterResource(image),
                                    modifier = Modifier.size(48.dp).padding(end = 16.dp),
                                    contentDescription = "$name flag"
                                )
                                Text(name)
                            }
                        }
                    }
                }
            }
            Button(
                onClick = { showCountries = !showCountries },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Select Location")
            }
        }
    }
}

fun localTime(
    location: String,
    timeZone: TimeZone
): String {
    val now = Clock.System.now()
    val localTime = now.toLocalDateTime(timeZone).time

    return "The time in $location is ${ localTime.formatted() }"
}

fun LocalTime.formatted() = "$hour:$minute:$second"

data class Country(
    val name: String,
    val timeZone: TimeZone,
    val image: DrawableResource
)

fun countries() = listOf(
    Country("Japan", TimeZone.of("Asia/Tokyo"), Res.drawable.jp),
    Country("France", TimeZone.of("Europe/Paris"), Res.drawable.fr),
    Country("Mexico", TimeZone.of("America/Mexico_City"), Res.drawable.mx),
    Country("Indonesia", TimeZone.of("Asia/Jakarta"), Res.drawable.id),
    Country("Egypt", TimeZone.of("Africa/Cairo"), Res.drawable.eg),
)