package com.neppplus.listview_20211215

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.neppplus.listview_20211215.adapters.StudentAdapter
import com.neppplus.listview_20211215.datas.StudentData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mStudentList = ArrayList<StudentData>()
    lateinit var mStudentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStudentList.add(StudentData("조경진", 1988, "서울시 동대문구"))
        mStudentList.add(StudentData("김민성 ", 1998, "서울시 도봉구"))
        mStudentList.add(StudentData("김준기 ", 1996, "경기도 남양주시"))
        mStudentList.add(StudentData("방우진 ", 1983, "경기도 고양시"))
        mStudentList.add(StudentData("이아현 ", 1996, "서울시 동대문구"))
        mStudentList.add(StudentData("이지원 ", 1993, "서울시 관악구"))
        mStudentList.add(StudentData("차수나 ", 1977, "서울시 성북구"))
        mStudentList.add(StudentData("김경식 ", 1992, "서울시 중랑구"))

        mStudentAdapter = StudentAdapter(this, R.layout.student_list_item, mStudentList)
        studentListView.adapter = mStudentAdapter

//        리스트뷰이 각 줄이 눌린 이벤트 철
        studentListView.setOnItemClickListener { adapterView, view, position, l ->

//             position : 몇번 줄이 눌렸는가? 알려주는 역할
            Log.d("리스트뷰클릭됨", "${position}번 줄 클릭됨")

//             눌린 위치에 맞는 학생 데이터 변수에 저장
            val clickedStudent = mStudentList[position]

//             ex.눌린 학생의 이름을 토스트로 출력
//             Toast.makeText(this, "${clickedStudent.name}학생 클릭됨", Toast.LENGTH_SHORT).show()

//             상세 보기 화면으로 이동
            val myIntent = Intent(this, ViewStudentDetailActivity::class.java)
            myIntent.putExtra("name", clickedStudent.name)
            myIntent.putExtra("birthYear", clickedStudent.birthYear)
            myIntent.putExtra("address", clickedStudent.address)
            startActivity(myIntent)

        }

        studentListView.setOnItemLongClickListener { adapterView, view, position, l ->

            val longClickedStudent = mStudentList[position]

//            Toast.makeText(this, "${longClickedStudent.name}학생이 길게 눌림", Toast.LENGTH_SHORT).show()

//            정말 그 학생을 지울건지? 확인을 눌러야만 지우자.
            val alert = AlertDialog.Builder(this)
            alert.setTitle("학생 삭제 확인")
            alert.setMessage("정말 ${longClickedStudent.name}을 제거 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->

//                확인이 눌릴때만 실행되는 코드

                //            길게 눌린 학생을 목록에서 제거 -> 리스트뷰에서도 빠지게 해보자 (삭제 기능)
                mStudentList.remove(longClickedStudent)

//            리스트뷰 -> 어댑터에게 새로고침 시키자. (인지시키자)
                mStudentAdapter.notifyDataSetChanged()

            })
            alert.setNegativeButton("취소", null)
            alert.show()

//            길게 눌린 학생을 목록에서 제거 -> 리스트뷰에서도 빠지게 해보자 (삭제 기능)
            mStudentList.remove(longClickedStudent)

//            리스트뷰 -> 어댑터에게 새로고침 시키자. (인지시키자)
            mStudentAdapter.notifyDataSetChanged()

//            Boolean(true / false)을 결과로 지정해 줘야 함.
            return@setOnItemLongClickListener true

        }


    }
}