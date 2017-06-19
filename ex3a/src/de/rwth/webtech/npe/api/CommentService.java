package de.rwth.webtech.npe.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/CommentService")
public class CommentService {
  CommentDao cmtDao = CommentDao.getInstance();

  private static final String SUCCESS_RESULT = "<result>success</result>";
  private static final String FAILURE_RESULT = "<result>failure</result>";

  @GET
  @Path("/comments")
  @Produces(MediaType.APPLICATION_XML)
  public List<Comment> getComments() {
    return cmtDao.getAllComments();
  }

  @GET
  @Path("/comments/{id}")
  @Produces(MediaType.APPLICATION_XML)
  public Comment getComment(@PathParam("id") int id) {
    return cmtDao.getComment(id);
  }

  @POST
  @Path("/comments")
  @Produces(MediaType.APPLICATION_XML)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public String createComment(@FormParam("id") int id, @FormParam("author") String author,
      @FormParam("message") String message, @FormParam("timestamp") String timestamp,
      @Context HttpServletResponse servletResponse) throws IOException {
    Comment cmt = new Comment(id, author, message, timestamp);
    int result = cmtDao.addComment(cmt);
    if (result == 1) {
      return SUCCESS_RESULT;
    }
    return FAILURE_RESULT;
  }

  @PUT
  @Path("/comments")
  @Produces(MediaType.APPLICATION_XML)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public String updateComment(@FormParam("id") int id, @FormParam("author") String author,
      @FormParam("message") String message, @FormParam("timestamp") String timestamp,
      @Context HttpServletResponse servletResponse) throws IOException {
    Comment cmt = new Comment(id, author, message, timestamp);
    int result = cmtDao.updateComment(cmt);
    if (result == 1) {
      return SUCCESS_RESULT;
    }
    return FAILURE_RESULT;
  }

  @DELETE
  @Path("/comments/{id}")
  @Produces(MediaType.APPLICATION_XML)
  public String deleteComment(@PathParam("id") int id) {
    int result = cmtDao.deleteComment(id);
    if (result == 1) {
      return SUCCESS_RESULT;
    }
    return FAILURE_RESULT;
  }
}
