package co.inanis.rgdemo.model

import com.google.gson.annotations.SerializedName
import java.util.*

enum class TranslationType {
    @SerializedName("sindarin")
    SINDARIN,

    @SerializedName("quenya")
    QUENYA;

    fun readableName() = name.lowercase().replaceFirstChar {
        it.titlecase(Locale.getDefault())
    }

}