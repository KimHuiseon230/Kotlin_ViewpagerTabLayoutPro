package com.example.viewpagertablayoutpro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustomAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    val fragmentList = ArrayList<Fragment>()

    override fun getItemCount() = fragmentList.size
    // crateViewHoler의 다른 모습.
    override fun createFragment(position: Int): Fragment = fragmentList.get(position)

    //2.
    fun addListFragment(fragment: Fragment){
        this.fragmentList.add(fragment)// 여기서 값을 넣어줌
    }

}