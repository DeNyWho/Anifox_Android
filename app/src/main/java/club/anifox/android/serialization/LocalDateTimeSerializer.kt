package club.anifox.android.serialization

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object LocalDateTimeSerializer : KSerializer<LocalDateTime?> {
    private val dtf: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneOffset.UTC)

    override fun serialize(encoder: Encoder, value: LocalDateTime?) {
        encoder.encodeString(value?.atZone(ZoneOffset.UTC)?.format(dtf) ?: "")
    }

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDateTime? {
        return try {
            LocalDateTime.parse(decoder.decodeString(), dtf)
        } catch (e: DateTimeParseException) {
            null
        }
    }
}
