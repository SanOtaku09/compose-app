import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class clock {

    val getMonth = mapOf(
        1 to "January" ,
        2 to "February" ,
        3 to "March" ,
        4 to "April" ,
        5 to "May" ,
        6 to "June" ,
        7 to "July" ,
        8 to "August" ,
        9 to "September" ,
        10 to "October" ,
        11 to "November" ,
        12 to "December"
    )
    var formattedDate = mutableStateOf( "-- - ----" )
    var formattedClock = mutableStateOf( "00:00:00:000" )
    val coroutine = CoroutineScope(Dispatchers.Main)

    init {
        formattedDate.value = formatLocalDateTime( "dd MM YYYY" , fetchDateTime() ).let {
            it.substring( 0 , 3 ) + getMonth[ it.substring( 3 ,5 ).toInt() ] + it.substring( it.length - 5 )
        }
        startTimeRefresher()
    }

    private fun fetchDateTime() : LocalDateTime {
        return LocalDateTime.ofInstant(
            Instant.now() ,
            ZoneId.systemDefault()
        )
    }

    private fun formatLocalDateTime( format : String , time : LocalDateTime ) : String {
        return DateTimeFormatter.ofPattern(
            format
        ).let {
            time.format( it )
        }
    }

    private fun startTimeRefresher() {
        coroutine.launch {
            while ( true ) {
                delay( 10L )
                formattedClock.value = fetchDateTime().let {
                    if ( it.hour == 11 && it.second > 50 ) {
                        this@clock.coroutine.launch {
                            delay( ( 59000 - ( it.second*1000 ) ).toLong() )
                            formattedDate.value = formatLocalDateTime( "dd MM YYYY" , fetchDateTime() ).let {
                                it.substring( 0 , 3 ) + getMonth[ it.substring( 3 ,5 ).toInt() ] + it.substring( it.length - 4 )
                            }
                        }
                    }
                    formatLocalDateTime( "HH:mm:ss:SSSS" , it )
                }
            }
        }
    }

}