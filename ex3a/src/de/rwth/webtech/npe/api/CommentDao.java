package de.rwth.webtech.npe.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
  public List<Comment> cmtList = new ArrayList<Comment>();
  private static CommentDao instance = new CommentDao();

  public static CommentDao getInstance() {
    return instance;
  }

  @SuppressWarnings("unchecked")
  public CommentDao() {
    try {
      File file = new File("Comments.dat");
      if (!file.exists()) {
        cmtList.add(new Comment(1, "A", "Test", "2017-06-19T20:00:00"));

        saveCommentList(cmtList);
      } else {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        cmtList = (List<Comment>) ois.readObject();
        ois.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private void saveCommentList(List<Comment> cmtList2) {
    try {
      File file = new File("Comments.dat");
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(cmtList);
      oos.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Comment> getAllComments() {
    return cmtList;
  }

  public Comment getComment(int id) {
    for (Comment c : getAllComments()) {
      if (c.getId() == id) {
        return c;
      }
    }
    return null;
  }

  public int addComment(Comment cmt) {
    if (getComment(cmt.getId()) == null) {
      cmtList.add(cmt);
      saveCommentList(cmtList);
      return 1;
    }
    return 0;
  }

  public int updateComment(Comment cmt) {
    Comment c = getComment(cmt.getId());
    if (c != null) {
      int index = cmtList.indexOf(c);
      cmtList.set(index, cmt);
      saveCommentList(cmtList);
      return 1;
    }
    return 0;
  }

  public int deleteComment(int id) {
    Comment c = getComment(id);
    if (c != null) {
      int index = cmtList.indexOf(c);
      cmtList.remove(index);
      saveCommentList(cmtList);
      return 1;
    }
    return 0;
  }
}
