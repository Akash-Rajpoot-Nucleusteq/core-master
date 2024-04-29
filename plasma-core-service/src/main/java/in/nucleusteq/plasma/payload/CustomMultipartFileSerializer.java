package in.nucleusteq.plasma.payload;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
/**
 * Custom JSON serializer for serializing CustomMultipartFile objects.
 */
public class CustomMultipartFileSerializer
        extends
            JsonSerializer<CustomMultipartFile> {

    /**
     * Serialize a CustomMultipartFile object into JSON format.
     *
     * @param value       The CustomMultipartFile object to serialize.
     * @param gen         The JsonGenerator used for writing JSON content.
     * @param serializers The SerializerProvider used for serialization.
     * @throws IOException If an I/O error occurs during serialization.
     */
    @Override
    public void serialize(CustomMultipartFile value, JsonGenerator gen,
            SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("fileName", value.getOriginalFilename());
        gen.writeFieldName("fileContent");
        gen.writeBinary(value.getBytes());
        gen.writeEndObject();
    }
}
