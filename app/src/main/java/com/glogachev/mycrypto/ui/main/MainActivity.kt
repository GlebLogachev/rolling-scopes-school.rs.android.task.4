package com.glogachev.mycrypto.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.glogachev.mycrypto.App
import com.glogachev.mycrypto.databinding.ActivityMainBinding
import com.glogachev.mycrypto.domain.CryptoRepository
import com.glogachev.mycrypto.domain.model.Crypto
import com.glogachev.mycrypto.ui.addCrypto.AddOrUpdateCryptoActivity

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter by lazy {
        CryptoAdapter(this::moveToAddOrUpdateActivity)
    }
    private val viewModel: MainViewModel by viewModels {
        val repository: CryptoRepository = App.repository
        MainViewModelFactory(repository = repository)
    }

    private val newCryptoLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
            viewModel.reloadList()
               // val data = result.data
//                val crypto =
//                    data?.getParcelableExtra<Crypto>(AddOrUpdateCryptoActivity.RESULT) as Crypto
                //Log.d("moon", crypto.toString())

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupCallbacks()
        observe()

        Log.d("moon","onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("moon","onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("moon","onRestart")
    }
    override fun onResume() {
        super.onResume()
        Log.d("moon","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("moon","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("moon","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("moon","onDestroy")
    }

    private fun observe() {
        viewModel.cryptoList.observe(this, { list ->
            Log.d("moon", list.toString())
            adapter.submitList(list)
        })
    }

    private fun setupCallbacks() {
        binding.recView.adapter = adapter
        binding.fabAddCrypto.setOnClickListener {
            moveToAddOrUpdateActivity(null)
        }
    }

    private fun moveToAddOrUpdateActivity(crypto : Crypto?) {
        val intent = Intent(this, AddOrUpdateCryptoActivity::class.java).apply {
            crypto?.let {
                putExtra(AddOrUpdateCryptoActivity.RESULT, it)
            }
        }
        newCryptoLauncher.launch(intent)
    }
}