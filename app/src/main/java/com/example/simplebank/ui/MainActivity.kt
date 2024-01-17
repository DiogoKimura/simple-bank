package com.example.simplebank.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ds.widget.BottomView
import com.example.sdk.delegates.viewProvider
import com.example.simplebank.R
import com.example.simplebank.ui.payment.PaymentActivity
import com.example.simplebank.ui.product.ProductFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val bottomView by viewProvider<BottomView>(R.id.main_bottom_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupProductFragment(savedInstanceState)
        setupBottomView()
    }

    private fun setupBottomView() {
        bottomView.apply {
            btnPayment.setOnClickListener {
                val intent = Intent(context, PaymentActivity::class.java)
                startActivity(intent)
            }
            btnTransfer.setOnClickListener { }
            btnHelp.setOnClickListener { }
        }
    }

    private fun setupProductFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val productFragment = ProductFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, productFragment)
                .commit()
        }
    }
}