package pl.mclojek.carpify.data.repository

import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.data.retrofit.RestService
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.repository.FishRepository
import pl.mclojek.carpify.network.NetworkErrorHandler
import timber.log.Timber

class RemoteFishRepositoryImpl