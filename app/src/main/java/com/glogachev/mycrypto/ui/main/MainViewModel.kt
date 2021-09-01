package com.glogachev.mycrypto.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.glogachev.mycrypto.domain.CryptoRepository
import com.glogachev.mycrypto.domain.model.Crypto
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val repository: CryptoRepository
) : ViewModel() {
    init {
        obtainCryptoList()
    }
    private var _cryptoList: MutableLiveData<List<Crypto>> = MutableLiveData()
    val cryptoList: LiveData<List<Crypto>>
        get() = _cryptoList

    private fun obtainCryptoList() {
        repository
            .obtainCryptoList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                _cryptoList.value = list
            }
    }

    fun reloadList() {
        obtainCryptoList()
    }
}

class MainViewModelFactory(private val repository: CryptoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}