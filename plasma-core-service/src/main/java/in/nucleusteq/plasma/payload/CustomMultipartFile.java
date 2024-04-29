package in.nucleusteq.plasma.payload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;
/**
 * A custom implementation of the Spring Framework's MultipartFile interface,
 * allowing the creation of a MultipartFile from byte content and a filename.
 */
public class CustomMultipartFile implements MultipartFile {
    /**
     * imgContent.
     */
    private final byte[] imgContent;
    /**
     * imgName.
     */
    private final String imgName;
    /**
     * Constructs a CustomMultipartFile with the given byte content and filename.
     * @param imgContent The byte content of the file
     * @param imgName    The name of the file
     */
    public CustomMultipartFile(byte[] imgContent, String imgName) {
        this.imgContent = imgContent;
        this.imgName = imgName;
    }
    /**
     * getName.
     */
    @Override
    public String getName() {
        return null;
    }
    /**
     * getOriginalFilename.
     * @return imgName
     */
    @Override
    public String getOriginalFilename() {
        return imgName;
    }
    /**
     * getContentType.
     */
    @Override
    public String getContentType() {
        return null;
    }
    /**
     * isEmpty.
     */
    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }
    /**
     * getSize.
     * @return imgContent.length
     */
    @Override
    public long getSize() {
        return imgContent.length;
    }
    /**
     * getBytes.
     * @return imgContent
     */
    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }
    /**
     * getInputStream.
     * @return InputStream
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }
    /**
     * Transfers the content of this MultipartFile to the given destination file.
     * @param dest The destination file to transfer the content to
     * @throws IOException            if an I/O error occurs
     * @throws IllegalStateException if the file cannot be written
     */
    @SuppressWarnings("resource")
    @Override
    public void transferTo(File dest)
            throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imgContent);
    }
}
