package cl.uchile.postgrado.mobile.calendar.view.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.postgrado.mobile.calendar.model.MonthObject
import cl.uchile.postgrado.mobile.calendar.view.components.MonthComponent
import java.time.Month

@Composable
fun ExpandedLayout(monthQty: Int, monthColumnQty: Int) {
    val actualDate = java.time.LocalDate.now()
    var monthDate = actualDate
    var totalMonths = 0
    val monthRows = monthQty / monthColumnQty

    // Esta es la vista de 3 meses en pantalla
    Column {
        for (i in 1.. monthRows) {
            Row {
                for (i in 1..monthColumnQty) {
                    if (totalMonths < monthQty) {
                        val month = monthDate.month
                        val year = monthDate.year
                        val monthObject = MonthObject(month, year)
                        MonthComponent(monthObject)
                        Spacer(Modifier.width(8.dp))
                        totalMonths += 1
                        monthDate = monthDate.plusMonths(1)
                    }
                }
            }
            Spacer(Modifier.width(8.dp))
        }
    }

/*  Row {
        MonthComponent(MonthObject(Month.NOVEMBER, 2025))   // Parámetro va el mes 1 a pintar
        Spacer(Modifier.width(8.dp))
        MonthComponent(MonthObject(Month.DECEMBER, 2025))   // Parámetro va el mes 2 a pintar
        Spacer(Modifier.width(8.dp))
        MonthComponent(MonthObject(Month.JANUARY, 2026))   // Parámetro va el mes 3 a pintar
    } */
}