package pe.edu.upc.MLTDAH.iam.domain.model.commands;

import org.springframework.web.multipart.MultipartFile;

public record UploadInstitutionImageCommand(MultipartFile file) {
    public UploadInstitutionImageCommand {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is null or empty");
        }
    }
}