import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun clockUi(
    date  : MutableState<String> ,
    clock : MutableState<String>
) {
    Column (
        Modifier.fillMaxSize().background( Color.Black ) ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            clock.value ,
            fontSize = 50.sp ,
            fontWeight = FontWeight.Bold ,
            fontFamily = FontFamily.Monospace ,
            color = Color.White
        )

        Spacer( Modifier.height( 10.dp ) )

        Text(
            date.value ,
            fontSize = 30.sp ,
            fontWeight = FontWeight.SemiBold ,
            fontFamily = FontFamily.SansSerif ,
            color = Color.Gray
        )

    }
}