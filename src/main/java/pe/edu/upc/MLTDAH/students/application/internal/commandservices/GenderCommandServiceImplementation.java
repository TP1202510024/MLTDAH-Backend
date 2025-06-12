package pe.edu.upc.MLTDAH.students.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.students.domain.model.commands.SeedGendersCommand;
import pe.edu.upc.MLTDAH.students.domain.model.entities.Gender;
import pe.edu.upc.MLTDAH.students.domain.model.entities.SchoolGrade;
import pe.edu.upc.MLTDAH.students.domain.model.valueobjects.Genders;
import pe.edu.upc.MLTDAH.students.domain.model.valueobjects.SchoolGrades;
import pe.edu.upc.MLTDAH.students.domain.services.GenderCommandService;
import pe.edu.upc.MLTDAH.students.infrastructure.persistence.jpa.GenderRepository;

import java.util.Arrays;

@Service
public class GenderCommandServiceImplementation implements GenderCommandService {
    private final GenderRepository genderRepository;

    public GenderCommandServiceImplementation(GenderRepository genderRepository){
        this.genderRepository = genderRepository;
    }

    @Override
    public void handle(SeedGendersCommand command) {
        Arrays.stream(Genders.values()).forEach(gender -> {
            if(!genderRepository.existsByName(gender)) {
                genderRepository.save(new Gender(gender));
            }
        });
    }
}
