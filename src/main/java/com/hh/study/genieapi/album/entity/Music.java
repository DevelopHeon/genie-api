package com.hh.study.genieapi.album.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder @ToString
public class Music {

    private Integer orders;
    private Integer musicId;
    private Integer albumId;
    private String musicTitle;
    private String playTime;
    private Boolean status;
}
