package club.anifox.android.data.datastore.serializer

import androidx.datastore.core.Serializer
import club.anifox.android.domain.model.user.UserAccount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.InputStream
import java.io.OutputStream

class LocalStorageSerializer : Serializer<UserAccount> {

    /**
     * Value to return if there is no data on disk.
     */
    override val defaultValue: UserAccount
        get() = UserAccount()

    /**
     * Unmarshal object from stream.
     *
     * @param input the InputStream with the data to deserialize
     */
    override suspend fun readFrom(input: InputStream): UserAccount {
        return ProtoBuf.decodeFromByteArray(input.readBytes())
    }

    /**
     * Marshal object to a stream. Closing the provided OutputStream is a no-op.
     *
     * @param t the data to write to output
     * @output the OutputStream to serialize data to
     */
    override suspend fun writeTo(t: UserAccount, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(ProtoBuf.encodeToByteArray(t))
        }
    }
}
