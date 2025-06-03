package com.example.catfact.Fragment.HistoricFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.catfact.SetupLogic
import com.example.catfact.databinding.FragmentHistoricListBinding

class HistoricListFragment : Fragment() {

    private var _fragmentBinding: FragmentHistoricListBinding? = null
    private val binding get() = _fragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentBinding = FragmentHistoricListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SetupLogic().logicHistoric(
            this, requireContext().applicationContext,
            this, binding.RecycleId, binding.removeViewTextId,
            binding.linearViewTextId, binding.textViewId
        )

    }
}