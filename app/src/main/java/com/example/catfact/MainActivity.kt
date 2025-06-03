package com.example.catfact

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.catfact.Fragment.HistoricFragment.HistoricListFragment
import com.example.catfact.Fragment.HomeCatFragment.HomeCatFragment
import com.example.catfact.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentConstruct(HomeCatFragment())

        val tabletWidth = resources.configuration.smallestScreenWidthDp

        if(tabletWidth >= 600) {

            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE

            SetupLogic().logicHistoric(this@MainActivity,this@MainActivity,
                this@MainActivity, binding.RecycleId, binding.removeViewTextId,
                binding.linearViewTextId,binding.textViewId)

            SetupLogic().logicCat(
                this@MainActivity, this@MainActivity,
                binding.Progressid, binding.TextFactId,
                binding.ButtonSavedId, binding.ButtonClickId
            )

        }

        binding.ButtonNavId?.setOnItemSelectedListener { menu ->

            when (menu.itemId) {
                R.id.HomeId -> fragmentConstruct(HomeCatFragment())
                R.id.HistoricListId -> fragmentConstruct(HistoricListFragment())
            }
            true
        }

    }

    private fun fragmentConstruct(fragment:Fragment) {

        if (binding.FrameLayoutId != null) {
            val fragSupport = supportFragmentManager
            val fragBegin = fragSupport.beginTransaction()
            fragBegin.replace(R.id.FrameLayoutId, fragment).commit()
        }

    }
}
