package telran.forum.dao;

import telran.forum.model.Post;

import java.time.LocalDate;

public class ForumImpl implements Forum{
    Post[] posts;
    int size;

    public ForumImpl(int capacity) {
        posts = new Post[capacity];
    }

    @Override
    public boolean addPost(Post post) {
        return false;
    }

    @Override
    public Post removePost(int postId) {
        return null;
    }

    @Override
    public boolean updatePost(int postId, String content) {
        return false;
    }

    @Override
    public Post getPostById(int postId) {
        return null;
    }

    @Override
    public Post[] getPostsByAuthor(String author) {
        return new Post[0];
    }

    @Override
    public Post[] getPostsByAuthor(String author, LocalDate dateFrom, LocalDate dateTo) {
        return new Post[0];
    }

    @Override
    public int size() {
        return size;
    }
}
