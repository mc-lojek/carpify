package pl.mclojek.carpify.domain.model

data class FishSpecies (
    val id: String,
    val name: String,
    val priority: Long,
    val isActive: Boolean = true
)

val AllSpecies = listOf(
    FishSpecies("common", "Common Carp", 10010),
    FishSpecies("mirror", "Mirror Carp", 10020),
    FishSpecies("grass", "Grass Carp", 10030),
    FishSpecies("sturgeon", "Sturgeon", 10040),
    FishSpecies("catfish", "Catfish", 10050),
    FishSpecies("other", "Other", 10060),
)