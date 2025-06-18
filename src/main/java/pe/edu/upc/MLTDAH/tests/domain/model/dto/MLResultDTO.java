package pe.edu.upc.MLTDAH.tests.domain.model.dto;

public record MLResultDTO(Double probability, String result) {
    public MLResultDTO {
        if (probability == null || probability < 0) {
            throw new IllegalArgumentException("probability must be a positive number");
        }
        if (result == null || result.isEmpty()) {
            throw new IllegalArgumentException("result must not be empty");
        }
    }
}
