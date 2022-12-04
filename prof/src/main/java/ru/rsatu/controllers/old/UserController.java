package ru.rsatu.controllers.old;

import ru.rsatu.services.old.UserService;

import javax.inject.Inject;

//@Path("/prof/user")
public class UserController {

    @Inject
    UserService userService;

//    @Inject
//    SecurityIdentity securityIdentity;

//    /**
//     * покупка
//     * @return
//     */
//    @POST
//    @Path("buyProgram")
//    @Produces("application/json")
//    @Consumes("application/json")
//    @RolesAllowed({"default-roles-prof"})
//    @Transactional
//    public Response getCourses(BuyProgramRequest request){
//        String username = securityIdentity.getPrincipal().getName();
//        BaseResponse response = userService.buyProgram(request, username);
//        return Response.ok(response).build();
//    }

//    /**
//     * получение программ обучения главной страницы с отображением купленых программ
//     * @return
//     */
//    @POST
//    @Path("getPrograms")
//    @Produces("application/json")
//    @Consumes("application/json")
//    public Response getPrograms(GetAllProgramRequest request){
//        BaseResponse response = userService.getPrograms(request);
//        return Response.ok(response).build();
//    }

//    /**
//     * получение программ авторизованного пользователя
//     * @return
//     */
//    @GET
//    @Path("programs")
//    @RolesAllowed({"default-roles-prof"})
//    @Produces("application/json")
//    public Response programsByUser(){
//        BaseResponse response = userService.programsByUser();
//        return Response.ok(response).build();
//    }
}
