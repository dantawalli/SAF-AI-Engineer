package com.flexisaf.resources;

import com.flexisaf.common.ApiParams;
import com.flexisaf.common.ApiPath;
import com.flexisaf.requests.MeetingRequest;
import com.flexisaf.services.MeetingService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiPath.MEETING)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class MeetingResource {

    @Inject
    MeetingService meetingService;

    @POST
    public Response createMeeting(
        @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
        @NotNull @Valid MeetingRequest meetingRequest){

        var meetingResponse = meetingService.create(meetingRequest,organizationId);

        return Response
            .ok(meetingResponse)
            .build();
    }

    @GET
    @Path(ApiPath.ID_PATH)
    public Response getMeetingById(
        @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
        @PathParam(ApiParams.ID) Long meetingId){

        var meetingResponse = meetingService.getById(meetingId);

        return Response
            .ok(meetingResponse)
            .build();
    }

    @GET
    @Path(ApiPath.ORGANIZATION)
    public Response getMeetingByOrganization(
        @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
        @QueryParam(ApiParams.OFFSET) @DefaultValue(value = "0") int offset,
        @QueryParam(ApiParams.LIMIT) @DefaultValue(value = "50") int limit){

        var meetingResponse = meetingService.getByOrganization(organizationId,offset,limit);

        return Response
            .ok(meetingResponse)
            .build();
    }

    @GET
    @Path(ApiPath.UPCOMING_MEETINGS)
    public Response getUpcomingMeetings(
            @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
            @QueryParam(ApiParams.OFFSET) @DefaultValue(value = "0") int offset,
            @QueryParam(ApiParams.LIMIT) @DefaultValue(value = "50") int limit) {

        var meetingResponse = meetingService.getUpcomingMeetings(organizationId, offset, limit);

        return Response
                .ok(meetingResponse)
                .build();
    }

    @GET
    @Path(ApiPath.PREVIOUS_MEETINGS)
    public Response getPreviousMeetings(
            @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
            @QueryParam(ApiParams.OFFSET) @DefaultValue(value = "0") int offset,
            @QueryParam(ApiParams.LIMIT) @DefaultValue(value = "50") int limit) {

        var meetingResponse = meetingService.getPreviousMeetings(organizationId, offset, limit);

        return Response
                .ok(meetingResponse)
                .build();
    }

    @PUT
    @Path(ApiPath.ID_PATH)
    public Response updateMeeting(
        @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
        @PathParam(ApiParams.ID) Long meetingId,
        MeetingRequest meetingRequest){

        var meetingResponse = meetingService.update(meetingRequest,meetingId);


        return Response
            .ok(meetingResponse)
            .build();
    }

    @PUT
    @Path(ApiPath.ID_PATH + ApiPath.START)
    public Response startMeeting(
            @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
            @PathParam(ApiParams.ID) Long meetingId) {

        meetingService.startMeeting(meetingId);
        
        return Response
                .ok("Meeting Started")
                .build();
    }

    @PUT
    @Path(ApiPath.ID_PATH + ApiPath.STOP)
    public Response stopMeeting(
            @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
            @PathParam(ApiParams.ID) Long meetingId) {

        meetingService.stopMeeting(meetingId);

        return Response
                .ok("Meeting Stopped")
                .build();
    }

    @DELETE
    @Path(ApiPath.ID_PATH)
    public Response deleteMeeting(
        @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
        @PathParam(ApiParams.ID) Long meetingId){

        meetingService.delete(meetingId);

        return Response
            .noContent()
            .build();
    }
}
