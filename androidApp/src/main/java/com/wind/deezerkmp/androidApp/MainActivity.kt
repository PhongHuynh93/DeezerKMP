package com.wind.deezerkmp.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.commit
import timber.log.Timber
import util.addFragment
import util.findFragmentById

class MainActivity : AppCompatActivity(R.layout.fragment) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(R.id.root, MainFragment.newInstance())
    }
}
