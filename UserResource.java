package com.flexisaf.resources;

import com.flexisaf.adapter.ums.request.*;
import com.flexisaf.adapter.ums.service.UserService;
import com.flexisaf.common.ApiParams;
import com.flexisaf.common.ApiPath;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path(ApiPath.USER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @POST
    public Response addUser(@NotNull @HeaderParam("X-ORG-ID") String orgId, @NotNull @Valid UserRequest userRequest){

        var user = userService.registerUser(orgId,userRequest);

        return Response
            .ok(user)
            .build();
    }

    @GET
    public Response getUser(
        @NotNull @HeaderParam("X-ORG-ID") String orgId,
        @QueryParam(ApiParams.LIMIT) @DefaultValue(value = "10") int limit,
        @QueryParam(ApiParams.OFFSET) @DefaultValue(value = "0") int offset){

        var users = userService.getUser(orgId,limit,offset);

        return Response
            .ok(users)
            .build();
    }


    @GET
    @Path(ApiPath.USERNAME_PATH)
    public Response getUserByUsername(@NotNull @HeaderParam("X-ORG-ID") String orgId,@PathParam(ApiParams.USERNAME) String username){

        var user = userService.getUserByUsername(orgId,username);

        return Response
            .ok(user)
            .build();

    }


    @GET
    @Path(ApiPath.ID_PATH)
    public Response getUserById(@NotNull @HeaderParam("X-ORG-ID") String orgId,@PathParam(ApiParams.ID) String id){

        var user = userService.getUserById(orgId,id);

        return Response
            .ok(user)
            .build();

    }

    @PATCH
    @Path(ApiPath.ID_PATH)
    public Response updateUser(
        @NotNull @HeaderParam("X-ORG-ID") String orgId,
        @PathParam(ApiParams.ID) String id,
        UserRequestBody requestBody){

        var updatedUser = userService.updateUser(orgId,id,requestBody);

        return Response
            .ok(updatedUser)
            .build();

    }

    @DELETE
    @Path(ApiPath.ID_PATH)
    public Response removeUser(
        @NotNull @HeaderParam("X-ORG-ID") String orgId,
        @PathParam(ApiParams.ID) String userId){

        userService.removeUser(orgId,userId);

        return Response
            .noContent()
            .build();

    }

    @POST
    @Path(ApiPath.ID_PATH)
    public Response updateUserRole(
        @NotNull @HeaderParam("X-ORG-ID") String orgId,
        @PathParam(ApiParams.ID) String userId,
        List<UserRoleRequest> roleRequest){

        var updatedUser = userService.updateUserRole(orgId,userId,roleRequest);

        return Response
            .ok(updatedUser)
            .build();

    }

    @POST
    @Path(ApiPath.BULK_USER_CREATION)
    public Response addMultipleUser(@NotBlank @HeaderParam("X-ORG-ID") String orgId, @NotNull @Size(min = 1,max=4) @Valid Set<UserRequest> userRequestList){

        var users = userService.registerMultipleUsers(orgId,userRequestList);

        return Response
            .ok(users)
            .build();
    }

}
