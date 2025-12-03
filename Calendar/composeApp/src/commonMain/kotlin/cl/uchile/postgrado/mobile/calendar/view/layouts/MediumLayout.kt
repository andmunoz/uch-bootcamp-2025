package cl.uchile.postgrado.mobile.calendar.view.layouts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.postgrado.mobile.calendar.model.MonthObject
import cl.uchile.postgrado.mobile.calendar.view.components.MonthComponent
import cl.uchile.postgrado.mobile.calendar.viewmodel.CalendarViewModel
import java.time.Month

@Composable
fun MediumLayout(viewModel: CalendarViewModel) {
    // Esta es la vista de 2 meses en pantalla
    Row {
        MonthComponent(MonthObject(Month.NOVEMBER, 2025), viewModel)   // Parámetro va el mes 1 a pintar
        Spacer(Modifier.width(8.dp))
        MonthComponent(MonthObject(Month.DECEMBER, 2025), viewModel)   // Parámetro va el mes 2 a pintar
    }
}