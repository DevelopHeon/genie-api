package com.hh.study.genieapi.album.controller;


import com.hh.study.common.BaseTest;
import com.hh.study.genieapi.album.dto.AlbumForm;
import com.hh.study.genieapi.album.dto.MusicForm;
import com.hh.study.genieapi.album.service.AlbumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AlbumControllerTest extends BaseTest {

    @Autowired
    private AlbumService albumService;

    @Test
    @DisplayName("앨범 정상적으로 생성")
    void createAlbums() throws Exception{
        AlbumForm albumForm = albumDtoBuilled();

        mockMvc.perform(post("/api/genie/albums")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(albumForm)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("잘못된 입력 값으로 앨범 생성")
    void createAlbums_InputWrong() throws Exception{
        AlbumForm albumForm = albumDtoBuilled();
        albumForm.setAlbumTitle(" ");

        mockMvc.perform(post("/api/genie/albums")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(albumForm)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("앨범 전체 조회")
    void findAllAlbums() throws Exception{

        mockMvc.perform(get("/api/genie/albums"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("앨범 단일 조회")
    void findById() throws Exception{

        mockMvc.perform(get("/api/genie/albums/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("albumTitle").exists())
                .andExpect(jsonPath("releaseDate").exists());
    }

    @Test
    @DisplayName("앨범 ID가 존재하지 않을 경우 조회")
    void finById_NotFoundAlbumId() throws Exception{

        mockMvc.perform(get("/api/genie/albums/{id}", "13231"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("앨범 정상적으로 수정")
    void updateAlbums() throws Exception{
        albumService.findById(1);

        AlbumForm album = AlbumForm.builder()
                .albumTitle("앨범 수정")
                .artistId(1)
                .genre("힙합")
                .albumExplanation("힙합앨범")
                .releaseDate(LocalDate.of(2022, 10, 16))
                .build();
        mockMvc.perform(put("/api/genie/albums/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(album)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 앨범 수정")
    void updateAlbums_AlbumNotFound() throws Exception{
        albumService.findById(1);

        AlbumForm albumForm = AlbumForm.builder()
                .albumTitle("앨범 수정")
                .artistId(1)
                .genre("힙합")
                .albumExplanation("힙합앨범")
                .releaseDate(LocalDate.of(2022, 10, 16))
                .build();
        mockMvc.perform(put("/api/genie/albums/{id}", "152342")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(albumForm)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("잘못된 입력 값으로 앨범 수정")
    void updateAlbums_InputWrong() throws Exception{
        AlbumForm albumForm = AlbumForm.builder().build();

        mockMvc.perform(put("/api/genie/albums/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(albumForm)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("앨범 정상적으로 삭제")
    void deleteAlbums() throws Exception{
        AlbumForm albumForm = albumDtoBuilled();
        int id = albumService.save(albumForm);

        mockMvc.perform(delete("/api/genie/albums/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("앨범 ID가 존재하지 않을 경우 삭제")
    void deleteAlbums_NotFound() throws Exception{
        mockMvc.perform(delete("/api/genie/albums/{id}", "1111"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    private static AlbumForm albumDtoBuilled() {
        return AlbumForm.builder()
                .artistId(1)
                .albumTitle("앨범 제목 테스트")
                .releaseDate(LocalDate.parse("2022-10-20"))
                .genre("힙합")
                .albumExplanation("")
                .musicFormList(musicDtoBuilled())
                .build();
    }

    private static List<MusicForm> musicDtoBuilled(){
        List<MusicForm> musicFormList = new ArrayList<>();
        for(int i=0; i<=5; i++){
            MusicForm musicForm = MusicForm.builder()
                    .orders(i)
                    .musicTitle("음원명 " + i)
                    .playTime("03:31")
                    .status(true)
                    .build();
            musicFormList.add(musicForm);
        }
        return musicFormList;
    }
}