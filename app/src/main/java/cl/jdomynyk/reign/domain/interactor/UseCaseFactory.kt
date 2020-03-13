package cl.jdomynyk.reign.domain.interactor

import cl.jdomynyk.reign.domain.Hits
import cl.jdomynyk.reign.domain.HitsRepo
import javax.inject.Inject

class UseCaseFactory @Inject constructor(private val repo: HitsRepo) {

    fun getHit(): UseCase<Hits> {
        return GetHit(repo)
    }

    fun getHits(): UseCase<List<Hits>> {
        return GetHits(repo)
    }

}