package pe.edu.upc.MLTDAH.students.domain.model.commands;

import org.springframework.web.multipart.MultipartFile;

public record UploadStudentImageCommand(MultipartFile file) {
    public UploadStudentImageCommand {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is null or empty");
        }
    }
}
