package com.wind.deezerkmp.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(R.layout.fragment) {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit(true) {
                add(R.id.root, MainFragment.newInstance())
            }
        }
    }
}
