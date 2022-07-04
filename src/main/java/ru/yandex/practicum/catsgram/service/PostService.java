package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceptions.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final UserService userService;
    private final List<Post> posts = new ArrayList<>();

    public PostService(UserService userService) {
        this.userService = userService;
    }


    public List<Post> findAll() {
        return posts;
    }

    public Post create(Post post) {

        if (userService.findUserByEmail(post.getAuthor()) == null) {
            throw new UserNotFoundException("User " + post.getAuthor() + " not found");
        }
        posts.add(post);
        return post;

    }
}
