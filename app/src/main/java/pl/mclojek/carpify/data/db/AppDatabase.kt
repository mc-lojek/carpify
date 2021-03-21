package pl.mclojek.carpify.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.data.dao.FishDao
import pl.mclojek.carpify.data.dao.LakeDao

@Database(entities = [Fish::class, Lake::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fishDao(): FishDao
    abstract fun lakeDao(): LakeDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private var CONTEXT: Context? = null

        fun setContext(context: Context) {
            CONTEXT = context
        }

        fun getInstance(): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(CONTEXT!!, AppDatabase::class.java, "app_db")
                        .build()
                prePopulate()
                prePopulateFish()
            }
            return INSTANCE!!
        }

        private fun prePopulate() {
            INSTANCE?.lakeDao()?.insertLake(
                Lake(
                    1,
                    "Jezioro Miłoszewskie",
                    "Pomorskie",
                    "54.122734, 17.420446, 54.132872, 17.434327"
                ),
            )
            INSTANCE?.lakeDao()?.insertLake(
                Lake(
                    2,
                    "Bobolice",
                    "Pomorskie",
                    "53.975909, 16.543280, 53.979544, 16.547207"
                )
            )
            INSTANCE?.lakeDao()?.insertLake(
                Lake(
                    3,
                    "Krążno",
                    "Pomorskie",
                    "54.096755, 17.606413, 54.101348, 17.613300"
                )
            )
            INSTANCE?.lakeDao()?.insertLake(
                Lake(
                    4,
                    "Tuszynek",
                    "Pomorskie",
                    "53.739333, 18.492747, 53.744978, 18.500091"
                )
            )

        }

        private fun prePopulateFish() {
            INSTANCE?.fishDao()?.insertFish(
                Fish(
                    1,
                    "Pełnołuski",
                    12.86f,
                    97,
                    1616107968000,
                    "Kulka proteinowa",
                    "karpik złowiony na 12 metrach głębokości na chod riga",
                    "53.739920, 18.494587",
                    4,
                    1,
                    "costam"
                ),
            )
            INSTANCE?.fishDao()?.insertFish(
                Fish(
                    2,
                    "Jesiotr",
                    15.50f,
                    123,
                    1616107968000,
                    "Kulka proteinowa",
                    "karpik złowiony na 12 metrach głębokości na chod riga",
                    "53.744402, 18.495760",
                    4,
                    1,
                    "costam"
                ),
            )
            INSTANCE?.fishDao()?.insertFish(
                Fish(
                    3,
                    "Amur",
                    23.00f,
                    97,
                        1616290247997,
                    "Kukurydza truskawkowa",
                    "karpik złowiony na 12 metrach głębokości na chod riga",
                    "53.742567, 18.499369",
                    4,
                    1,
                    "costam"
                ),
            )
            INSTANCE?.fishDao()?.insertFish(
                Fish(
                    4,
                    "Królewski",
                    3.45f,
                    35,
                        1616290247997,
                    "Pellet",
                    "karpik złowiony na 12 metrach głębokości na chod riga",
                    "53.742214, 18.496348",
                    4,
                    1,
                    "costam"
                ),
            )
        }
    }
}