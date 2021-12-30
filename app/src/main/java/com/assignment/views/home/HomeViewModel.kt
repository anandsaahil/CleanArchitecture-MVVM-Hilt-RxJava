package com.assignment.views.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domain.interactor.UserDetailsUseCase
import com.domain.model.UserDomain
import com.assignment.utils.Resource
import com.assignment.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userDetailsUseCase: UserDetailsUseCase) : ViewModel() {

    private var navigator: HomeNavigator? = null
    var userDetailsLiveData: MutableLiveData<Resource<List<UserDomain>>> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        userDetailsUseCase.dispose()
    }

    fun getUsers() {
        userDetailsLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        userDetailsUseCase.execute(object : DisposableObserver<List<UserDomain>>() {
            override fun onComplete() {
                Log.d("api_response", "getUsers onComplete called")
            }

            override fun onNext(data: List<UserDomain>) {
                userDetailsLiveData.postValue(Resource(ResourceState.SUCCESS, data, null))
                Log.d("api_response", "getUsers onNext called")
            }

            override fun onError(e: Throwable) {
                Log.d("api_response", "getUsers onError called")
                userDetailsLiveData.postValue(Resource(ResourceState.ERROR, null, e.message))
                e.message?.let { navigator?.showErrorMessage(it) }
            }
        }, Unit)
    }

    fun setNavigator(homeNavigator: HomeNavigator) {
        navigator = homeNavigator
    }
}