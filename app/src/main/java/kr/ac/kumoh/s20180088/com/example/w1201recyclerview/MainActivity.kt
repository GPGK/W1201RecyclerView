package kr.ac.kumoh.s20180088.com.example.w1201recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
//import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kumoh.s20180088.com.example.w1201recyclerview.databinding.ActivityMainBinding

// getItemCount(): 아이템 갯수
// onCreateViewHolder(): infaltion할때 (찾아서 넣어주는 역할)
// onBindViewHolder(): 나타날려고하는 애를 세팅해주는 역할

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: ListViewModel
    private val songAdapter = SongAdapter()     // 22라인과 32라인

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        model = ViewModelProvider(this)[ListViewModel::class.java]  // model을 얻어올수 있음
        

        binding.list.apply {    // apply 앞의 공통에서 아래의 것을 처리하겠다.
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = songAdapter
        }

        model.list.observe(this) {
            //songAdapter.notifyDataSetChanged()      // 바꼇어! -> 뭐가 바꼈는데? 아니면 다시 그리라고?
            songAdapter.notifyItemRangeChanged(0,
                model.list.value?.size ?: 0)
        }

        for (i in 1..3) {
            model.add("♡")
        }
        model.add("♣")

    }
    // 상속의 경우 : 양옆을 띄울것
    inner class SongAdapter : RecyclerView.Adapter<SongAdapter.ViewHolder>(){
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val txSong: TextView = itemView.findViewById(android.R.id.text1)    // 리스트에 들어있는 것
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.txSong.text = model.list.value?.get(position) ?: ""
        }

        override fun getItemCount() = model.list.value?.size ?: 0
    }
}