package com.hh.study.genieapi.album.controller;

import com.github.pagehelper.PageInfo;
import com.hh.study.genieapi.album.dto.AlbumDetail;
import com.hh.study.genieapi.album.dto.AlbumForm;
import com.hh.study.genieapi.album.dto.AlbumList;
import com.hh.study.genieapi.album.dto.SerachArtistList;
import com.hh.study.genieapi.album.service.AlbumService;
import com.hh.study.genieapi.common.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/genie")
@Slf4j
@RestController
public class AlbumController {
    private final AlbumService albumService;
    @GetMapping("/albums")
    public ResponseEntity quertAlbums(SearchDto albumSearchDto){
        List<AlbumList> content = albumService.findAll(albumSearchDto);
        PageInfo<AlbumList> albums = new PageInfo<>(content, 10);
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/albums/artists")
    public ResponseEntity searchArtists(SearchDto artistSerachDto){
        List<SerachArtistList> content = albumService.searchArtist(artistSerachDto);
        PageInfo<SerachArtistList> artists = new PageInfo<>(content, 10);
        return ResponseEntity.ok(artists);
    }

    @PostMapping("/albums")
    public ResponseEntity createAlbums(@RequestBody @Valid AlbumForm albumForm){
        int result = albumService.createAlbums(albumForm);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity getAlbums(@PathVariable Integer id){
        AlbumDetail album = albumService.findById(id);
        return ResponseEntity.ok(album);
    }

    @PutMapping("/albums/{id}")
    public ResponseEntity updateAlbums(@RequestBody @Valid AlbumForm albumForm,
                                       @PathVariable Integer id){
        int result = albumService.updateAlbums(albumForm, id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/albums/{id}")
    public ResponseEntity deleteAlbums(@PathVariable Integer id){
        int result = albumService.deleteAlbums(id);
        return ResponseEntity.ok(result);
    }
}
