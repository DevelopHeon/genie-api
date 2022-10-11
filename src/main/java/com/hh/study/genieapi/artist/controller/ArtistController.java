package com.hh.study.genieapi.artist.controller;

import com.hh.study.genieapi.artist.dto.ArtistDto;
import com.hh.study.genieapi.artist.service.ArtistService;
import com.hh.study.genieapi.entity.Artist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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
    public ResponseEntity quertArtist(@RequestParam(required = false) String searchParam,
                                    Pageable pageable){
        int count;
        List<Artist> content;
        if(searchParam != null){
            count = artistService.getSearchCount(searchParam);
            content = artistService.findSearchAll(searchParam);
        }else{
            count = artistService.getCount();
            content = artistService.findAll(pageable);
        }

        PageImpl<Artist> artists = new PageImpl<>(content, pageable, count);
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/artists/{id}")
    public Artist getArtist(@PathVariable Integer id){
        Artist artist = artistService.findById(id);
        return artist;
    }

    @PostMapping("/artists")
    public ResponseEntity createArtist(@RequestBody @Valid ArtistDto artistDto,
                                       Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        int result = artistService.save(artistDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/artists/{id}")
    public ResponseEntity updateArtist(@RequestBody @Valid ArtistDto artistDto,
                                       Errors errors,
                                       @PathVariable Integer id){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        artistService.updateArtist(artistDto, id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/artists/{id}")
    public ResponseEntity deleteArtist(@PathVariable Integer id){
        artistService.deleteArtist(id);
        return ResponseEntity.ok("아티스트 삭제 성공");
    }
}
