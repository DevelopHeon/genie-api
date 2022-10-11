package com.hh.study.genieapi.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder @ToString
public class Music {

    private Integer musicId;
    private Integer albumId;
    private Integer orders;
    private String musicTitle;
    private String playTime;
    private Boolean status;
}
