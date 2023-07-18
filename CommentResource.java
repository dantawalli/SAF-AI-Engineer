package com.flexisaf.resources;

import com.flexisaf.common.ApiParams;
import com.flexisaf.common.ApiPath;
import com.flexisaf.requests.CommentRequest;
import com.flexisaf.services.CommentService;
import com.flexisaf.services.PrayerService;
import lombok.RequiredArgsConstructor;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiPath.COMMENTS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class CommentResource {

    @Inject
    CommentService commentService;

    @POST
    public Response createComment(@Valid @NotNull CommentRequest commentRequest){
        var commentResponse = commentService.create(commentRequest);
        return Response.ok(commentResponse).build();
    }

    @GET
    @Path(ApiPath.COMMENT_ID_PATH)
    public Response getCommentById(
            @Valid @PathParam(ApiParams.ID) Long id){
        var commentResponse = commentService.getById(id);
        return Response
                .ok(commentResponse)
                .build();
    }

    @PUT
    @Path(ApiPath.ID_PATH)
    public Response updatePrayer(
            @Valid @PathParam(ApiParams.ID) Long id,
            CommentRequest commentRequest){
        var commentResponse = commentService.update(commentRequest, id);
        return Response
                .ok(commentResponse)
                .build();
    }

    @DELETE
    @Path(ApiPath.ID_PATH)
    public Response deletePrayer(
             @Valid @PathParam(ApiParams.ID) Long id){
        commentService.delete(id);
        return Response
                .noContent()
                .build();
    }

    @GET
    @Path(ApiPath.COMMENT_PATH)
    public Response getPrayerCommentsById(
            @Valid @PathParam(ApiParams.PRAYER_ID) Long prayerId,
            @QueryParam(ApiParams.OFFSET) @DefaultValue(value = "0") int offset,
            @QueryParam(ApiParams.LIMIT) @DefaultValue(value = "50") int limit){
        var prayerResponse = commentService.getCommentsByPrayerId(prayerId,offset,limit);
        return Response
                .ok(prayerResponse)
                .build();
    }
}