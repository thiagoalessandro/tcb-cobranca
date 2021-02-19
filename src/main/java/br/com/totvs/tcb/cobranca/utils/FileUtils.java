package br.com.totvs.tcb.cobranca.utils;

import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class FileUtils {
    public static InputStream resourceToInputStream(Resource resource) throws IOException {
        return new FileInputStream(resource.getFile().toPath().toFile());
    }

    public static byte[] resourceToBytes(Resource resource) throws IOException {
        return Files.readAllBytes(resource.getFile().toPath());
    }
}
