package pl.mclojek.carpify.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import pl.mclojek.carpify.data.Fish
import pl.mclojek.carpify.data.Lake

@Database(entities = [Fish::class, Lake::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fishDao(): FishDao

    companion object {
        val DATABASE_NAME = "carpify-db"

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            //TODO:
                            //here we can add pre-populating db
                            //val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                            //WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}