package com.hh.study.genieapi.artist.controller;

import com.github.pagehelper.PageInfo;
import com.hh.study.genieapi.artist.dto.ArtistDetail;
import com.hh.study.genieapi.artist.dto.ArtistForm;
import com.hh.study.genieapi.artist.dto.ArtistList;
import com.hh.study.genieapi.artist.service.ArtistService;
import com.hh.study.genieapi.common.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/genie")
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping("/artists")
    public ResponseEntity queryArtist(SearchDto searchDto){
        PageInfo<ArtistList> artistsList = artistService.findAll(searchDto);
        return ResponseEntity.ok(artistsList);
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity getArtist(@PathVariable Integer id){
        ArtistDetail artist = artistService.findById(id);
        return ResponseEntity.ok(artist);
    }

    @PostMapping("/artists")
    public ResponseEntity createArtist(@RequestBody @Valid ArtistForm artistForm){
        int result = artistService.save(artistForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/artists/{id}")
    public ResponseEntity updateArtist(@RequestBody @Valid ArtistForm artistForm,
                                       @PathVariable Integer id){
        int result = artistService.updateArtist(artistForm, id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/artists/{id}")
    public ResponseEntity deleteArtist(@PathVariable Integer id){
        int result = artistService.deleteArtist(id);
        return ResponseEntity.ok(result);
    }
}
