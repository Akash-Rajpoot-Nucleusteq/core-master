package in.nucleusteq.plasma.utility;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for file storage.
 */
@Component
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    /**
     * uploadDir.
     */
    private String uploadDir;

    /**
     * Get the directory path where files will be uploaded.
     * @return The directory path for file uploads.
     */
    public String getUploadDir() {
        return uploadDir;
    }

    /**
     * Set the directory path where files will be uploaded.
     * @param uploadDir The directory path for file uploads.
     */
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
