package telran.forum.dao;

import telran.forum.model.Post;

import java.time.LocalDate;

public interface Forum {
    boolean addPost(Post post);

    Post removePost(int postId);

    boolean updatePost(int postId, String content);

    Post getPostById(int postId);

    Post[] getPostsByAuthor(String author);

    Post[] getPostsByAuthor(String author, LocalDate dateFrom, LocalDate dateTo);

    Post[] getPostsByLikes(int minLikes, int maxLikes);

    int size();
}
