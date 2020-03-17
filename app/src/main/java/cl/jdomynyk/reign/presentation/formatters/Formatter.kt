package cl.jdomynyk.reign.presentation.formatters

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class Formatter @Inject constructor() {

    companion object {
        private const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'"
        private const val APP_DATE_FORMAT = "dd/MM/yyyy HH:mm"
    }

    fun formatDate(serverDate: String): String {
        lateinit var date: Date
        try {
            val serverDateFormat = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.getDefault())
            date = serverDateFormat.parse(serverDate)
        } catch (ignored: ParseException) {
        }

        val appDateFormat = SimpleDateFormat(APP_DATE_FORMAT, Locale.getDefault())
        return appDateFormat.format(date)
    }
}