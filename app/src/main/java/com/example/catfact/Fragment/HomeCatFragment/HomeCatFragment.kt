package com.example.catfact.Fragment.HomeCatFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.catfact.SetupLogic
import com.example.catfact.databinding.FragmentHomeCatBinding

class HomeCatFragment : Fragment() {

    private var _fragmentBinding: FragmentHomeCatBinding? = null
    private val binding get() = _fragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _fragmentBinding = FragmentHomeCatBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SetupLogic().logicCat(
            this@HomeCatFragment,
            this@HomeCatFragment,
            binding.Progressid,
            binding.TextFactId,
            binding.ButtonSavedId,
            binding.ButtonClickId)

    }
}