package pe.edu.upc.MLTDAH.students.interfaces.rest.resources;

import pe.edu.upc.MLTDAH.iam.interfaces.rest.resources.UserResource;

public record ParentResource(Long id, StudentResource student, UserResource parent){

}
