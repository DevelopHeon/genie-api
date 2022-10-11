package com.hh.study.genieapi.album.service;

import com.hh.study.genieapi.album.dto.AlbumDto;
import com.hh.study.genieapi.album.dto.MusicDto;
import com.hh.study.genieapi.album.repository.AlbumMapper;
import com.hh.study.genieapi.artist.service.ArtistService;
import com.hh.study.genieapi.entity.Album;
import com.hh.study.genieapi.entity.Artist;
import com.hh.study.genieapi.entity.Music;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Service
public class AlbumService {
    private final ArtistService artistService;
    private final AlbumMapper albumMapper;
    private final ModelMapper modelMapper;

    public int getCount() {
        return albumMapper.getCount();
    }

    public List<Album> findAll() {
        return albumMapper.findAll();
    }

    public void createAlbums(AlbumDto albumDto) {
        Album album = modelMapper.map(albumDto, Album.class);
        log.info("album : " + album.toString());
        albumMapper.createAlbums(album);
        log.info("albumId : " + album.getAlbumId());

        List<MusicDto> musicList = albumDto.getMusicDtos();
        for(MusicDto m : musicList){
            List<Music> result = new ArrayList<>();
            Music music = modelMapper.map(m, Music.class);
            music.setAlbumId(album.getAlbumId());
            result.add(music);
        }
        albumMapper.insertMusics(musicList);
    }

    public List<Artist> searchArtist(String searchParam, int pageNum) {
        return artistService.findAll(searchParam, pageNum);
    }
}
