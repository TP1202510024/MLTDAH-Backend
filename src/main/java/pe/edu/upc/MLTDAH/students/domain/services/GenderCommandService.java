package pe.edu.upc.MLTDAH.students.domain.services;

import pe.edu.upc.MLTDAH.students.domain.model.commands.SeedGendersCommand;

public interface GenderCommandService {
    void handle(SeedGendersCommand command);
}
