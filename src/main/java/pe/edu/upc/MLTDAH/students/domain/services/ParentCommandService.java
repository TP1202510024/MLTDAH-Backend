package pe.edu.upc.MLTDAH.students.domain.services;

import org.apache.catalina.User;
import pe.edu.upc.MLTDAH.students.domain.model.aggregates.Parent;
import pe.edu.upc.MLTDAH.students.domain.model.commands.CreateParentCommand;
import pe.edu.upc.MLTDAH.students.domain.model.commands.DeleteParentCommand;

import java.util.Optional;

public interface ParentCommandService {
    Optional<Parent> handle(CreateParentCommand command);
    Optional<Parent> handle(DeleteParentCommand command);
}
