package uz.gita.task.presentation.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.task.presentation.ui.pages.MainPage

class PagerAdapter(
    val list: List<String>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putString("key", list[position])
        val mainPage = MainPage()
        mainPage.arguments = bundle
        return mainPage
    }

}