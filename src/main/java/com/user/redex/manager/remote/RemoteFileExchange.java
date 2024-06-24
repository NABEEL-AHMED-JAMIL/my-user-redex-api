package com.user.redex.manager.remote;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nabeel Ahmed
 */
@Component
public class RemoteFileExchange {

    private Logger logger = LogManager.getLogger(RemoteFileExchange.class);

    private final String FILE_PATH = "filePath";
    public final String PUBLIC_FILE = "publicFile";
    public final String FILE_URL_PATTERN = "%s/%s:%s/%s";

    @Value("${bucket.id}")
    private String bucketId;

    @Value("${bucket.access_key}")
    private String accessKey;

    @Value("${bucket.secret_key}")
    private String secretKey;

    @Value("${bucket.secret_region}")
    private String region;

    @Value("${bucket.storage_url}")
    private String storageUrl;

    private AmazonS3 amazonS3;

    public RemoteFileExchange() {}

    @PostConstruct
    public void initialize() {
        logger.debug("+================AWS--START====================+");
        AwsClientBuilder.EndpointConfiguration awsClientBuilder =
            new AwsClientBuilder.EndpointConfiguration(this.storageUrl, this.region);
        AWSStaticCredentialsProvider awsStaticCredentialsProvider =
            new AWSStaticCredentialsProvider(new BasicAWSCredentials(this.accessKey, this.secretKey));
        this.amazonS3 = AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(awsClientBuilder)
            .withCredentials(awsStaticCredentialsProvider).build();
        logger.debug("+================AWS-S3-END====================+");
    }

    /**
     * Method use to upload object to bucket
     * @param bucketName
     * @param objKey
     * @param inputStream
     * @param isPublicAccess
     * @return Map<?, ?>
     * @throws AmazonClientException
     * */
    public Map<String, Object> uploadToBucket(String bucketName, String objKey,
        InputStream inputStream, Boolean isPublicAccess) throws AmazonClientException, IOException {
        logger.debug("Uploading a new object to S3 from a file => " + objKey);
        Map<String, Object> resultObject = new HashMap<>();
        PutObjectRequest putObjectRequest = new PutObjectRequest(getBucketName(bucketName), objKey,
            inputStream, new ObjectMetadata());
        if (isPublicAccess) {
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        }
        PutObjectResult putObjectResult = this.amazonS3.putObject(putObjectRequest);
        if (inputStream != null) {
            inputStream.close();
        }
        resultObject.put(FILE_PATH, objKey);
        // only show the url in log if its allow public read access
        if (isPublicAccess) {
            String fileUrl = String.format(FILE_URL_PATTERN,
                this.storageUrl, this.bucketId, bucketName, objKey);
            resultObject.put(PUBLIC_FILE, fileUrl);
            logger.info("File Url :- " + fileUrl);
        }
        logger.info("Upload File Detail Aws :- " + putObjectResult.toString());
        return resultObject;
    }

    private String getBucketName(String bucket) {
        return "/" + bucket;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

}
