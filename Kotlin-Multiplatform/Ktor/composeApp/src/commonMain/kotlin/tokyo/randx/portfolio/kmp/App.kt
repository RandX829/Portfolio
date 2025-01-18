package tokyo.randx.portfolio.kmp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val viewModel = RocketLaunchViewModel(SpaceXApi())
    val state by remember { viewModel.state }
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("SpaceX Launches", style = MaterialTheme.typography.h5) })
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (state.isLoading) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text("Loading...", style = MaterialTheme.typography.h4)
                    }
                } else {
                    LazyColumn {
                        items(state.launches) { launch: RocketLaunch ->
                            Column(modifier = Modifier.padding(all = 16.dp)) {
                                Text(
                                    text = "${launch.name} - ${launch.launchYear}",
                                    style = MaterialTheme.typography.h6
                                )
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    text = if (launch.isSuccess == true) "Successful" else "Unsuccessful",
                                    color = if (launch.isSuccess == true) Color.Green else Color.Red
                                )
                                Spacer(Modifier.height(8.dp))
                                val details = launch.details
                                if (details?.isNotBlank() == true) {
                                    Text(details)
                                }
                            }
                            Divider()
                        }
                    }
                }
            }
        }
    }
}