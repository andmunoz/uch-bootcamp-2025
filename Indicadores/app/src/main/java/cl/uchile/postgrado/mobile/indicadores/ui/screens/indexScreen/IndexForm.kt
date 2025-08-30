package cl.uchile.postgrado.mobile.indicadores.ui.screens.indexScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import cl.uchile.postgrado.mobile.indicadores.R
import cl.uchile.postgrado.mobile.indicadores.model.IndexViewModel
import cl.uchile.postgrado.mobile.indicadores.model.Indicador
import cl.uchile.postgrado.mobile.indicadores.model.IndicadorInternacionalEnumeration
import cl.uchile.postgrado.mobile.indicadores.model.IndicadorNacionalEnumeration
import cl.uchile.postgrado.mobile.indicadores.ui.components.Destination
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndexForm(navController: NavHostController,
              snackbarHostState: SnackbarHostState,
              innerPadding: PaddingValues,
              destination: Destination,
              indexModel: IndexViewModel = viewModel()) {
    var expandedIndex by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val businessIndex by indexModel.businessIndex.collectAsState()
    var indexSelected by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        indexModel.indexType = destination.contentDescription

        if (destination == Destination.NAC) {
            Text(
                "Índices Nacionales",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        } else if (destination == Destination.INT) {
            Text(
                "Índices Internacionales",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }

        ExposedDropdownMenuBox(
            expanded = expandedIndex,
            onExpandedChange = { expandedIndex = !expandedIndex },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = indexSelected,
                onValueChange = { },
                readOnly = true,
                label = { Text(stringResource(R.string.business_index_text)) },
                trailingIcon = { TrailingIcon(expanded = expandedIndex) },
                isError = indexModel.indexErrorMessage != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryEditable, true)
            )
            ExposedDropdownMenu(
                expanded = expandedIndex,
                onDismissRequest = { expandedIndex = false }
            ) {
                if (indexModel.indexType == "Nacionales") {
                    IndicadorNacionalEnumeration.entries.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option.nombre) },
                            onClick = {
                                expandedIndex = false
                                indexSelected = option.nombre
                                indexModel.onIndexChange(option.codigo)
                            }
                        )
                    }
                }
                else {
                    IndicadorInternacionalEnumeration.entries.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option.nombre) },
                            onClick = {
                                expandedIndex = false
                                indexSelected = option.nombre
                                indexModel.onIndexChange(option.codigo)
                            }
                        )
                    }
                }
            }
        }
        indexModel.indexErrorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        TextField(
            value = indexModel.date,
            onValueChange = { indexModel.onDateChange(it) },
            label = { Text(stringResource(R.string.date_text)) },
            placeholder = { Text(stringResource(R.string.date_placeholder)) },
            isError = indexModel.dateErrorMessage != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        indexModel.dateErrorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Button(
            onClick = {
                val result = indexModel.validateForm()
                if (result.isSuccess) {
                    indexModel.getIndex()
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar("El formulario presenta errores")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(stringResource(R.string.query_button))
        }

    if (businessIndex != null && businessIndex.codigo != "" && businessIndex.serie.size > 0) {
            Row() {
                Text(
                    text = businessIndex.nombre,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = businessIndex.serie[0].valor.toString(),
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = businessIndex.unidad_medida.toString(),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}