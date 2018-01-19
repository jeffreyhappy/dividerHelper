package com.fei.divider

import android.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fei.divider.divider.DividerHelper
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * 创建日期：2018/1/19 on 14:31
 * 描述:
 * 作者:Li
 */
class ListFragment2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.layoutManager = LinearLayoutManager(activity)
        rv.adapter = TextAdapter()

        val divider: RecyclerView.ItemDecoration =
                DividerHelper.Builder()
                        .divider(ActivityCompat.getDrawable(activity, R.drawable.shape_divider_brand_left_padding))
                        .marginLeft(100)
                        .marginRight(100)
                        .build()
        rv.addItemDecoration(divider)
    }
}