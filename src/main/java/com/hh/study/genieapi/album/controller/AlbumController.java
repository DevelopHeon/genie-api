package com.hh.study.genieapi.album.controller;

import com.github.pagehelper.PageInfo;
import com.hh.study.genieapi.album.dto.AlbumDto;
import com.hh.study.genieapi.album.service.AlbumService;
import com.hh.study.genieapi.entity.Album;
import com.hh.study.genieapi.entity.Artist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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
    public ResponseEntity quertAlbums(@RequestParam(required = false) String albumSearchParam,
                                      @RequestParam(required = false, defaultValue = "1") int pageNum){
        List<Album> content = albumService.findAll(albumSearchParam, pageNum);
        PageInfo<Album> albums = new PageInfo<>(content, 10);
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/albums/artist")
    public ResponseEntity searchArtist(@RequestParam(required = false) String searchParam,
                                       @RequestParam(required = false, defaultValue = "1") int pageNum,
                                       @RequestParam(required = false, defaultValue = "5") int pageOption){
        List<Artist> content = albumService.searchArtist(searchParam, pageNum, pageOption);
        PageInfo<Artist> artists = new PageInfo<>(content);
        return ResponseEntity.ok(artists);
    }

    @PostMapping("/albums")
    public ResponseEntity createAlbums(@RequestBody @Valid AlbumDto albumDto,
                                       Errors errors){
        log.info("album : " + albumDto.toString());
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        albumService.createAlbums(albumDto);
        return ResponseEntity.ok("앨범 등록 성공");
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity getAlbums(@PathVariable Integer id){
        Album album = albumService.findById(id);
        return ResponseEntity.ok(album);
    }

    @PutMapping("/albums/{id}")
    public ResponseEntity updateAlbums(@RequestBody @Valid AlbumDto albumDto,
                                       Errors errors,
                                       @PathVariable Integer id){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        albumService.updateAlbums(albumDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/albums/{id}")
    public ResponseEntity deleteAlbums(@PathVariable Integer id){
        albumService.deleteAlbums(id);
        return ResponseEntity.ok("앨범 삭제 성공");
    }
}
