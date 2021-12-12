package ru.rsatu.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.rsatu.admin.adminPOJO.SectionRequest;
import ru.rsatu.common.BaseResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/prof/edu")
public class AdminResource {

    @Inject
    AdminService adminService;

    private static final Logger LOG = LoggerFactory.getLogger(AdminResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    /**
     * добавление нового раздела обучения
     * @return
     */
    @POST
    @Path("addStudySection")
    @Produces("application/json")
    @Consumes("application/json")
    @Transactional
    //todo role
    //@RolesAllowed({"E_ArchiveAdmins", "E_ArchiveOTD", "E_ArchiveSGT", "NotesManagers"})
    public Response addStudy(SectionRequest request){
        BaseResponse response = adminService.addSection(request);
        return Response.ok(response).build();
    }

    /**
     * получение разделов обучения
     * @return
     */
    @GET
    @Path("getSection")
    @Produces("application/json")
    @Transactional
    //todo role
    //@RolesAllowed({"E_ArchiveAdmins", "E_ArchiveOTD", "E_ArchiveSGT", "NotesManagers"})
    public Response getStudy(){
        BaseResponse response = adminService.getSection();
        return Response.ok(response).build();
    }

}
