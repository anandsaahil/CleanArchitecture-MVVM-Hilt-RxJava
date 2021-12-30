package com.domain.interactor

import com.domain.repository.UserRepository
import com.domain.executor.PostExecutionThread
import com.domain.executor.ThreadExecutor
import com.domain.model.UserDomain
import io.reactivex.Observable
import javax.inject.Inject

class UserDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<UserDomain>, Unit>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Unit): Observable<List<UserDomain>> {
        return userRepository.getUserDetails()
    }
}