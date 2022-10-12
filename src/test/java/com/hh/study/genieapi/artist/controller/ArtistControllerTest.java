package com.hh.study.genieapi.artist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hh.study.genieapi.artist.repository.ArtistMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ArtistControllerTest {

    @Autowired
    private ArtistMapper artistMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("아티스트 전체 조회")
    void quertArtists() throws Exception{

        mockMvc.perform(get("/api/genie/artists"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("아티스트 검색 조회")
    void searchArtists() throws Exception{

        mockMvc.perform(get("/api/genie/artists")
                .param("searchParam", "아이유")
                .param("pageNum", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}