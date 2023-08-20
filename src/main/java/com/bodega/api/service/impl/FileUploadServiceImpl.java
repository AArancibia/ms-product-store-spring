package com.bodega.api.service.impl;

import com.bodega.api.service.FileUploadService;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.ListBlobItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Slf4j
@Repository
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    CloudBlobContainer cloudBlob;

    @Override
    public void listAllBlobs() {
        Iterable<ListBlobItem> blobItems = cloudBlob.listBlobs();
        for (ListBlobItem blobItem: blobItems) {
            System.out.println("HOST: {}" + blobItem.getUri().getHost());
        }
    }
}
