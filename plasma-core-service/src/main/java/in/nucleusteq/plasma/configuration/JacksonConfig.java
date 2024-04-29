package in.nucleusteq.plasma.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import in.nucleusteq.plasma.payload.CustomMultipartFile;
import in.nucleusteq.plasma.payload.CustomMultipartFileSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Jackson ObjectMapper settings.
 * It configures custom serializers for specific types.
 */
@Configuration
public class JacksonConfig {
    /**
     * Bean method to provide a configured instance of ObjectMapper.
     * It registers a custom serializer for CustomMultipartFile.
     * @return ObjectMapper configured with custom serializers.
     */
    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(CustomMultipartFile.class,
                new CustomMultipartFileSerializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
