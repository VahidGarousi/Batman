package ir.vbile.app.batman.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.vbile.app.batman.core.data.dto.local.BatmanDB
import ir.vbile.app.batman.core.util.RatingTypeConverter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideBatmanDB(
        @ApplicationContext context: Context
    ): BatmanDB {
        return BatmanDB.getDatabase(context)
    }
}