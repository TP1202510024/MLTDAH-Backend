package pe.edu.upc.MLTDAH.iam.domain.model.queries;

public record GetAllUsersByInstitutionIdQuery(Long id) {
    public GetAllUsersByInstitutionIdQuery {
        if(id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
