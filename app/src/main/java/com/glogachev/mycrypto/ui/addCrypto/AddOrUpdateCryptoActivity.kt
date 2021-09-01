package com.glogachev.mycrypto.ui.addCrypto

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.glogachev.mycrypto.App
import com.glogachev.mycrypto.R
import com.glogachev.mycrypto.databinding.ActivityAddOrUpdateCryptoBinding
import com.glogachev.mycrypto.domain.CryptoRepository
import com.glogachev.mycrypto.domain.model.Crypto

class AddOrUpdateCryptoActivity : AppCompatActivity() {

    private val binding: ActivityAddOrUpdateCryptoBinding by lazy {
         ActivityAddOrUpdateCryptoBinding.inflate(layoutInflater)
    }

    private val viewModel: AddOrUpdateViewModel by viewModels {
        val repository: CryptoRepository = App.repository
        AddOrUpdateViewModelFactory(repository = repository)
    }
    private val actualCrypto: Crypto? by lazy {
        intent.getParcelableExtra<Crypto>(RESULT)  
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupCallbacks()
        observe()
    }

    private fun observe() {
        viewModel
            .clickedButton
            .observe(this,{ clickedButton ->
               if(clickedButton) {
                   setResult(RESULT_OK)
                   finish()
               }
            })
    }


    // обновить и добавить сделать одной кнопкой
    private fun setupCallbacks() {

        binding.btnDelete.setOnClickListener {
            viewModel.deleteCrypto(actualCrypto!!.id)
        }
        if(actualCrypto == null ) {
            newCryptoState()
        } else {
            updateCryptoState()
        }

    }

    private fun updateCryptoState() {
        binding.apply {
            tvViewState.text = getString(R.string.update_state)
            tieCoinName.setText(actualCrypto!!.name)
            tieCoinPrice.setText(actualCrypto!!.price.toString())
            tieQuantity.setText(actualCrypto!!.quantity.toString())
            btnAddOrUpdate.text = getString(R.string.update)
            btnDelete.isVisible = true
            btnAddOrUpdate.setOnClickListener {
                addOrUpdateCrypto(true)
            }
        }
    }

    private fun newCryptoState() {
        binding.apply {
            tvViewState.text = getString(R.string.add_new_crypto_state)
            btnDelete.isVisible = false
            btnAddOrUpdate.text = getString(R.string.add)
            btnAddOrUpdate.setOnClickListener {
                addOrUpdateCrypto(false)
            }
        }
    }

    private fun addOrUpdateCrypto(isUpdate : Boolean ) {
        val cryptoInfo = Crypto(
            id = actualCrypto?.id ?: 0,
            name = binding.tieCoinName.text.toString(),
            price = binding.tieCoinPrice.text.toString().toFloatOrNull() ?: 0F,
            quantity = binding.tieQuantity.text.toString().toFloatOrNull() ?: 0F
        )
        if (isUpdate) {
                viewModel.updateCrypto(cryptoInfo)
        } else {
            viewModel.addCrypto(cryptoInfo)
        }
    }

    companion object {
        const val RESULT = "RESULT"
    }
}

