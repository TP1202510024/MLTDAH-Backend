package pe.edu.upc.MLTDAH.uploads.application.internal.outboundservices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {
    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.s3.base-url}")
    private String baseUrl;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        String nameFile = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            nameFile = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase();
        }

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key( nameFile)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

        return baseUrl + "/" + nameFile;
    }
}