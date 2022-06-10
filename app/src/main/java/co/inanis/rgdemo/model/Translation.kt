package co.inanis.rgdemo.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "translations")
data class Translation(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("translation") val type: TranslationType,
    val text: String,
    val translated: String,
) : Parcelable

object TranslationComparator : DiffUtil.ItemCallback<Translation>() {
    override fun areItemsTheSame(oldItem: Translation, newItem: Translation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Translation, newItem: Translation): Boolean {
        return oldItem == newItem
    }
}