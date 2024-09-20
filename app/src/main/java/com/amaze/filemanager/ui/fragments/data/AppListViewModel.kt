package com.amaze.filemanager.ui.fragments.data

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amaze.filemanager.adapters.data.AppDataParcelable

class AppListViewModel : ViewModel() {
    var recyclerViewState: Parcelable? = null
    val isAscending = false
    val sortby = 0
    val showSystemApps = true

    val appDataParcelable: MutableLiveData<ArrayList<AppDataParcelable>> by lazy {
        MutableLiveData()
    }
}
