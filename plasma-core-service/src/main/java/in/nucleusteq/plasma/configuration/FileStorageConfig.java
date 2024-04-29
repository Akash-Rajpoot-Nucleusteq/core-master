package in.nucleusteq.plasma.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for file storage settings.
 * It defines a bean to specify the file storage location.
 */
@Configuration
public class FileStorageConfig {
    /**
     * uploadDir.
     */
    @Value("${file.upload-dir}")
    private String uploadDir;
    /**
     * Bean method to specify the file storage location.
     *
     * @return Path representing the absolute path to the file storage directory.
     */
    @Bean
    Path fileStorageLocation() {
        return Paths.get(uploadDir).toAbsolutePath().normalize();
    }
}
