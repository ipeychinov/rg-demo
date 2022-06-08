package co.inanis.rgdemo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "translations")
data class Translation(
    @PrimaryKey(autoGenerate = true) private val id: Int = 0,
    @SerializedName("translation") val type: TranslationType,
    val text: String,
    val translated: String,
    val timestamp: Long = System.currentTimeMillis(),
) : Parcelable