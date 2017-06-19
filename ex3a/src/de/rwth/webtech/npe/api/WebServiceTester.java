package de.rwth.webtech.npe.api;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class WebServiceTester {
  private Client client;
  private String REST_SERVICE_URL =
      "http://localhost:8080/NPECommentManagement/rest/CommentService/comments";

  private static final String SUCCESS_RESULT = "<result>success</result>";
  private static final String PASS = "pass";
  private static final String FAIL = "fail";

  private void init() {
    this.client = ClientBuilder.newClient();
  }

  public static void main(String[] args) {
    WebServiceTester tester = new WebServiceTester();

    tester.init();

    tester.testGetAllComments();
    tester.testGetComment();
    tester.testAddComment();
    tester.testUpdateComment();
    tester.testDeleteComment();
  }

  private void testGetAllComments() {
    GenericType<List<Comment>> list = new GenericType<List<Comment>>() {};
    List<Comment> comments =
        client.target(REST_SERVICE_URL)
        .request(MediaType.APPLICATION_XML)
        .get(list);
    String result = PASS;
    if (comments.isEmpty()) {
      result = FAIL;
    }
    System.out.println("testGetAllComments: " + result);
  }

  private void testGetComment() {
    Comment sample = new Comment();
    sample.setId(1);
    Comment user = (Comment) client
        .target(REST_SERVICE_URL)
        .path("/{id}")
        .resolveTemplate("id", sample.getId())
        .request(MediaType.APPLICATION_XML)
        .get(Comment.class);
    String result = FAIL;
    if (user != null && sample.getId() == user.getId()) {
      result = PASS;
    }
    System.out.println("testGetComment: " + result);
  }

  private void testAddComment() {
    Form form = new Form();
    form.param("id", "2");
    form.param("author", "Tester");
    form.param("message", "This is testing message");
    form.param("timestamp", "2017-06-19T21:00:00");
    String callResult = client
        .target(REST_SERVICE_URL)
        .request(MediaType.APPLICATION_XML)
        .post(Entity.entity(form,
            MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
    String result = FAIL;
    if (SUCCESS_RESULT.equals(callResult)) {
      result = PASS;
    }
    System.out.println("testAddComment: " + result);
  }

  private void testUpdateComment() {
    Form form = new Form();
    form.param("id", "1");
    form.param("author", "Tester");
    form.param("message", "This is testing message");
    form.param("timestamp", "2017-06-19T21:00:00");
    String callResult = client
        .target(REST_SERVICE_URL)
        .request(MediaType.APPLICATION_XML)
        .put(Entity.entity(form,
            MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
    String result = FAIL;
    if (SUCCESS_RESULT.equals(callResult)) {
      result = PASS;
    }
    System.out.println("testUpdateComment: " + result);

  }

  private void testDeleteComment() {
    String callResult = client
        .target(REST_SERVICE_URL)
        .path("/{id}")
        .resolveTemplate("id", 2)
        .request(MediaType.APPLICATION_XML)
        .delete(String.class);
    String result = FAIL;
    if (SUCCESS_RESULT.equals(callResult)) {
      result = PASS;
    }
    System.out.println("testDeleteComment: " + result);
  }

}
