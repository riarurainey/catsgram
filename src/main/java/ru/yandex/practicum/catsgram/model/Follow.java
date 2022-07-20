package ru.yandex.practicum.catsgram.model;

public class Follow {
    private String authorId;
    private String followerId;

    public Follow(String authorId, String follower_id) {
        this.authorId = authorId;
        this.followerId = follower_id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }
}
