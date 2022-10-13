package com.hh.study.genieapi.album.controller;

import com.github.pagehelper.PageInfo;
import com.hh.study.genieapi.album.dto.AlbumDto;
import com.hh.study.genieapi.album.entity.Album;
import com.hh.study.genieapi.album.service.AlbumService;
import com.hh.study.genieapi.artist.entity.Artist;
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
        List<Album> content = albumService.findAll(albumSearchDto);
        PageInfo<Album> albums = new PageInfo<>(content, 10);
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/albums/artists")
    public ResponseEntity searchArtist(SearchDto artistSerachDto){
        List<Artist> content = albumService.searchArtist(artistSerachDto);
        PageInfo<Artist> artists = new PageInfo<>(content);
        return ResponseEntity.ok(artists);
    }

    @PostMapping("/albums")
    public ResponseEntity createAlbums(@RequestBody @Valid AlbumDto albumDto){
        int result = albumService.createAlbums(albumDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity getAlbums(@PathVariable Integer id){
        Album album = albumService.findById(id);
        return ResponseEntity.ok(album);
    }

    @PutMapping("/albums/{id}")
    public ResponseEntity updateAlbums(@RequestBody @Valid AlbumDto albumDto,
                                       @PathVariable Integer id){
        albumService.updateAlbums(albumDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/albums/{id}")
    public ResponseEntity deleteAlbums(@PathVariable Integer id){
        albumService.deleteAlbums(id);
        return ResponseEntity.ok().build();
    }
}
