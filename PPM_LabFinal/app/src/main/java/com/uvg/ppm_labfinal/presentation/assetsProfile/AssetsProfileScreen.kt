package com.uvg.ppm_labfinal.presentation.assetsProfile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.ppm_labfinal.ui.theme.PPM_LabFinalTheme

@Composable
fun AssetsProfileRoute(
    onNavigateBack: () -> Unit
){

    AssetsProfileScreen(
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AssetsProfileScreen(
    onNavigateBack: () -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        TopAppBar(
            title = {
                Text("Asset")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                titleContentColor = MaterialTheme.colorScheme.onSecondary,
                navigationIconContentColor = MaterialTheme.colorScheme.onSecondary,
            ),
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ){
            Text(text = "Nombre: ")
            Text(text = "Símbolo: ")
            Text(text = "Precio en USD: ")
            Text(text = "Cambio porcentual: ")
            Text(text = "Supply: ")
            Text(text = "Máximo Supply: ")
            Text(text = "Market Cap USD: ")
            Text(text = "Fecha de actualización: ")
        }
    }
}

@Preview
@Composable
private fun PreviewAssetsProfileScreen() {
    PPM_LabFinalTheme {
        Surface {
            AssetsProfileScreen(
                onNavigateBack = {}
            )
        }
    }
}
