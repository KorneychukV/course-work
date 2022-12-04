package ru.rsatu.controllers.old;

//@Path("/prof")
public class ProfController {

//    private static final Logger LOG = Logger.getLogger(ProfController.class);
//
//    @Inject
//    ProfService profService;
//
//    @Inject
//    TestingService testingService;
//
//    @Inject
//    SecurityIdentity keycloakSecurityContext;

//    /**
//     * получение разделов обучения
//     * @return
//     */
//    @GET
//    @Path("getSection")
//    @Counted(name = "helloCount", description = "Количество запросов к ресурсу hello")
//    @Timed(name = "helloTimer", description = "Вычисляется, как долго выполнялся метод.", unit = MetricUnits.MILLISECONDS)
//    @Produces("application/json")
//    public Response getStudy(){
//
//        System.out.println("Username: " + keycloakSecurityContext.getPrincipal().getName() + " ; Roles: ");
//        keycloakSecurityContext.getRoles().forEach(System.out::println);
//        LOG.info("Some useful log message");
//
//        BaseResponse response = profService.getSection();
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
//    public Response getCourses(GetAllCourseRequest request){
//        BaseResponse response = profService.getCourse(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * получение литературу
//     * @return
//     */
//    @POST
//    @Path("getLiterature")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @RolesAllowed({"default-roles-prof"})
//    public Response getLiterature(GetProgramIdRequest request){
//        BaseResponse response = profService.getLiterature(request);
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
//    public Response getPrograms(GetAllProgramRequest request){
//        BaseResponse response = profService.getPrograms(request);
//        return Response.ok(response).build();
//    }


//    /**
//     * начать тестирование
//     * @return
//     */
//    @POST
//    @Path("start_test")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @RolesAllowed({"default-roles-prof"})
//    @Transactional
//    public Response startTest(StartRequest request){
//        BaseResponse response = testingService.startTest(request);
//        return Response.ok(response).build();
//    }


//    /**
//     * получить следующий вопрос
//     * @return
//     */
//    @POST
//    @Path("get_question")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @RolesAllowed({"default-roles-prof"})
//    @Transactional
//    public Response getQuestion(GetQuestionRequest request){
//        BaseResponse response = testingService.getQuestion(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * записать ответ
//     * @return
//     */
//    @POST
//    @Path("put_answer")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @RolesAllowed({"default-roles-prof"})
//    @Transactional
//    public Response putAnswer(PutAnswerRequest request){
//        BaseResponse response = testingService.putAnswer(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * получить результат
//     * @return
//     */
//    @POST
//    @Path("get_result")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @RolesAllowed({"default-roles-prof"})
//    @Transactional
//    public Response getResult(GetResultRequest request){
//        BaseResponse response = testingService.getResult(request);
//        return Response.ok(response).build();
//    }


}