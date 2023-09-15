package com.meet5.kafkaconsumer.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileLikes {

    private Long likeId;
    private Long likerId;
    private Long liked_profile_id;
    private Date likedTimestamp;
    private int likes = 0;

    public int setLikes(int like) {
        return this.likes += like;
    }
}
