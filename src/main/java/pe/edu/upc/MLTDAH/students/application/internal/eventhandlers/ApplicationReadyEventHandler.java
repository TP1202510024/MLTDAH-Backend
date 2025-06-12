package pe.edu.upc.MLTDAH.students.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.students.domain.model.commands.SeedGendersCommand;
import pe.edu.upc.MLTDAH.students.domain.model.commands.SeedSchoolGradesCommand;
import pe.edu.upc.MLTDAH.students.domain.services.GenderCommandService;
import pe.edu.upc.MLTDAH.students.domain.services.SchoolGradeCommandService;

import java.sql.Timestamp;

@Service
@Component("studentsApplicationReadyEventHandler")
public class ApplicationReadyEventHandler {
    private final SchoolGradeCommandService schoolGradeCommandService;
    private final GenderCommandService genderCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(SchoolGradeCommandService schoolGradeCommandService, GenderCommandService genderCommandService) {
        this.schoolGradeCommandService = schoolGradeCommandService;
        this.genderCommandService = genderCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();

        LOGGER.info("Starting to verify if School grades seeding is needed for {} at {}", applicationName, currentTimestamp());
        schoolGradeCommandService.handle(new SeedSchoolGradesCommand());
        LOGGER.info("School grades seeding verification finished for {} at {}", applicationName, currentTimestamp());

        LOGGER.info("Starting to verify if Genders seeding is needed for {} at {}", applicationName, currentTimestamp());
        genderCommandService.handle(new SeedGendersCommand());
        LOGGER.info("Genders seeding verification finished for {} at {}", applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
