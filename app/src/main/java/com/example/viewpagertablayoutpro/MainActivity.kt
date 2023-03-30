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
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(){
    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var customAdapter: CustomAdapter
    lateinit var tabTitleList: MutableList<String>
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //+++ RecyclerView (Adapter setting) Start
        customAdapter = CustomAdapter(this)
        customAdapter.addListFragment(OneFragment())
        customAdapter.addListFragment(TwoFragment())    //val tab1: TabLayout.Tab = binding.tabLayout.newTab()
        customAdapter.addListFragment(ThreeFragment())
        tabTitleList = mutableListOf("car", "house", "air")
        binding.viewPager2.adapter = customAdapter
        //+++ RecyclerView (Adapter setting) End

        //+++ tabLayout 와 viewPager2를 연결  : binding.tabLayout.addTab(tab1)
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tap, position ->
            // tab1.text ="FRAG1"
            tap.text = tabTitleList.get(position)
            tap.setCustomView(tabCustomView(position))
        }.attach()
        //+++ tabLayout 와 viewPager2 연결 End

        //1.액션 바 대신에 툴바로 대체.
        setSupportActionBar(binding.toolbar)
        //2.(*중요*) ActionBarDrawerToggle 버튼 적용 : ( < - > 오른쪽과 왼쪽으로 화면을 움직임) // 반드시 String 사용
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout,R.string.drawer_open,R.string.drawer_close)
        //3.Up button([<-] 모양) 활성화 : BackPress 기능을 담당. 이전 화면으로 돌아가는 기능 액션바에 만들어주는 기능.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //4.Up button --> toggle button sync
        toggle.syncState()

        // +++ NavigationView 메뉴 항목을 클릭했을 때 이벤트 처리 Start
        binding.navigationView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.item_user -> Toast.makeText(applicationContext,"User Data clicked",Toast.LENGTH_SHORT).show()
                    R.id.item_info -> Toast.makeText(applicationContext,"App Data clicked",Toast.LENGTH_SHORT).show()
                    R.id.item_age -> Toast.makeText(applicationContext,"User Age clicked",Toast.LENGTH_SHORT).show()
                    R.id.item_email-> Toast.makeText(applicationContext,"User Email clicked",Toast.LENGTH_SHORT).show()
                }
                return false
            }
        }) // +++ NavigationView 메뉴 항목을 클릭했을 때 이벤트 처리 End
    } //onCreate of end

    fun tabCustomView(position: Int): View {
        val binding = UsertabButtonBinding.inflate(layoutInflater) // 뷰 바인딩 진행 . 객체가 되었음.
        when (position) {
            0 -> binding.ivICon.setImageResource(R.drawable.car)
            1 -> binding.ivICon.setImageResource(R.drawable.house)
            2 -> binding.ivICon.setImageResource(R.drawable.airport)
        }
        binding.tvTabName.text = tabTitleList.get(position)
        return binding.root
    }
    // 메뉴 토글 버튼을 눌렀을 때 네비게이션 바 활성화 < -- >
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //toggle button push check
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}//class MainActivity : AppCompatActivity() End

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
