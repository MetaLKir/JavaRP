package telran.forum.dao;

import telran.forum.model.Post;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class ForumImpl implements Forum{
    Post[] posts;
    int size;

    private Comparator<Post> cmp = (p1,p2)->Arrays.compare(p1.getAuthor().toCharArray(),p2.getAuthor().toCharArray());

    public ForumImpl(int capacity) {
        posts = new Post[capacity];
    }

    @Override
    public boolean addPost(Post post) {

        if (size == posts.length || post == null
                || getPostById(post.getPostId()) != null) {
            return false;
        }
        int index = Arrays.binarySearch(posts, 0, size, post,cmp);
        index = index < 0 ? -index - 1 : index;
        System.arraycopy(posts, index, posts, index + 1, size - index);
        posts[index] = post;
        size++;
        return true;

    }

    @Override
    public Post removePost(int postId) {

        for (int i = 0; i < size; i++) {
            if (posts[i].getPostId() == postId) {
                Post res = posts[postId];
                System.arraycopy(posts, i + 1, posts, i, size - i - 1);
                posts[--size] = null;
                return res;
            }
        }

        return null;
    }

    @Override
    public boolean updatePost(int postId, String content) {

        Post post = getPostById(postId);
        if(post != null){
            post.setContent(content);
            return true;
        }

        return false;
    }

    @Override
    public Post getPostById(int postId) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getPostId() == postId)
                return posts[i];
        }

        //if(postId >= 0 && postId < size)return posts[postId];
        return null;
    }

    @Override
    public Post[] getPostsByAuthor(String author) {
        Post pattern = new Post(0, null, author, null);
        int from = Arrays.binarySearch(posts, pattern,cmp);
        while (from >= 0) {
            from = Arrays.binarySearch(posts, 0, from, pattern, cmp);
        }
        from = -from - 1;

        int to = Arrays.binarySearch(posts, pattern, cmp);
        while (to >= 0) {
            to = Arrays.binarySearch(posts, to + 1, size, pattern, cmp);
        }
        to = -to - 1;
        return Arrays.copyOfRange(posts, from, to);
    }

    @Override
    public Post[] getPostsByAuthor(String author, LocalDate dateFrom, LocalDate dateTo) {
        Post[] authorPost = getPostsByAuthor(author);

        return getPostsByPredicate(authorPost,(Post p)-> {return
                ( p.getDate().compareTo(dateFrom.atStartOfDay())>=0 &&
                        p.getDate().compareTo(dateFrom.atStartOfDay())<0);});
    }


    private Post[] getPostsByPredicate( Post[] array, Predicate<Post> predicate) {
        Post[] res = new Post[size];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (predicate.test(array[i])) {
                res[j++] = array[i];
            }
        }
        return Arrays.copyOf(res, j);
    }



    @Override
    public int size() {
        return size;
    }
}
