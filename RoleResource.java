package com.flexisaf.resources;

import com.flexisaf.adapter.ums.request.RoleRequest;
import com.flexisaf.adapter.ums.service.RoleService;
import com.flexisaf.common.ApiPath;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiPath.ROLES)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleResource {

    @Inject
    RoleService roleService;

    @POST
    public Response addRole(@NotNull @HeaderParam("X-ORG-ID") String orgId,@NotNull @Valid RoleRequest roleRequest){

        var roleResponse = roleService.addRole(orgId,roleRequest);

        return Response
            .ok(roleResponse)
            .build();
    }

    @GET
    public Response getAllRoles(@NotNull @HeaderParam("X-ORG-ID") String orgId){

        var roleResponse = roleService.getAllRoles(orgId);

        return Response
            .ok(roleResponse)
            .build();
    }

    @PUT
    @Path("/{roleId}")
    public Response updateRoles(
        @NotNull @HeaderParam("X-ORG-ID") String orgId,
        @NotNull @Valid RoleRequest roleRequest,
        @PathParam("roleId") String roleId){

        var roleResponse = roleService.updateRole(orgId,roleId,roleRequest);

        return Response
            .ok(roleResponse)
            .build();
    }

    @DELETE
    @Path("/{roleId}")
    public Response removeRoles(@NotNull @HeaderParam("X-ORG-ID") String orgId,@PathParam("roleId") String roleId){

        roleService.removeRole(orgId,roleId);

        return Response
            .noContent()
            .build();
    }

    @GET
    @Path("/{roleId}")
    public Response getOneRoles(@NotNull @HeaderParam("X-ORG-ID") String orgId,@PathParam("roleId") String roleId){

        var roleResponse = roleService.getOneRole(orgId,roleId);

        return Response
            .ok(roleResponse)
            .build();
    }

}
