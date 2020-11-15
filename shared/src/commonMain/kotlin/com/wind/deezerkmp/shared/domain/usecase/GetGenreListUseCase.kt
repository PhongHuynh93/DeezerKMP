package com.wind.deezerkmp.shared.domain.usecase

import com.wind.deezerkmp.shared.data.Repository
import com.wind.deezerkmp.shared.domain.UseCase
import com.wind.deezerkmp.shared.domain.mapper.impl.GenreDataMapper
import com.wind.deezerkmp.shared.domain.model.Genre
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Phong Huynh on 11/9/2020
 */
object GetGenreListParam
class GetGenreListUseCase constructor(
    dispatcher: CoroutineDispatcher,
    private val repository: Repository
) : UseCase<GetGenreListParam, List<Genre>>(dispatcher) {
    override suspend fun execute(parameters: GetGenreListParam): List<Genre> {
        return repository.getGenreList().data
            .map {
                GenreDataMapper().map(it)
            }.filter {
                it.isValid()
            }
    }
}