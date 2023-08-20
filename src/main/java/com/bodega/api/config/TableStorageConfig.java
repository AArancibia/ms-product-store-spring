package com.bodega.api.config;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.table.CloudTableClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TableStorageConfig {
    @Bean
    public CloudBlobClient cloudBlobClient() throws Exception {
        CloudBlobClient blobClient = null;
        try {
            CloudStorageAccount storageAccount = CloudStorageAccount.parse("DefaultEndpointsProtocol=https;AccountName=scvisordatadev;AccountKey=n7AzXQ+OONO870oSYfzueL6euGpwwdfhtlYqQy6XxzrEtKDow2oevwaYn28FvmBEyrZ7UB6zbSGNrXIJIJHA5w==;EndpointSuffix=core.windows.net");
            blobClient = storageAccount.createCloudBlobClient();
        } catch (Exception e) {
            throw new Exception("Error de conexion");
        }
        return blobClient;
    }

    @Bean
    public CloudBlobContainer cloudBlobContainer() throws Exception {
        CloudBlobContainer cloudBlob = null;
        try {
            cloudBlob = cloudBlobClient().getContainerReference("visor-defenders");
            cloudBlob.createIfNotExists();
        } catch (Exception e) {
            throw new Exception("Error de creacion de visor defenders container");
        }
        return cloudBlob;
    }
}
