package com.example.viewpagertablayoutpro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewpagertablayoutpro.databinding.FragmentOneBinding
import com.example.viewpagertablayoutpro.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    //1
    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentTwoBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //2
        mainActivity = context as MainActivity // 형변환
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater)
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

}