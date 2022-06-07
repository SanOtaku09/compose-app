import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class stopWatchWorker {

    var coroutine = CoroutineScope( Dispatchers.Main )
    var timeInMilis = 0L
    var lastTime = 0L
    var isActive = false
    var formattedTime = mutableStateOf( "00:00:00:000" )
    var timeStamp = mutableStateOf( StringBuilder("") )

    fun start() {
        if ( isActive ) return
        coroutine.launch {
            this@stopWatchWorker.isActive = true
            lastTime = System.currentTimeMillis()
            while ( this@stopWatchWorker.isActive ) {
                delay( 10L )
                timeInMilis += System.currentTimeMillis() - lastTime
                lastTime = System.currentTimeMillis()
                formattedTime.value = formatTime( timeInMilis )
            }
        }
    }

    fun pause() {
        isActive = false
        timeStamp.value.append( formattedTime.value )
        timeStamp.value.append( "\n" )
    }

    fun reset() {
        isActive = false
        coroutine.cancel()
        coroutine = CoroutineScope( Dispatchers.Main )
        timeInMilis = 0L
        lastTime = 0L
        formattedTime.value = "00:00:00:000"
        timeStamp.value = StringBuilder( "" )
    }

    private fun formatTime( timeInMillis : Long ) : String {
        return LocalDateTime.ofInstant(
            Instant.ofEpochMilli( timeInMillis ) ,
            // using default zone id sets the zone id value so for stop watch zone id with UTC 0 should be used or else you get your zonr UTC value added init
            ZoneId.of("Greenwich")
        ).let { time ->
            DateTimeFormatter.ofPattern(
                "HH:mm:ss:SSS" ,
                Locale.getDefault()
            ).let {formatter ->
                time.format( formatter )
            }
        }
    }

}