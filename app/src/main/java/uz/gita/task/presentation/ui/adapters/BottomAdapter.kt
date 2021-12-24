package uz.gita.task.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.task.presentation.ui.pages.HomePage
import uz.gita.task.presentation.ui.pages.SearchPage

class BottomAdapter(
    fm:FragmentManager,
    lifecycle: Lifecycle
) :FragmentStateAdapter(fm,lifecycle){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
       return when(position){
            0 -> HomePage()
           else -> SearchPage()
        }
    }
}