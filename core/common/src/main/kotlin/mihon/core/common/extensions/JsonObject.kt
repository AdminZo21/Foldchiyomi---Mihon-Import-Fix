package mihon.core.common.extensions

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

val JsonObjectEmpty = JsonObject(emptyMap())

val JsonObjectEmptyBytes = byteArrayOf(0x7B, 0x7D)

val JsonObject.Companion.EMPTY: JsonObject
    inline get() = JsonObjectEmpty

fun JsonObject.toByteArray(): ByteArray = toString().encodeToByteArray()

// Backups from forks such as Yokai can carry a zero-length memo, which is not valid JSON.
fun ByteArray.toJsonObject(): JsonObject {
    val text = decodeToString()
    return if (text.isBlank()) JsonObjectEmpty else Json.decodeFromString<JsonObject>(text)
}
