package com.flexisaf.resources;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flexisaf.adapter.constants.PrayerStatus;
import com.flexisaf.common.ApiParams;
import com.flexisaf.common.ApiPath;
import com.flexisaf.requests.PrayerRequest;
import com.flexisaf.services.CommentService;
import com.flexisaf.services.PrayerService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@Path(ApiPath.PRAYERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class PrayerResource {
    @Inject
    PrayerService prayerService;

    @Inject
    CommentService commentService;

    @POST
    public Response createPrayer(@Valid @NotBlank  @HeaderParam("X-ORG-ID") String organizationId,
                                 @Valid @NotNull PrayerRequest prayerRequest){
        var prayerResponse = prayerService.create(prayerRequest,organizationId);
            return Response.ok(prayerResponse).build();
    }

    @GET
    @Path(ApiPath.PRAYER_ID_PATH)
    public Response getPrayerById(
            @Valid @NotBlank  @HeaderParam("X-ORG-ID") String organizationId,
            @Valid @PathParam(ApiParams.PRAYER_ID) Long prayerId){
        var prayerResponse = prayerService.getById(prayerId);

        return Response
                .ok(prayerResponse)
                .build();
    }

    @GET
    public Response getPrayers(
              @NotBlank @HeaderParam("X-ORG-ID") String organizationId,
              @QueryParam(ApiParams.OFFSET) @DefaultValue(value = "0") int offset,
              @QueryParam(ApiParams.LIMIT) @DefaultValue(value = "50") int limit,
              @QueryParam(ApiParams.STATUS) PrayerStatus status,
              @QueryParam(ApiParams.TYPE) String type,
              @QueryParam(ApiParams.MEETING_ID) String meetingId,
              @QueryParam(ApiParams.FACULTY_ID) String facultyId,
              @QueryParam(ApiParams.DEPARTMENT_ID) String departmentId,
              @QueryParam(ApiParams.CATEGORY) String category)
    {
        var prayerResponse = prayerService.getPrayers(organizationId,offset,limit,status,type,meetingId,facultyId,departmentId,category);
        return Response.ok(prayerResponse).build();
    }

    @PUT
    @Path(ApiPath.PRAYER_ID_PATH)
    public Response updatePrayer(
            @Valid @NotBlank @HeaderParam("X-ORG-ID") String organizationId,
            @Valid @PathParam(ApiParams.PRAYER_ID) Long prayerId,
            PrayerRequest prayerRequest){
        var prayerResponse = prayerService.update(prayerRequest, prayerId);
        return Response
                .ok(prayerResponse)
                .build();
    }

    @DELETE
    @Path(ApiPath.PRAYER_ID_PATH)
    public Response deletePrayer(
            @Valid @NotBlank @HeaderParam("X-ORG-ID") String organizationId, @Valid @PathParam(ApiParams.PRAYER_ID) Long prayerId){
        prayerService.delete(prayerId);
        return Response
                .noContent()
                .build();
    }
}
