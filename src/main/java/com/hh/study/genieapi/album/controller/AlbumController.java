package com.hh.study.genieapi.album.controller;

import com.hh.study.genieapi.album.dto.AlbumDto;
import com.hh.study.genieapi.album.service.AlbumService;
import com.hh.study.genieapi.artist.service.ArtistService;
import com.hh.study.genieapi.entity.Album;
import com.hh.study.genieapi.entity.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/genie")
@RestController
public class AlbumController {

    private final AlbumService albumService;
    private final ArtistService artistService;

    @GetMapping("/albums")
    public ResponseEntity quertAlbums(){
        int count = albumService.getCount();
        List<Album> content = albumService.findAll();

        return ResponseEntity.ok(content);
    }

    @GetMapping("/albums/artist")
    public ResponseEntity searchArtist(@RequestParam String artistName){
        return null;
    }

    @PostMapping("/albums")
    public ResponseEntity createAlbums(@RequestBody @Valid AlbumDto albumDto,
                                       Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        albumService.createAlbums(albumDto);
        return ResponseEntity.ok("앨범 등록 성공");
    }
}
