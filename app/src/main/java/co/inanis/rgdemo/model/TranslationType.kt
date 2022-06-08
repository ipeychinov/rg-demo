package co.inanis.rgdemo.model

import com.google.gson.annotations.SerializedName

enum class TranslationType {
    @SerializedName("sindarin")
    SINDARIN,

    @SerializedName("quenya")
    QUENYA;
}