package co.inanis.rgdemo.data.remote.serializares

import co.inanis.rgdemo.data.remote.ApiErrorResponse
import co.inanis.rgdemo.data.remote.ApiResponse
import co.inanis.rgdemo.extensions.fromJson
import co.inanis.rgdemo.factories.serializationModule
import co.inanis.rgdemo.model.Translation
import co.inanis.rgdemo.model.TranslationType
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class TranslationSerializerTest : KoinTest{

    private val serializer: Gson by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(serializationModule)
    }

    @Test
    fun `deserialize translation api response`(){
        val json = """
            {
              "success": {
                "total": 1
              },
              "contents": {
                "translated": "I ambar na- changed; im tur- feel ha in i nen,  im tur- feel ha in i coe,  im tur- smell ha in i gwilith.",
                "text": "The world is changed; I can feel it in the water, I can feel it in the earth, I can smell it in the air.",
                "translation": "sindarin"
              }
            }
        """.trimIndent()

        val apiResponse = serializer.fromJson<ApiResponse<Translation>>(json)

        assertThat(apiResponse.contents.id).isEqualTo(0)
        assertThat(apiResponse.contents.type).isEqualTo(TranslationType.SINDARIN)
        assertThat(apiResponse.contents.text).isEqualTo("The world is changed; I can feel it in the water, I can feel it in the earth, I can smell it in the air.")
        assertThat(apiResponse.contents.translated).isEqualTo("I ambar na- changed; im tur- feel ha in i nen,  im tur- feel ha in i coe,  im tur- smell ha in i gwilith.")
    }

    @Test
    fun `deserialize api error response`(){
        val json = """
            {
            	"error": {
            		"code": 401,
            		"message": "Unauthorized"
            	}
            }
        """.trimIndent()

        val errorResponse = serializer.fromJson<ApiErrorResponse>(json)

        assertThat(errorResponse.error.code).isEqualTo(401)
        assertThat(errorResponse.error.message).isEqualTo("Unauthorized")
    }

}