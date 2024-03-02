package tokyo.randx.portfolio.android.navigation4compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExpenseListScreen(
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,

    ) {
        items(items = expenses) { expense ->
            Row(
                modifier = modifier.fillMaxSize().padding(horizontal = 16.dp).clickable { onItemClick(expense.id) },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = modifier
                        .padding(),
                    text = expense.category,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    modifier = modifier,
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null
                )
            }
            Divider()
        }
    }
}