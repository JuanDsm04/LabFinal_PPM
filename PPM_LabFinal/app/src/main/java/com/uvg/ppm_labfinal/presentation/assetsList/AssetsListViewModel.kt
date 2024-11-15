package com.uvg.ppm_labfinal.presentation.assetsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.ppm_labfinal.data.local.repository.LocalAssetRepository
import com.uvg.ppm_labfinal.data.remote.KtorCoinCapAPI
import com.uvg.ppm_labfinal.di.AppDependencies
import com.uvg.ppm_labfinal.domain.remote.util.DataError
import com.uvg.ppm_labfinal.domain.remote.util.onError
import com.uvg.ppm_labfinal.domain.remote.util.onSuccess
import com.uvg.ppm_labfinal.domain.repository.AssetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssetsListViewModel(
    private val assetRepository: AssetRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<AssetsListState> = MutableStateFlow(AssetsListState())
    val uiState = _uiState.asStateFlow()

    init {
        getAssetsListData()
    }

    private fun getAssetsListData() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            assetRepository.getAllAssets()
                .onSuccess { assets ->
                    _uiState.update { state ->
                        state.copy(
                            data = assets,
                            isLoading = false,
                            hasError = false
                        )
                    }
                }
                .onError { error ->
                    setError(error)
                }
        }
    }

    private fun setError(error: DataError) {
        _uiState.update { state ->
            state.copy(
                hasError = true,
                isLoading = false
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY]) {
                    "Application context is missing!"
                }
                val db = AppDependencies.provideDatabase(application)
                val api = KtorCoinCapAPI(AppDependencies.provideHttpClient())
                AssetsListViewModel(
                    assetRepository = LocalAssetRepository(
                        coinCapAPI = api,
                        assetDao = db.assetDao()
                    )
                )
            }
        }
    }
}
