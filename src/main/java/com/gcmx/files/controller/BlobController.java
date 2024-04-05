package com.gcmx.files.controller;

import com.azure.core.management.Resource;
import com.gcmx.files.exception.AzureBlobStroageException;
import com.gcmx.files.models.Storage;
import com.gcmx.files.services.impl.AzureBlobStorageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.WritableResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

@RestController
@RequestMapping("blob")
public class BlobController {

    @Autowired
    private AzureBlobStorageImpl service;

    @PostMapping("/writeBlobFile")
    public String writeBlobFile(@RequestBody String data) throws  AzureBlobStroageException {
        Storage storage = new Storage();
        storage.setPath("images");
        storage.setFileName("img.txt");
        String datos ="Hola";
        storage.setInputStream(new ByteArrayInputStream(datos.getBytes()));
        service.write(storage);
        return "file was updated";
    }
}