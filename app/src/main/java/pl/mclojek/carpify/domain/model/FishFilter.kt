package pl.mclojek.carpify.domain.model

class FishFilter(
        var speciesList: List<String> = listOf("Pełnołuski", "Królewski", "Amur", "Jesiotr", "Inne"),
        var timeFrom: Long = 0,
        var timeTo: Long = Long.MAX_VALUE,
        var weightFrom: Float = 0.0f,
        var weightTo: Float = Float.MAX_VALUE,
        var lengthFrom: Int = 0,
        var lengthTo: Int = Int.MAX_VALUE
) {

        override fun toString(): String {
                return "FishFilter(speciesList=$speciesList, timeFrom=$timeFrom, timeTo=$timeTo, weightFrom=$weightFrom, weightTo=$weightTo, lengthFrom=$lengthFrom, lengthTo=$lengthTo)"
        }
}