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
            }
            return INSTANCE!!
        }

        private fun prePopulate() {
//            INSTANCE?.lakeDao()?.insertLake(
//                Lake(
//                    1,
//                    "Jezioro Miłoszewskie",
//                    "Pomorskie",
//                    "52.014596,17.994455"
//                ),
//            )
            INSTANCE?.lakeDao()?.insertLake(
                Lake(
                    2,
                    "Bobolice",
                    "Pomorskie",
                    "51.014596,19.994455"
                )
            )
            INSTANCE?.lakeDao()?.insertLake(
                Lake(
                    3,
                    "Krążno",
                    "Pomorskie",
                    "52.014596,17.994455"
                )
            )
            INSTANCE?.lakeDao()?.insertLake(
                Lake(
                    4,
                    "Tuszynek",
                    "Pomorskie",
                    "52.014596,17.994455"
                )
            )

        }
    }
}