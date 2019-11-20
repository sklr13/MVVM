package com.example

import android.os.Bundle
import android.view.View.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.core.ui.BaseActivity
import com.example.core.ui.BaseFragment
import com.example.mvvm.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_content.*

class MainActivity : BaseActivity() {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        retryBtn.setOnClickListener {
            val currentFragment = supportFragmentManager.getCurrentNavigationFragment()
            if (currentFragment is BaseFragment) {
                errorContent.visibility = GONE
                progressBar.visibility = VISIBLE
                currentFragment.tryAgain(getRetryAction())
            }
        }
    }

    override fun showProgress() {
        progressBar.visibility = VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = INVISIBLE
    }

    override fun saveRetryAction(r: (() -> Unit)?, message: String?) {
        super.saveRetryAction(r, message)
        showErrorScreeen(message)
    }

    private fun showErrorScreeen(message: String?) {
        errorContent.visibility = VISIBLE
        errorMessageTV.text = message
    }
}
