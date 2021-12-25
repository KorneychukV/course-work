package ru.rsatu;

import ru.rsatu.admin.adminPOJO.course.getAll.GetAllCourseRequest;
import ru.rsatu.admin.adminPOJO.programs.GetProgramIdRequest;
import ru.rsatu.admin.adminPOJO.programs.getAll.GetAllProgramRequest;
import ru.rsatu.common.BaseResponse;
import ru.rsatu.testing.TestingService;
import ru.rsatu.testing.startTest.getResult.GetResultRequest;
import ru.rsatu.testing.startTest.putAnswer.PutAnswerRequest;
import ru.rsatu.testing.startTest.getQuestion.GetQuestionRequest;
import ru.rsatu.testing.startTest.startTest.StartRequest;
import ru.rsatu.testing.startTest.statistic.GetStatisticRequest;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/prof")
public class ProfResourse {

    @Inject
    ProfService profService;

    @Inject
    TestingService testingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    /**
     * получение разделов обучения
     * @return
     */
    @GET
    @Path("getSection")
    @Produces("application/json")
    public Response getStudy(){
        BaseResponse response = profService.getSection();
        return Response.ok(response).build();
    }

    /**
     * получение курсов
     * @return
     */
    @POST
    @Path("getCourses")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getCourses(GetAllCourseRequest request){
        BaseResponse response = profService.getCourse(request);
        return Response.ok(response).build();
    }

    /**
     * получение литературу
     * @return
     */
    @POST
    @Path("getLiterature")
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed({"default-roles-prof"})
    public Response getLiterature(GetProgramIdRequest request){
        BaseResponse response = profService.getLiterature(request);
        return Response.ok(response).build();
    }

    /**
     * получение программ обучения
     * @return
     */
    @POST
    @Path("getPrograms")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getPrograms(GetAllProgramRequest request){
        BaseResponse response = profService.getPrograms(request);
        return Response.ok(response).build();
    }


    /**
     * начать тестирование
     * @return
     */
    @POST
    @Path("start_test")
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed({"default-roles-prof"})
    @Transactional
    public Response startTest(StartRequest request){
        BaseResponse response = testingService.startTest(request);
        return Response.ok(response).build();
    }


    /**
     * получить следующий вопрос
     * @return
     */
    @POST
    @Path("get_question")
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed({"default-roles-prof"})
    @Transactional
    public Response getQuestion(GetQuestionRequest request){
        BaseResponse response = testingService.getQuestion(request);
        return Response.ok(response).build();
    }

    /**
     * записать ответ
     * @return
     */
    @POST
    @Path("put_answer")
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed({"default-roles-prof"})
    @Transactional
    public Response putAnswer(PutAnswerRequest request){
        BaseResponse response = testingService.putAnswer(request);
        return Response.ok(response).build();
    }

    /**
     * получить результат
     * @return
     */
    @POST
    @Path("get_result")
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed({"default-roles-prof"})
    @Transactional
    public Response getResult(GetResultRequest request){
        BaseResponse response = testingService.getResult(request);
        return Response.ok(response).build();
    }


}