package uz.gita.task.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import uz.gita.task.R
import uz.gita.task.databinding.PageHomeBinding
import uz.gita.task.presentation.viewModel.impls.HomeViewModelImpl
import uz.gita.task.presentation.viewModel.interfaces.HomeViewModel
import uz.gita.task.utils.scope
@AndroidEntryPoint
class HomePage:Fragment(R.layout.page_home) {

    private val binding by viewBinding(PageHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private var _adapter: uz.gita.task.presentation.ui.adapters.PagerAdapter? = null
    private val adapter: uz.gita.task.presentation.ui.adapters.PagerAdapter get() = _adapter!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadFlows()
    }

    private fun loadFlows() = binding.scope {
        lifecycleScope.launchWhenStarted {
            viewModel.categoriesTabFlow.collect {
                _adapter = uz.gita.task.presentation.ui.adapters.PagerAdapter(
                    it,
                    childFragmentManager,
                    lifecycle
                )
                viewPager.adapter = adapter
                TabLayoutMediator(
                    tabLayout, viewPager
                ) { tab, position ->
                    tab.text = it[position]
                }.attach()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _adapter = null
    }
}