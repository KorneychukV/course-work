package ru.rsatu.controllers.old;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.rsatu.services.old.AdminService;

import javax.inject.Inject;
import javax.ws.rs.*;

//@Path("/prof/edu")
public class AdminController {

//    @Inject
//    AdminService adminService;

//    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

//    /**
//     * добавление нового раздела обучения
//     * @return
//     */
//    @POST
//    @Path("addStudySection")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin"})
//    public Response addStudy(SectionRequest request) {
//        BaseResponse response = adminService.addSection(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * удаление раздела обучения
//     * @return
//     */
//    @POST
//    @Path("deleteStudySection")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin"})
//    public Response deleteStudySection(DeleteSecRequest request)  {
//        BaseResponse response = adminService.deleteSection(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * удаление направления
//     * @return
//     */
//    @POST
//    @Path("deleteCourses")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin"})
//    public Response deleteCourses(DeleteCoursesRequest request)  {
//        BaseResponse response = adminService.deleteCourses(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * получение разделов обучения
//     * @return
//     */
//    @GET
//    @Path("getSection")
//    @Produces("application/json")
//    @RolesAllowed({"admin", "razrab"})
//    public Response getStudy(){
//        BaseResponse response = adminService.getSection();
//        return Response.ok(response).build();
//    }

//    /**
//     * редактирование раздела обучения
//     * @return
//     */
//    @POST
//    @Path("editSection")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin"})
//    public Response editSection(EditSectionRequest request){
//        BaseResponse response = adminService.editSection(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * добавление нового курса
//     * @return
//     */
//    @POST
//    @Path("addCourse")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin"})
//    public Response addCourse(CourseRequest request){
//        BaseResponse response = adminService.addCourse(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * редактирование курса
//     * @return
//     */
//    @POST
//    @Path("editCourse")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin"})
//    public Response editCourse(EditCourseRequest request){
//        BaseResponse response = adminService.editCourse(request);
//        return Response.ok(response).build();
//    }


//    /**
//     * получение курсов
//     * @return
//     */
//    @POST
//    @Path("getCourses")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @RolesAllowed({"admin", "razrab"})
//    public Response getCourses(GetAllCourseRequest request){
//        BaseResponse response = adminService.getCourse(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * получение ответов на вопрос
//     * @return
//     */
//    @POST
//    @Path("getAnswer")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @RolesAllowed({"admin", "razrab"})
//    public Response getAnswer(GetAnswerRequest request){
//        BaseResponse response = adminService.getAnswer(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * добавление новой программы обучения
//     * @return
//     */
//    @POST
//    @Path("addProgram")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin"})
//    public Response addProgram(AddProgramRequest request){
//        BaseResponse response = adminService.addProgram(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * добавление вопроса
//     * @return
//     */
//    @POST
//    @Path("addQuestion")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin", "razrab"})
//    public Response addQuestion(AddQuestionRequest request){
//        BaseResponse response = adminService.addQuestion(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * удаление вопроса вопроса
//     * @return
//     */
//    @POST
//    @Path("deleteQuestion")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin", "razrab"})
//    public Response deleteQuestion(DeleteQuestionRequest request){
//        BaseResponse response = adminService.deleteQuestion(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * удаление программы
//     * @return
//     */
//    @POST
//    @Path("deleteProgram")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin"})
//    public Response deleteProgram(DeleteProgramRequest request){
//        BaseResponse response = adminService.deleteProgram(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * удаление литературы
//     * @return
//     */
//    @POST
//    @Path("deleteLit")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin", "razrab"})
//    public Response deleteLit(DeleteLitRequest request){
//        BaseResponse response = adminService.deleteLit(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * изменение вопроса
//     * @return
//     */
//    @POST
//    @Path("editQuestion")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin", "razrab"})
//    public Response editQuestion(EditQuestionRequest request){
//        BaseResponse response = adminService.editQuestion(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * выгрузка вопросов
//     * @return
//     */
//    @POST
//    @Path("getQuestion")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin", "razrab"})
//    public Response getQuestion(GetQuestionsRequest request){
//        BaseResponse response = adminService.getQuestion(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * получение программ обучения
//     * @return
//     */
//    @POST
//    @Path("getPrograms")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @RolesAllowed({"admin", "razrab"})
//    public Response getPrograms(GetAllProgramRequest request){
//        BaseResponse response = adminService.getPrograms(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * редактирование программы
//     * @return
//     */
//    @POST
//    @Path("editProgram")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin", "razrab"})
//    public Response editPrograms(EditProgramRequest request){
//        BaseResponse response = adminService.editPrograms(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * добавление литературы
//     * @return
//     */
//    @POST
//    @Path("addLiter")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @Transactional
//    @RolesAllowed({"admin", "razrab"})
//    public Response addLiterature(AddLiteratureRequest request){
//        BaseResponse response = adminService.addLiterature(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * получить статистику по студентам
//     * @return
//     */
//    @GET
//    @Path("statistics")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @RolesAllowed({"admin", "razrab"})
//    @Transactional
//    public Response getStatistics(GetStatisticRequest request){
//        BaseResponse response = adminService.getStatistic(request);
//        return Response.ok(response).build();
//    }
}
