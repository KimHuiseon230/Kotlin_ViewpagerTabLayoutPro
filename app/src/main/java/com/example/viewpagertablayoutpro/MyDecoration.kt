package com.example.viewpagertablayoutpro

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

//
class MyDecoration(val context: Context) : RecyclerView.ItemDecoration() {
    //리사이클의 뷰가 만들어지고 나서 리사이클 뷰 위에 화면을 배치

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        //1. 리사이클러 뷰의 사이즈를 가져온다
        val recycleWith = parent.width
        val recycleHight = parent.height
        //2.리사이클러뷰에 배치할 화면 사이즈를 가져온다.
        val imageData = ResourcesCompat.getDrawable(context.resources, R.drawable.kbo, null)
        val imageWidth = imageData?.intrinsicWidth
        val imageHight = imageData?.intrinsicHeight

        //3. 화면에 배치할 리사이클 뷰의 중심 좌표를 구한다
        val locateX = (recycleWith / 2) - (imageWidth?.div(2) as Int)
        val locateY = (recycleHight / 2) - (imageHight?.div(2) as Int)

        //4 캠퍼스 중앙위치로 이미지를 그린다.
  /*      c.drawBitmap(
            BitmapFactory.decodeResource(context.resources, R.drawable.kbo),
            locateX.toFloat(),
            locateY.toFloat(),
            null
        )*/
    }

    // 아이템 뷰를 꾸미는 역할
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        //1.꾸며야 할 아이템 뷰의 위치를 가져옴
        val index = parent.getChildAdapterPosition(view)

        //3~4 개 묶어서 작업한다.
        outRect.set(10, 10, 10, 50)
        if (index % 1 == 0) {
            outRect.set(10, 10, 10, 50)
        } else {
            outRect.set(10, 10, 10, 0)
        }

        //뷰의 배경색을 정한다.
/*        view.setBackgroundColor(Color.parseColor("#F8E6E0"))*/
        ViewCompat.setElevation(view, 10.0f)
    }

}