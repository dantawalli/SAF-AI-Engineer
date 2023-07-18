package com.flexisaf.resources;

import com.flexisaf.common.ApiParams;
import com.flexisaf.common.ApiPath;
import com.flexisaf.requests.MinuteRequest;
import com.flexisaf.services.MinuteService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiPath.MINUTE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class MinuteResource {

    @Inject
    MinuteService minuteService;

    @POST
    public Response createMinute(
        @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
        @NotNull @Valid MinuteRequest minuteRequest){

        var minuteResponse = minuteService.create(minuteRequest,organizationId);

        return Response
            .ok(minuteResponse)
            .build();
    }

    @GET
    @Path(ApiPath.ID_PATH)
    public Response getMinuteById(
        @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
        @PathParam(ApiParams.ID) Long minuteId){

        var minuteResponse = minuteService.getByMinuteById(minuteId);

        return Response
            .ok(minuteResponse)
            .build();
    }

    @GET
    @Path(ApiPath.MEETING + ApiPath.ID_PATH)
    public Response getMinuteByMeetingId(
        @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
        @PathParam(ApiParams.ID) Long meetingId){

        var minuteResponse = minuteService.getByMeetingId(meetingId);

        return Response
            .ok(minuteResponse)
            .build();
    }

    @GET
    @Path(ApiPath.ORGANIZATION)
    public Response getMinuteByOrganization(
        @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
        @QueryParam(ApiParams.OFFSET) @DefaultValue(value = "0") int offset,
        @QueryParam(ApiParams.LIMIT) @DefaultValue(value = "50") int limit){

        var minuteResponse = minuteService.getByOrganization(organizationId,offset,limit);

        return Response
            .ok(minuteResponse)
            .build();
    }

    @PUT
    @Path(ApiPath.ID_PATH)
    public Response updateMinute(
        @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
        @PathParam(ApiParams.ID) Long minuteId,
        MinuteRequest minuteRequest){

        var minuteResponse = minuteService.update(minuteRequest,minuteId);


        return Response
            .ok(minuteResponse)
            .build();
    }

    @DELETE
    @Path(ApiPath.ID_PATH)
    public Response deleteMinute(
        @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
        @PathParam(ApiParams.ID) Long minuteId){

        minuteService.delete(minuteId);

        return Response
            .noContent()
            .build();
    }
}
