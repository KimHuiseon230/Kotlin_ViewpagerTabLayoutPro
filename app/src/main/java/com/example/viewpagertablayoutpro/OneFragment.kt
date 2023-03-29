package com.example.viewpagertablayoutpro

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.RawContacts.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewpagertablayoutpro.databinding.FragmentOneBinding

class OneFragment : Fragment() {

    //1
    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentOneBinding
    lateinit var dataList: MutableList<DataList>
    override fun onAttach(context: Context) {
        super.onAttach(context)
        //2
        mainActivity = context as MainActivity // 형변환
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater)
        dataList = mutableListOf()
        for (i in 1..30) {
            if (i % 2 == 0) {
                dataList.add(DataList("홍길동${i}", "${20 + i}세","asdf${i}@naver.com",R.drawable.man))
            } else {
                dataList.add(DataList("홍길동${i}", "${20 + i}세","asdf${i}@naver.com",R.drawable.woman))
            }
        }

        //어댑터 만들어서 제공하기
        val CustomRecycleAdapter = CustomRecycleAdapter(dataList)
        binding.recyclerView.adapter = CustomRecycleAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerView.addItemDecoration(MyDecoration(activity as Context))
        return binding.root
    }
}