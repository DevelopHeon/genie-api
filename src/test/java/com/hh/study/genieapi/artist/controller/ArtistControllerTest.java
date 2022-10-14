package com.hh.study.genieapi.artist.controller;


import com.hh.study.common.BaseTest;
import com.hh.study.genieapi.artist.dto.ArtistDto;
import com.hh.study.genieapi.artist.service.ArtistService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ArtistControllerTest extends BaseTest {

    @Autowired
    private ArtistService artistService;

    @Test
    @DisplayName("아티스트 전체 조회 테스트")
    void findAllArtist() throws Exception{

        mockMvc.perform(get("/api/genie/artists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("total").exists());
    }

    @Test
    @DisplayName("아티스트 총 50개 중 5개씩 2번째 페이지 조회")
    void pageArtists() throws Exception {

        IntStream.range(0, 29).forEach(i -> saveArtist());

        mockMvc.perform(get("/api/genie/artists")
                        .param("pageNum", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("total").exists())
                .andExpect(jsonPath("total").value("50"));;

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
        ArtistDto newArtistDto = buildArtistDto();

        mockMvc.perform(post("/api/genie/artists")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(newArtistDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("잘못된 값으로 아티스트 생성")
    void createArtist_BadRequest() throws Exception{
        ArtistDto newArtistDto = buildArtistDto();
        newArtistDto.setArtistName(" ");

        mockMvc.perform(post("/api/genie/artists")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(newArtistDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").exists());
    }
    @Test
    @DisplayName("빈 값으로 아티스트 생성")
    void createArtist_Input_Empty() throws Exception{
        ArtistDto newArtistDto = new ArtistDto();
        mockMvc.perform(post("/api/genie/artists")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(newArtistDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").exists());
    }

    @Test
    @DisplayName("아티스트 정상적으로 수정하기")
    void updateArtist() throws Exception{
        int id = saveArtist();
        ArtistDto newArtistDto = buildArtistDto();
        String newArtistName = "홍길동";
        newArtistDto.setArtistName(newArtistName);

        mockMvc.perform(put("/api/genie/artists/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(newArtistDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("잘못된 입력 값으로 수정")
    void updateArtist_BadRequest() throws Exception{
        int id = saveArtist();
        ArtistDto artistDto = buildArtistDto();

        artistDto.setArtistName(" ");
        artistDto.setCountry(" ");

        mockMvc.perform(put("/api/genie/artists/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(artistDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("없는 아티스트 수정")
    void updateArtist_NotFound() throws Exception{
        ArtistDto artistDto = buildArtistDto();
        String newArtistName = "홍길동";
        artistDto.setArtistName(newArtistName);

        mockMvc.perform(put("/api/genie/artists/{id}", "99999")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(artistDto)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("아티스트 정상적으로 삭제하기")
    void deleteArtist() throws Exception{
        int id = saveArtist();

        mockMvc.perform(delete("/api/genie/artists/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("없는 아티스트 삭제하기")
    void deleteArtist_NotFound() throws Exception{
        saveArtist();

        mockMvc.perform(delete("/api/genie/artists/{id}", "1231232998"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    private ArtistDto buildArtistDto() {
        return ArtistDto.builder()
                .artistName("김희헌")
                .artistBirth(LocalDate.parse("1995-07-21"))
                .agency("와이지")
                .country("한국")
                .artistExplanation("내각리 방구석 가수")
                .build();
    }
    private int saveArtist() {
        ArtistDto newArtistDto = buildArtistDto();
        int id = artistService.save(newArtistDto);
        return id;
    }

}