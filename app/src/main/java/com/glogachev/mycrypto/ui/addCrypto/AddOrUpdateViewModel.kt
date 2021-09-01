package com.glogachev.mycrypto.ui.addCrypto

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.glogachev.mycrypto.domain.CryptoRepository
import com.glogachev.mycrypto.domain.model.Crypto
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AddOrUpdateViewModel(
    private val repository: CryptoRepository
) : ViewModel() {

    private var _clickedButton: MutableLiveData<Boolean> = MutableLiveData(false)
    val clickedButton: LiveData<Boolean>
    get() = _clickedButton

    fun addCrypto(crypto: Crypto){
        repository
            .addCrypto(crypto)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                _clickedButton.value = true
            }
    }
    fun deleteCrypto(id: Int) {
        repository
            .deleteCrypto(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                _clickedButton.value = true
            }
    }

    fun updateCrypto(crypto: Crypto) {
        repository
            .updateCrypto(crypto)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _clickedButton.value = true
            }
    }

}

class AddOrUpdateViewModelFactory(private val repository: CryptoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddOrUpdateViewModel(repository) as T
    }
}