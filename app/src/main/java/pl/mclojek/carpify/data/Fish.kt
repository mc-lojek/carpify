package pl.mclojek.carpify.data

data class Fish (
    val id: Int,
    val species: String,
    val size: Int,
    val datetime: Long,
    val bait: String,
    val desc: String,
    val coords: String,
    val lakeId: String,
    val hunterId: String,
    val img: String
)