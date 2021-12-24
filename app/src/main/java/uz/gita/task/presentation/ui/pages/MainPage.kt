package uz.gita.task.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.task.R
import uz.gita.task.app.App
import uz.gita.task.databinding.PageMainBinding
import uz.gita.task.presentation.ui.adapters.AllDataAdapter
import uz.gita.task.presentation.ui.adapters.ContentsLoadStateAdapter
import uz.gita.task.presentation.viewModel.impls.MainPageViewModelImpl
import uz.gita.task.presentation.viewModel.interfaces.MainPageViewModel
import uz.gita.task.utils.gone
import uz.gita.task.utils.scope
import uz.gita.task.utils.visible

@AndroidEntryPoint
class MainPage : Fragment(R.layout.page_main) {

    private val binding by viewBinding(PageMainBinding::bind)
    private val viewModel: MainPageViewModel by viewModels<MainPageViewModelImpl>()
    private val adapter by lazy { AllDataAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        loadViews()
        loadFlows()


    }

    private fun loadFlows() = binding.scope {
             lifecycleScope.launchWhenStarted {
                 viewModel.contentLoad.onEach {
                     progress.gone()
                     adapter.submitData(it)
                 }.collect()
             }
        lifecycleScope.launchWhenStarted {
            viewModel.pagingAdapterFlow.collect {
                progress.visible()
                rv.adapter = adapter
                rv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

                rv.addItemDecoration(
                    DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
                )

                adapter.withLoadStateHeaderAndFooter(
                    ContentsLoadStateAdapter { adapter.retry() },
                    ContentsLoadStateAdapter { adapter.retry() }
                )

            }
        }
    }

    private fun loadViews() = binding.scope {
        requireArguments()?.let {
            it.getString("key")?.let { it1 -> viewModel.getCategoryName(it1) }
        }
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                progress.isVisible = it.refresh is LoadState.Loading
            }
        }
    }
}