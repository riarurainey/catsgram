package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.dao.PostDao;
import ru.yandex.practicum.catsgram.exceptions.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostDao postDao;
    private final UserService userService;


    @Autowired
    public PostService(PostDao postDao, UserService userService) {
        this.postDao = postDao;
        this.userService = userService;
    }

    public Collection<Post> findPostsByUser(String userId) {
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        return postDao.findPostsByUser(user);
    }


    public Collection<Post> findPostsByUser(String authorId, Integer size, String sort) {
        return findPostsByUser(authorId).stream().sorted((p1, p2) -> {
                    int comp = p1.getCreationDate().compareTo(p2.getCreationDate());
                    if (("desc").equals(sort)) {
                        comp = -1 * comp;
                    }
                    return comp;
                })
                .limit(size)
                .collect(Collectors.toList());
    }

}

