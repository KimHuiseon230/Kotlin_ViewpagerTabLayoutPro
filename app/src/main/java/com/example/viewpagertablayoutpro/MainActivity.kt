package com.example.viewpagertablayoutpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.viewpagertablayoutpro.databinding.ActivityMainBinding
import com.example.viewpagertablayoutpro.databinding.UsertabButtonBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var customAdapter: CustomAdapter
    lateinit var tabTitleLsit: MutableList<String>
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        customAdapter = CustomAdapter(this)
        customAdapter.addListFragment(OneFragment()) //  객체를 만듬
        customAdapter.addListFragment(TwoFragment()) //  객체를 만듬 val tab1: TabLayout.Tab = binding.tabLayout.newTab()
        customAdapter.addListFragment(ThreeFragment()) //  객체를 만듬
        tabTitleLsit = mutableListOf("car", "house", "air")
        binding.viewPager2.adapter = customAdapter

        binding.navigationView.setNavigationItemSelectedListener(this)

        //tabLayout 와 viewPager2를 연결
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tap, position ->
            // tab1.text ="FRAG1"
            tap.text = tabTitleLsit.get(position)
            tap.setCustomView(tabCustomView(position))
        }.attach() // binding.tabLayout.addTab(tab1)

        //1.액션 바 대신에 툴바로 대체.
        setSupportActionBar(binding.toolbar)
        //2.ActionBarDrawerToggle 버튼 적용
        toggle =ActionBarDrawerToggle(this, binding.drawerLayout,R.string.drawer_open,R.string.drawer_close)
        //3.업 버튼 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //4. 토글 sync
        toggle.syncState()
    }

    fun tabCustomView(position: Int): View {
        val binding = UsertabButtonBinding.inflate(layoutInflater) // 뷰 바인딩 진행 . 객체가 되었음.
        when (position) {
            0 -> binding.ivICon.setImageResource(R.drawable.car)
            1 -> binding.ivICon.setImageResource(R.drawable.house)
            2 -> binding.ivICon.setImageResource(R.drawable.airport)
        }
        binding.tvTabName.text = tabTitleLsit.get(position)
        return binding.root
    }
    // 메튜 토글 버튼을 눌렀을 때 네비게이션 바 활성화 < -- >
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_user -> Toast.makeText(this,"User Data clicked",Toast.LENGTH_SHORT).show()
            R.id.item_info -> Toast.makeText(this,"App Data clicked",Toast.LENGTH_SHORT).show()
            R.id.item_age-> Toast.makeText(this,"User Age clicked",Toast.LENGTH_SHORT).show()
            R.id.item_email-> Toast.makeText(this,"User Email clicked",Toast.LENGTH_SHORT).show()
        }
        return false
    }
}
/*  binding.viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL

        val tab1: TabLayout.Tab = binding.tabLayout.newTab()
        val tab2: TabLayout.Tab = binding.tabLayout.newTab()
        val tab3: TabLayout.Tab = binding.tabLayout.newTab()

        tab1.text = "FRAG1"
        tab2.text = "FRAG2"
        tab3.text = "FRAG3"

        binding.tabLayout.addTab(tab1)
        binding.tabLayout.addTab(tab2)
        binding.tabLayout.addTab(tab3)
       */
