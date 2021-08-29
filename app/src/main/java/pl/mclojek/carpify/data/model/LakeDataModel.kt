package pl.mclojek.carpify.data.model

import pl.mclojek.carpify.domain.model.Lake

class LakeDataModel(
    val id: Int,
    val name: String,
    val bounds: String,
    val voivodeship: String
) {

    fun toDomainModel() : Lake {
        return Lake(
            id=id,
            name=name,
            bounds=bounds,
            voivodeship=voivodeship
        )
    }
}