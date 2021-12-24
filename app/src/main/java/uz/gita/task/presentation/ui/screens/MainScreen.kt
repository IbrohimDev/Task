package uz.gita.task.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import uz.gita.task.R
import uz.gita.task.databinding.ScreenMainBinding
import uz.gita.task.presentation.ui.adapters.BottomAdapter
import uz.gita.task.presentation.viewModel.impls.HomeViewModelImpl
import uz.gita.task.presentation.viewModel.impls.MainScreenViewModelImpl
import uz.gita.task.presentation.viewModel.interfaces.HomeViewModel
import uz.gita.task.presentation.viewModel.interfaces.MainScreenViewModel
import uz.gita.task.utils.scope


@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {

    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel:MainScreenViewModel by viewModels<MainScreenViewModelImpl>()
    private val bottomAdapter:BottomAdapter by lazy { BottomAdapter(childFragmentManager,lifecycle) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        loadViews()
        loadFlows()
    }

    private fun loadViews() = binding.scope{
        bottomNavigationView.background = resources.getDrawable(R.drawable.bottom_back)

         bottomNavigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
             when(it.itemId){
                 R.id.home_menu -> {
                   viewPager.setCurrentItem(0,true)
                 }
                 R.id.search_menu -> {
                     viewPager.setCurrentItem(1,true)
                 }
             }
             return@OnItemSelectedListener true
         })
        viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(position == 0){
                    bottomNavigationView.selectedItemId = R.id.home_menu
                }else{
                    bottomNavigationView.selectedItemId = R.id.search_menu
                }
            }
        })
    }

    private fun loadFlows() = binding.scope {
       lifecycleScope.launchWhenStarted {
           viewModel.bottomAdapterFlow.collect {
               viewPager.adapter = bottomAdapter
           }
       }
    }


}