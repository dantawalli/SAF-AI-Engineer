package com.flexisaf.resources;

import com.flexisaf.common.ApiParams;
import com.flexisaf.common.ApiPath;
import com.flexisaf.requests.AttendanceRequest;
import com.flexisaf.requests.AttendeeRequest;
import com.flexisaf.services.AttendanceService;
import com.flexisaf.services.MeetingService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path(ApiPath.ATTENDANCE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class AttendanceResource {
    @Inject
    AttendanceService attendanceService;

    @POST
    public Response createAttendance(
            @NotBlank @Valid @HeaderParam("X-ORG-ID") String organizationId,
            @NotNull @Valid AttendanceRequest attendanceRequest){
        var attendanceResponse = attendanceService.createAttendance(attendanceRequest);
        return Response.ok(attendanceResponse).build();
    }
    @POST
    @Path(ApiPath.ATTENDANCE_ID_PATH)
    public Response addAttendee(
        @NotNull @Valid AttendeeRequest attendeeRequest){

        var attendanceResponse = attendanceService.addAttendee(attendeeRequest);

        return Response.ok(attendanceResponse).build();
    }


    @POST
    @Path(ApiPath.ATTENDEES_PATH)
    public Response addAttendees(@NotBlank @HeaderParam("X-ORG-ID") String orgId, @NotNull @Size(min = 2) @Valid Set<AttendeeRequest> attendeeRequestSet){

        var attendees = attendanceService.AddMultipleAttendees(attendeeRequestSet);

        return Response
                .ok(attendees)
                .build();
    }

    @GET
    @Path(ApiPath.ATTENDANCE_ID_PATH)
    public Response getAttendees(@PathParam(ApiParams.ATTENDANCE_ID) Long attendanceId,
                                 @QueryParam(ApiParams.OFFSET) @DefaultValue(value = "0") int offset,
    @QueryParam(ApiParams.LIMIT) @DefaultValue(value = "50") int limit){
        var attendanceResponse = attendanceService.getAttendeesByAttendanceId(attendanceId,offset,limit);
        return Response.ok(attendanceResponse).build();
    }

    @GET
    @Path(ApiPath.MEETING_ID_PATH)
    public Response getAttendanceByMeetingId(@PathParam(ApiParams.MEETING_ID) Long meetingId){
        var attendanceResponse = attendanceService.getAttendanceByMeetingId(meetingId);
        return Response.ok(attendanceResponse).build();
    }
}
