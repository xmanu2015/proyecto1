package pe.com.tdp.ventafija.microservices.service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AzureService {

    private static final Logger logger = LogManager.getLogger(AzureService.class);

    private Iterable<ListBlobItem> listBlobItems(String storageConnectionString) {
        try {
            CloudBlobClient cloudBlobClient = getBlobClient(storageConnectionString);
            CloudBlobContainer cloudBlobContainer = cloudBlobClient.getContainerReference("ventasfija");
            return cloudBlobContainer.listBlobs();
        } catch (URISyntaxException | InvalidKeyException | StorageException e) {
            logger.error("Error", e);
        }
        return null;
    }

    public List<String> listFiles(String storageConnectionString) {
        List<String> response = new ArrayList<>();
        for (ListBlobItem a : Objects.requireNonNull(listBlobItems(storageConnectionString))) {
            response.add(a.getUri().getPath().split("/ventasfija/")[1]);
        }
        return response;
    }

    public File retrieveFile(String fileNameTxt, String storageConnectionString) throws IOException {
        logger.info("obtener archivo");
        File f = File.createTempFile("ftpFile" + new Date().getTime(), ".tmp");
        String fileURI = f.getAbsolutePath();
        logger.info("archivo temporal.......  " + fileURI);
        try {
            CloudBlobClient cloudBlobClient = getBlobClient(storageConnectionString);
            CloudBlobContainer cloudBlobContainer = cloudBlobClient.getContainerReference("ventasfija");
            CloudBlockBlob cloudBlockBlob = cloudBlobContainer.getBlockBlobReference(fileNameTxt);
            cloudBlockBlob.downloadToFile(f.getAbsolutePath());
        } catch (Exception e) {
            logger.error("Error", e);
        }
        return f;
    }

    public void moveFile(String fileName, String newFileName,String storageConnectionString, String channel, String state) {
        logger.info("cambiando nombre de archivo");
        logger.info("nombre de archivo procesado: " + fileName);
        logger.info("nuevo nombre y ruta del archivo procesado: " + channel + "\\" + state + "\\" + newFileName);
        try {
            CloudBlobClient cloudBlobClient = getBlobClient(storageConnectionString);
            CloudBlobContainer cloudBlobContainer = cloudBlobClient.getContainerReference("ventasfija");
            CloudBlockBlob cloudBlockBlobOld = cloudBlobContainer.getBlockBlobReference(fileName);
            CloudBlockBlob cloudBlockBlobNew = cloudBlobContainer.getBlockBlobReference(channel + "\\" + state + "\\" + newFileName);
            cloudBlockBlobNew.startCopy(cloudBlockBlobOld);
            while (cloudBlockBlobNew.getCopyState().getStatus() == CopyStatus.PENDING) {
                Thread.sleep(1000);
            }
            cloudBlockBlobOld.deleteIfExists();
        } catch (Exception e) {
            logger.error("Error", e);
        }

    }

    private static CloudBlobClient getBlobClient(String storageConnectionString) throws URISyntaxException, InvalidKeyException {
        CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
        return storageAccount.createCloudBlobClient();
    }

    public CloudBlobContainer createBlobContainer(String azureConnection, String directoryName) {
        CloudStorageAccount account;
        try {
            account = CloudStorageAccount.parse(azureConnection);
        } catch (Exception e) {
            logger.info("Error en Cuenta." + e);
            return null;
        }
        CloudBlobClient client = account.createCloudBlobClient();

        CloudBlobContainer container;
        try {
            container = client.getContainerReference(directoryName);
        } catch (Exception e) {
            logger.info("Error Compartido." + e);
            return null;
        }

        try {
            if (container.createIfNotExists()) {
                logger.info("New container created");
            }
        } catch (Exception e) {
            logger.info("Error Crear Compartido." + e);
        }

        return container;
    }

}
