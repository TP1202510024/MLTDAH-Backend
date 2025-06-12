package pe.edu.upc.MLTDAH.students.domain.model.valueobjects;

import java.util.Arrays;
import java.util.Optional;

public enum SchoolGrades {
    FIRST_GRADE("1st Grade"),
    SECOND_GRADE("2nd Grade"),
    THIRD_GRADE("3rd Grade"),
    FOURTH_GRADE("4th Grade"),
    FIFTH_GRADE("5th Grade"),
    SIXTH_GRADE("6th Grade");

    private final String label;

    SchoolGrades(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
