package cl.uchile.postgrado.mobile.calendar.view.layouts

import androidx.compose.runtime.Composable
import cl.uchile.postgrado.mobile.calendar.model.MonthObject
import cl.uchile.postgrado.mobile.calendar.view.components.MonthComponent
import java.time.Month

@Composable
fun CompactLayout() {
    // Esta es la vista de 1 solo mes
    MonthComponent(MonthObject(Month.NOVEMBER, 2025), viewModel)   // Parámetro va el mes a pintar
}