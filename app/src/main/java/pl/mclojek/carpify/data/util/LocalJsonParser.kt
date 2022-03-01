package pl.mclojek.carpify.data.util

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.delay
import pl.mclojek.carpify.R
import pl.mclojek.carpify.data.model.FishSpeciesDataModel
import pl.mclojek.carpify.data.model.FishSpeciesWrapper
import pl.mclojek.carpify.data.model.VoivodeWrapper
import pl.mclojek.carpify.data.model.VoivodeshipDataModel
import timber.log.Timber

class LocalJsonParser {

    private lateinit var context: Context
    val gson: Gson = Gson()

    fun setContext(context: Context) {
        this.context = context
    }

    suspend fun parseSpecies(): List<FishSpeciesDataModel> {
        delay(5000)
        val json = loadJsonFromResources(R.raw.fish_species)
        return gson.fromJson(json, FishSpeciesWrapper::class.java).species
    }

    suspend fun parseVoivodeships(): List<VoivodeshipDataModel> {
        delay(5000)
        val json = loadJsonFromResources(R.raw.voivodeships)
        return gson.fromJson(json, VoivodeWrapper::class.java).voivodeships
    }

    suspend fun loadJsonFromResources(id: Int) =
        context.resources.openRawResource(id).bufferedReader().use { it.readText() }

}