package kr.ac.kumoh.s20180088.com.example.w1201recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel: ViewModel() {
    private val songs = ArrayList<String>()
    private val _list = MutableLiveData<ArrayList<String>>() // (변할수 있음)
    val list: LiveData<ArrayList<String>>
        get() = _list

    init {
        _list.value = songs
    }

    fun  add(song: String) {
        songs.add(song)
        _list.value = songs
    }

    // LiveData는 데이터를 저장하고 변화를 관찰할 수 있는 객체(변할수 없음)
    //fun  getList(): LiveData<ArrayList<String>> = _list

    //fun getSong(i: Int) = songs[i]
    //fun getSize() = songs.size
}