package com.punkapp.ui.beers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.punkapp.databinding.MainFragmentBinding
import com.punkapp.ui.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class BeerListFragment : Fragment() {

    companion object {
        fun newInstance() = BeerListFragment()
    }

    private val viewModel: BeerListViewModel by viewModels()
    private val adapter = BeersAdapter()
    private val binding: MainFragmentBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchBeers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = MainFragmentBinding.inflate(inflater, container, false).apply {
        vm = viewModel
        lifecycleOwner = viewLifecycleOwner
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.beerList.adapter = adapter

        lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                if (it.isError) {
                    binding.error.visibility = VISIBLE
                } else {
                    adapter.appendItems(it.beers)
                }
            }
        }
    }
}