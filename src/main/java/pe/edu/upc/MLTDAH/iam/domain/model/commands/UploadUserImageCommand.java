package pe.edu.upc.MLTDAH.iam.domain.model.commands;

import org.springframework.web.multipart.MultipartFile;

public record UploadUserImageCommand(MultipartFile file) {
    public UploadUserImageCommand {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is null or empty");
        }
    }
}
