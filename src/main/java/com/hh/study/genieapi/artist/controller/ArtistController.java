package com.hh.study.genieapi.artist.controller;

import com.github.pagehelper.PageInfo;
import com.hh.study.genieapi.artist.dto.ArtistDto;
import com.hh.study.genieapi.artist.entity.Artist;
import com.hh.study.genieapi.artist.service.ArtistService;
import com.hh.study.genieapi.common.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/genie")
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping("/artists")
    public ResponseEntity queryArtist(SearchDto artistSearch){
        List<Artist> content = artistService.findAll(artistSearch);

        PageInfo<Artist> artists = new PageInfo<>(content, 10);
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity getArtist(@PathVariable Integer id){
        Artist artist = artistService.findById(id);
        return ResponseEntity.ok(artist);
    }

    @PostMapping("/artists")
    public ResponseEntity createArtist(@RequestBody @Valid ArtistDto artistDto){
        int result = artistService.save(artistDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/artists/{id}")
    public ResponseEntity updateArtist(@RequestBody @Valid ArtistDto artistDto,
                                       @PathVariable Integer id){
        int result = artistService.updateArtist(artistDto, id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/artists/{id}")
    public ResponseEntity deleteArtist(@PathVariable Integer id){
        int result = artistService.deleteArtist(id);
        return ResponseEntity.ok(result);
    }
}
