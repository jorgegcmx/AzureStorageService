package com.gcmx.files.services;

import com.gcmx.files.exception.AzureBlobStroageException;
import com.gcmx.files.models.Storage;

import java.util.List;

public interface IAzureBlobStorage {

    public String write(Storage storage) throws AzureBlobStroageException;
    public String update(Storage storage) throws AzureBlobStroageException;

    public byte[] read(Storage storage) throws AzureBlobStroageException;

    public List<String> listFiles(Storage storage) throws AzureBlobStroageException;

    public void delete(Storage storage) throws AzureBlobStroageException;

    public void createContainer() throws AzureBlobStroageException;

    public void deleteContainer() throws AzureBlobStroageException;

}