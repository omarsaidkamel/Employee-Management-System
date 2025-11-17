package com.mycompany.utils;


import org.primefaces.model.file.UploadedFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class StoredUploadedFile implements UploadedFile {

    private final File file;
    private final String contentType;

    public StoredUploadedFile(File file) {
        this.file = Objects.requireNonNull(file, "file must not be null");
        String ct;
        try {
            ct = Files.probeContentType(file.toPath());
        } catch (IOException e) {
            ct = null;
        }
        this.contentType = (ct == null) ? "application/octet-stream" : ct;
    }

    @Override
    public String getFileName() {
        return file.getName();
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public long getSize() {
        return file.length();
    }

    /**
     * Return a fresh InputStream for the file. IOExceptions are wrapped in UncheckedIOException.
     */
    @Override
    public InputStream getInputStream() {
        try {
            return new FileInputStream(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Return the whole file content as a byte[]. If the file is large this will use a lot of memory.
     * IOExceptions are wrapped in UncheckedIOException so no checked exception is declared.
     */
    @Override
    public byte[] getContent() {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public String getWebkitRelativePath() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public File write(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
