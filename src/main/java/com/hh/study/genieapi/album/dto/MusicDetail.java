package com.hh.study.genieapi.album.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicDetail {
    private Integer musicId;
    private Integer orders;
    private String musicTitle;
    private String playTime;
    private Boolean status;
}
