package ru.yandex.practicum.catsgram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.exceptions.IncorrectParameterException;
import ru.yandex.practicum.catsgram.model.FeedParams;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.List;

import static ru.yandex.practicum.catsgram.Constants.SORTS;

@RestController

public class PostFeedController {
    private final PostService postService;

    public PostFeedController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/feed/friends")
    List<Post> getFriendsFeed(@RequestBody String params) {
        ObjectMapper mapper = new ObjectMapper();
        FeedParams friendsParams;

        try {
            String paramsForString = mapper.readValue(params, String.class);
            friendsParams = mapper.readValue(paramsForString, FeedParams.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if (!SORTS.contains(friendsParams.getSort())) {
            throw new IncorrectParameterException("sort");
        }
        if (friendsParams.getSize() == null || friendsParams.getSize() < 0) {
            throw new IncorrectParameterException("size");
        }
        if (friendsParams.getFriendsEmails().isEmpty()) {
            throw new IncorrectParameterException("friendsEmails");
        }

        List<Post> result = new ArrayList<>();
        for (String friend : friendsParams.getFriendsEmails()) {
            result.addAll(postService.findPostsByUser(friendsParams.getSort(), friendsParams.getSize(), friend));
        }
        return result;

    }

}
