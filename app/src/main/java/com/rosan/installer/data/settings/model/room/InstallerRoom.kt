package com.rosan.installer.data.settings.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rosan.installer.data.settings.model.room.dao.AppDao
import com.rosan.installer.data.settings.model.room.dao.ConfigDao
import com.rosan.installer.data.settings.model.room.entity.AppEntity
import com.rosan.installer.data.settings.model.room.entity.ConfigEntity
import com.rosan.installer.data.settings.model.room.entity.converter.AuthorizerConverter
import com.rosan.installer.data.settings.model.room.entity.converter.InstallModeConverter
import org.koin.core.component.KoinComponent

@Database(entities = [ConfigEntity::class, AppEntity::class], version = 4, exportSchema = true)
@TypeConverters(AuthorizerConverter::class, InstallModeConverter::class)
abstract class InstallerRoom : RoomDatabase() {
        abstract fun configDao(): ConfigDao
        abstract fun appDao(): AppDao

        companion object : KoinComponent {
                private const val DATABASE_NAME = "installer.db"

                private val MIGRATION_3_4 =
                        object : Migration(3, 4) {
                                override fun migrate(database: SupportSQLiteDatabase) {
                                        database.execSQL(
                                                "ALTER TABLE config ADD COLUMN use_authorizer_launcher INTEGER NOT NULL DEFAULT 0"
                                        )
                                }
                        }

                fun build(context: Context): InstallerRoom {
                        return Room.databaseBuilder(
                                context,
                                InstallerRoom::class.java,
                                DATABASE_NAME
                        )
                                .addMigrations(MIGRATION_3_4)
                                .fallbackToDestructiveMigration(false)
                                .build()
                }
        }
}
