package com.hh.study.genieapi.album.service;

import com.github.pagehelper.PageHelper;
import com.hh.study.genieapi.album.dto.AlbumDto;
import com.hh.study.genieapi.album.dto.MusicDto;
import com.hh.study.genieapi.album.repository.AlbumMapper;
import com.hh.study.genieapi.common.error.AlbumNotFoundException;
import com.hh.study.genieapi.entity.Album;
import com.hh.study.genieapi.entity.Artist;
import com.hh.study.genieapi.entity.Music;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class AlbumService {
    private final AlbumMapper albumMapper;
    private final ModelMapper modelMapper;
    private final static int PAGE_SIZE=5;

    @Transactional(readOnly = true)
    public List<Album> findAll(String albumSearchParam, int pageNum) {
        PageHelper.startPage(pageNum, PAGE_SIZE);
        return albumMapper.findAll(albumSearchParam);
    }

    public void createAlbums(AlbumDto albumDto) {
        Album album = modelMapper.map(albumDto, Album.class);
        albumMapper.createAlbums(album);

        if(albumDto.getMusicDtoList() != null){
            List<Music> musicList = new ArrayList<>();

            for(MusicDto m : albumDto.getMusicDtoList()){
                Music music = modelMapper.map(m, Music.class);
                music.setAlbumId(album.getAlbumId());
                musicList.add(music);
            }
            albumMapper.insertMusics(musicList);
        }
    }

    @Transactional(readOnly = true)
    public List<Artist> searchArtist(String searchParam, int pageNum, int pageOption) {
        PageHelper.startPage(pageNum, pageOption);
        return albumMapper.searchArtist(searchParam);
    }

    @Transactional(readOnly = true)
    public Album findById(Integer id) {
        Optional<Album> optionalAlbum = albumMapper.findByIdToAlbum(id);
        if(!optionalAlbum.isPresent()){
            throw new AlbumNotFoundException("앨범을 찾을 수 없습니다.");
        }
        Album album = optionalAlbum.get();

        List<Music> musicList = albumMapper.findByIdToMusic(id);
        if(!musicList.isEmpty()){
            album.setMusicList(musicList);
        }
        return album;
    }

    public void updateAlbums(AlbumDto albumDto, Integer id) {
        findById(id);
        Album album = modelMapper.map(albumDto, Album.class);
        album.setAlbumId(id);
        albumMapper.updateAlbums(album);

        if(albumDto.getMusicDtoList() != null){
            List<Music> musicList = new ArrayList<>();
            for(MusicDto m : albumDto.getMusicDtoList()){
                Music music = modelMapper.map(m, Music.class);
                music.setAlbumId(id);
                musicList.add(music);
                log.info(music.toString());
            }
            albumMapper.updateMusics(musicList);
        }
    }

    public void deleteAlbums(Integer id) {
        findById(id);
        albumMapper.deleteAlbums(id);
    }
}