package com.hh.study.genieapi.artist.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hh.study.genieapi.album.mapper.AlbumMapper;
import com.hh.study.genieapi.artist.dto.ArtistDto;
import com.hh.study.genieapi.artist.entity.Artist;
import com.hh.study.genieapi.artist.mapper.ArtistMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ArtistMapper artistMapper;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    @DisplayName("아티스트 전체 조회 테스트")
    void findAllArtist() throws Exception{

        mockMvc.perform(get("/api/genie/artists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("total").exists());
    }

    @Test
    @DisplayName("아티스트 검색 조회")
    void serachArtists() throws Exception{

        mockMvc.perform(get("/api/genie/artists")
                .param("searchParam", "아이유")
                .param("pageNum", "2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("아티스트 단일 조회")
    void getArtist() throws Exception{
        int id = saveArtist();

        mockMvc.perform(get("/api/genie/artists/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("artistName").exists())
                .andExpect(jsonPath("artistBirth").exists());
    }

    @Test
    @DisplayName("없는 아티스트 조회")
    void getArtist_NotFound() throws Exception{
        mockMvc.perform(get("/api/genie/artists/{id}", "12312"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("아티스트를 찾을 수 없습니다."));
    }

    @Test
    @DisplayName("아티스트 정상적으로 생성")
    void createArtist() throws Exception{
        Artist newArtist = buildArtist();

        mockMvc.perform(post("/api/genie/artists")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(newArtist)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("잘못된 값으로 아티스트 생성")
    void createArtist_BadRequest() throws Exception{
        Artist newArtist = buildArtist();

        mockMvc.perform(post("/api/genie/artists")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(newArtist)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").exists());
    }
    @Test
    @DisplayName("빈 값으로 아티스트 생성")
    void createArtist_Input_Empty() throws Exception{
        Artist newArtist = new Artist();
        mockMvc.perform(post("/api/genie/artists")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(newArtist)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").exists());
    }

    @Test
    @DisplayName("아티스트 정상적으로 수정하기")
    void updateArtist() throws Exception{
        int id = saveArtist();
        Artist artist = buildArtist();

        ArtistDto artistDto = modelMapper.map(artist, ArtistDto.class);
        String newArtistName = "홍길동";
        artistDto.setArtistName(newArtistName);

        mockMvc.perform(put("/api/genie/artists/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(artistDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("잘못된 입력 값으로 수정")
    void updateArtist_BadRequest() throws Exception{
        int id = saveArtist();
        Artist artist = buildArtist();

        ArtistDto artistDto = modelMapper.map(artist, ArtistDto.class);
        artistDto.setArtistName(" ");
        artistDto.setCountry(" ");

        mockMvc.perform(put("/api/genie/artists/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(artistDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    private Artist buildArtist() {
        return Artist.builder()
                .artistName("김희헌")
                .artistBirth(LocalDate.parse("1995-07-21"))
                .agency("와이지")
                .country("한국")
                .artistExplanation("내각리 방구석 가수")
                .build();
    }
    private int saveArtist() {
        Artist newArtist = buildArtist();
        artistMapper.save(newArtist);
        return newArtist.getArtistId();
    }

}