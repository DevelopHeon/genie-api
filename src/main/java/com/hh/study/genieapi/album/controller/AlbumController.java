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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/genie")
@Slf4j
@RestController
public class AlbumController {
    private final AlbumService albumService;
    @GetMapping("/albums")
    public ResponseEntity queryAlbums(SearchDto searchDto){
        PageInfo<AlbumList> albumLists = albumService.findAll(searchDto);
        log.info("git-flow feature branch 생성");
        log.info("merge commit 테스트");
        return ResponseEntity.ok(albumLists);
    }

    @GetMapping("/albums/artists")
    public ResponseEntity searchArtists(SearchDto searchDto){
        PageInfo<SerachArtistList> artistLists = albumService.searchArtist(searchDto);
        log.info("원격 저장소에 feature 생성");
        log.info("stash 테스트");
        return ResponseEntity.ok(artistLists);
    }

    @PostMapping("/albums")
    public ResponseEntity createAlbums(@RequestBody @Valid AlbumForm albumForm){
        int result = albumService.save(albumForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity getAlbums(@PathVariable Integer id){
        AlbumDetail album = albumService.getAlbums(id);
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
