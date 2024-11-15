package com.uvg.ppm_labfinal.presentation.assetsList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ppm_labfinal.ui.theme.PPM_LabFinalTheme
import com.uvg.ppm_labfinal.data.model.Asset
import com.uvg.ppm_labfinal.presentation.assetsList.AssetsListViewModel

@Composable
fun AssetsListRoute(
    onAssetClick: (Int) -> Unit,
    viewModel: AssetsListViewModel = viewModel(factory = AssetsListViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    AssetsListScreen(
        assetList = uiState.data,
        isLoading = uiState.isLoading,
        hasError = uiState.hasError,
        onAssetClick = onAssetClick,
        onRetry = { /**/ }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AssetsListScreen(
    assetList: List<Asset>,
    isLoading: Boolean,
    hasError: Boolean,
    onAssetClick: (Int) -> Unit,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text("Assets")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                titleContentColor = MaterialTheme.colorScheme.onSecondary,
                navigationIconContentColor = MaterialTheme.colorScheme.onSecondary,
            )
        )

        when {
            isLoading -> {
                Text(
                    text = "Loading...",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                )
            }
            hasError -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Text(text = "Error loading assets. Please try again.")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Retry",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable { onRetry() }
                    )
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    items(assetList) { asset ->
                        AssetItem(
                            asset = asset,
                            onClick = { onAssetClick(asset.name.hashCode()) }
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun AssetItem(
    asset: Asset,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() }
    ) {
        Text(text = "Nombre: ${asset.name}")
        Text(text = "Símbolo: ${asset.symbol}")
        Text(text = "Precio USD: ${asset.priceUsd}")
        Text(text = "Cambio: ${asset.changePercent24Hr}")
    }
}

@Preview
@Composable
private fun PreviewAssetsListScreen() {
    PPM_LabFinalTheme {
        Surface {
            AssetsListScreen(
                assetList = listOf(
                    Asset(id = "1", name = "Bitcoin", symbol = "BTC", priceUsd = 45000.0, changePercent24Hr = "+2.5%"),
                    Asset(id = "2", name = "Ethereum", symbol = "ETH", priceUsd = 3000.0, changePercent24Hr = "-1.2%"),
                    Asset(id = "3", name = "Dogecoin", symbol = "DOGE", priceUsd = 0.07, changePercent24Hr = "+5.1%")
                ),
                isLoading = false,
                hasError = false,
                onAssetClick = {},
                onRetry = {}
            )
        }
    }
}
