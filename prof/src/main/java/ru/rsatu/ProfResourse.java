package ru.rsatu;

import ru.rsatu.admin.AdminService;
import ru.rsatu.admin.adminPOJO.course.getAll.GetAllCourseRequest;
import ru.rsatu.admin.adminPOJO.programs.GetProgramIdRequest;
import ru.rsatu.admin.adminPOJO.programs.getAll.GetAllProgramRequest;
import ru.rsatu.common.BaseResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/prof")
public class ProfResourse {

    @Inject
    ProfService profService;

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
}