package pe.edu.upc.MLTDAH.tests.domain.services;

import pe.edu.upc.MLTDAH.tests.domain.model.commands.CreateQuestionCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.DeleteQuestionCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.commands.UpdateQuestionCommand;
import pe.edu.upc.MLTDAH.tests.domain.model.entities.Question;

import java.util.Optional;

public interface QuestionCommandService {
    Optional<Question> handle(CreateQuestionCommand command);
    Optional<Question> handle(UpdateQuestionCommand command, Long id);
    Optional<Question> handle(DeleteQuestionCommand command);
}
