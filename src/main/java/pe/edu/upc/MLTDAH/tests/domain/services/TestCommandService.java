package pe.edu.upc.MLTDAH.tests.domain.services;

import pe.edu.upc.MLTDAH.tests.domain.model.aggregates.Test;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateTestCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteTestCommand;

import java.util.Optional;

public interface TestCommandService {
    Optional<Test> handle(CreateTestCommand command);
    Optional<Test> handle(DeleteTestCommand command);
}
