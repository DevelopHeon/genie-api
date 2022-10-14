package com.hh.study.genieapi.album.controller;


import com.hh.study.common.BaseTest;
import com.hh.study.genieapi.album.dto.AlbumForm;
import com.hh.study.genieapi.album.dto.MusicForm;
import com.hh.study.genieapi.album.service.AlbumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AlbumControllerTest extends BaseTest {

    @Autowired
    private AlbumService albumService;

    @Test
    @DisplayName("앨범 정상적으로 생성")
    void createAlbums() throws Exception{
        AlbumForm albumForm = albumDtoBuilled();

    }

    @Test
    @DisplayName("앨범 전체 조회")
    void findAllAlbums() throws Exception{

        mockMvc.perform(get("/api/genie/albums"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private static AlbumForm albumDtoBuilled() {
        return AlbumForm.builder()
                .artistId(1)
                .albumTitle("앨범 제목 테스트")
                .releaseDate(LocalDate.parse("2022-10-20"))
                .genre("힙합")
                .albumExplanation("")
                .build();
    }

    private static MusicForm musicDtoBuilled(int index){
        return MusicForm.builder()
                .orders(index)
                .musicTitle("음원 제목")
                .playTime("03:31")
                .status(true)
                .build();
    }
}