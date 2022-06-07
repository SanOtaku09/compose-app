import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun stopWatchUi(
    formattedDate : MutableState<String> ,
    start : () -> Unit ,
    pause : () -> Unit ,
    reset : () -> Unit ,
    timeStamp : MutableState<StringBuilder>
){
    Column(
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = formattedDate.value ,
            fontWeight = FontWeight.Bold ,
            fontSize = 50.sp ,
            color = Color.Black
        )

        Spacer( Modifier.height( 20.dp ) )

        Row ( Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.Center ) {

            Button( start ) { Text( "Start" ) }

            Spacer( Modifier.width( 16.dp ) )

            Button( pause ) { Text( "Stop" ) }

            Spacer( Modifier.width( 16.dp ) )

            Button( reset ) { Text( "Reset" ) }

        }
    }

    Column ( Modifier.padding( 10.dp ) ) {
        if ( timeStamp.value.length != 0 ) Text(
            "Time Stamps" ,
            color = Color.Black ,
            fontWeight = FontWeight.Bold
        )
        Text( timeStamp.value.toString() )
    }

}