package pe.edu.upc.MLTDAH.students.domain.model.valueobjects;

public enum Genders {
    MALE("Masculino"),
    FEMALE("Femenino");

    private final String label;

    Genders(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
