package com.neppplus.listview_20211215.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.neppplus.listview_20211215.datas.StudentData

class StudentAdapter(
    val mContext: Context,
    val resId: Int,
    val mList: ArrayList<StudentData> ) : ArrayAdapter<StudentData>() {
}