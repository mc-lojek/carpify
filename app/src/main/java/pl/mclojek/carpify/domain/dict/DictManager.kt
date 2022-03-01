package pl.mclojek.carpify.domain.dict

import android.content.Context
import pl.mclojek.carpify.data.model.FishSpeciesDataModel
import pl.mclojek.carpify.data.model.VoivodeWrapper
import pl.mclojek.carpify.data.model.VoivodeshipDataModel
import pl.mclojek.carpify.data.util.LocalJsonParser
import timber.log.Timber

object DictManager {

    private val parser = LocalJsonParser()

    val species = HashMap<String, FishSpeciesDataModel>()
    val voivodeships = HashMap<String, VoivodeshipDataModel>()

    var isInitialized = false

    suspend fun initialize(context: Context, callback: (Int, Int) -> Unit) {
        parser.setContext(context)

        callback(0,2)
        loadSpecies()
        callback(1, 2)
        loadVoivodeships()
        callback(2,2)
        isInitialized = true
    }

    private suspend fun loadSpecies() {
        val list = parser.parseSpecies()
        Timber.d(list.size.toString())
        list.forEach {
            species.put(it.name, it)
        }
    }

    private suspend fun loadVoivodeships() {
        val list = parser.parseVoivodeships()
        list.forEach {
            voivodeships.put(it.name, it)
        }
    }
}