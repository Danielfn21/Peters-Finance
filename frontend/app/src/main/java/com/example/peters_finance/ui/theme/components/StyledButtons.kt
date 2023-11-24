import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StyledButton(
    text: String,
    onClick: () -> Unit,
    colorScheme: ColorScheme = MaterialTheme.colorScheme
) {
    val fontSize = 32.sp
    val spacing = 10.dp

    Button(
        onClick = {
            onClick.invoke()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorScheme.primary,
            contentColor = colorScheme.secondary
        )
    ) {
        Text(text = text, fontSize = fontSize)
    }

    Spacer(modifier = Modifier.size(spacing))
}
