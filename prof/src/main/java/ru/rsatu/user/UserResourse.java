package ru.rsatu.user;

import io.quarkus.oidc.common.runtime.OidcCommonConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;
import ru.rsatu.ProfService;
import ru.rsatu.admin.adminPOJO.course.getAll.GetAllCourseRequest;
import ru.rsatu.admin.adminPOJO.programs.getAll.GetAllProgramRequest;
import ru.rsatu.common.BaseResponse;
import ru.rsatu.user.userPOJO.BuyProgramRequest;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/prof/user")
public class UserResourse {
    @Inject
    UserService userService;

    /**
     * покупка
     * @return
     */
    @POST
    @Path("buyProgram")
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed({"default-roles-prof"})
    @Transactional
    public Response getCourses(BuyProgramRequest request){
        BaseResponse response = userService.buyProgram(request);
        return Response.ok(response).build();
    }

    /**
     * получение программ обучения главной страницы с отображением купленым программ
     * @return
     */
    @POST
    @Path("getPrograms")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getPrograms(GetAllProgramRequest request){
        BaseResponse response = userService.getPrograms(request);
        return Response.ok(response).build();
    }

    /**
     * получение программ авторизованного пользователя
     * @return
     */
    @GET
    @Path("programs")
    @Produces("application/json")
    public Response programsByUser(){
        BaseResponse response = userService.programsByUser();
        return Response.ok(response).build();
    }
}
