package telran.forum.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.forum.dao.Forum;
import telran.forum.dao.ForumImpl;
import telran.forum.model.Post;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ForumTest {
    Forum forum;
    Post[] posts = new Post[5];
    Post oneMore1;
    Post oneMore2;
    LocalDateTime now = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        forum = new ForumImpl(6);

        posts[0] = new Post(1,"1","Vova","Jopa");
        posts[1] = new Post(2,"2","Petya","Hui");
        posts[2] = new Post(3,"3","Jora","Pisdez");
        posts[3] = new Post(4,"4","Jora","Govno");
        posts[4] = new Post(5,"5","Vova","Goida");

        oneMore1 = new Post(6, "6", "Goga", "Nigger");
        oneMore2 = new Post(7, "7", "Seroja", "GayHelicopter");

        for (int i = 0; i < posts.length; i++) {
            forum.addPost(posts[i]);
        }
    }

    @Test
    void addPost() {
        // regular adding
        assertTrue(forum.addPost(oneMore1));
        assertEquals(oneMore1, forum.getPostById(oneMore1.getPostId()));
        assertEquals(6, forum.size());
        // out of capacity
        assertFalse(forum.addPost(oneMore2));
        // already added
        forum.removePost(2);
        assertFalse(forum.addPost(oneMore1));
        // add null
        assertFalse(forum.addPost(null));
    }

    @Test
    void removePost() {
        // regular remove
        assertEquals(posts[1], forum.removePost(posts[1].getPostId()));
        assertEquals(4, forum.size());
        // remove already removed
        assertNull(forum.removePost(posts[1].getPostId()));
        assertEquals(4, forum.size());
    }

    @Test
    void updatePost() {
        // regular update
        assertTrue(forum.updatePost(2, "Dobro"));
        assertEquals("Dobro", forum.getPostById(2).getContent());
        // update not existed
        assertFalse(forum.updatePost(666, "Dobro"));
    }

    @Test
    void getPostById() {
        // regular get
        assertEquals(posts[3], forum.getPostById(posts[3].getPostId()));
        // get not existed
        assertNull(forum.getPostById(666));
    }

    @Test
    void getPostsByAuthor() {
        Post[] expected = {posts[0], posts[4]}; // TODO: SORT THIS SHIT !!!!
        Comparator<Post> comp = (p1, p2) -> Integer.compare(p1.getPostId(), p2.getPostId());
        Arrays.sort(expected, comp);

        Post[] actual = forum.getPostsByAuthor("Vova");
        Arrays.sort(actual, comp);
        assertArrayEquals(expected, actual);
    }

    @Test
    void getPostsByAuthor_WithDate(){
        posts[2].setDate(LocalDateTime.of(2000,12,25,5,13));
        posts[3].setDate(LocalDateTime.of(2007,5,27,9,1));

        Post[] expected = {posts[2]};
        Post[] actual = forum.getPostsByAuthor("Jora", LocalDate.of(1999,1,1), LocalDate.of(2002, 2,2));
        assertArrayEquals(expected, actual);
    }


    @Test
    void size() {
        assertEquals(5, forum.size());

        forum.addPost(oneMore1);
        assertEquals(6, forum.size());

        forum.removePost(posts[2].getPostId());
        assertEquals(5, forum.size());
    }
}