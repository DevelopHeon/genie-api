package com.hh.study.genieapi.album.service;

import com.github.pagehelper.PageHelper;
import com.hh.study.genieapi.album.dto.*;
import com.hh.study.genieapi.album.entity.Album;
import com.hh.study.genieapi.album.entity.Music;
import com.hh.study.genieapi.album.mapper.AlbumMapper;
import com.hh.study.genieapi.common.dto.SearchDto;
import com.hh.study.genieapi.common.error.AlbumNotFoundException;
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

    @Transactional(readOnly = true)
    public List<AlbumList> findAll(SearchDto albumSearchDto) {
        PageHelper.startPage(albumSearchDto.getPageNum(), albumSearchDto.getPageOption());
        return albumMapper.findAll(albumSearchDto.getKeyword());
    }

    public int createAlbums(AlbumForm albumForm) {

        Album album = modelMapper.map(albumForm, Album.class);
        albumMapper.createAlbums(album);

        if(albumForm.getMusicFormList() != null){
            log.info("musicList" + albumForm.getMusicFormList().toString());
            List<Music> musicList = musicListBilled(albumForm, album.getAlbumId());
            albumMapper.insertMusics(musicList);
        }
        int result = album.getAlbumId();
        return result;
    }

    @Transactional(readOnly = true)
    public List<SerachArtistList> searchArtist(SearchDto artistSerachDto) {
        PageHelper.startPage(artistSerachDto.getPageNum(), artistSerachDto.getPageOption());
        return albumMapper.searchArtist(artistSerachDto.getKeyword());
    }

    @Transactional(readOnly = true)
    public AlbumDetail findById(Integer id) {
        Optional<AlbumDetail> optionalAlbum = albumMapper.findByIdToAlbum(id);
        if(!optionalAlbum.isPresent()){
            throw new AlbumNotFoundException("앨범을 찾을 수 없습니다.");
        }
        AlbumDetail album = optionalAlbum.get();

        List<MusicDetail> musicList = albumMapper.findByIdToMusic(id);
        if(!musicList.isEmpty()){
            album.setMusicList(musicList);
        }
        return album;
    }

    public int updateAlbums(AlbumForm albumForm, Integer id) {
        findById(id);
        Album album = modelMapper.map(albumForm, Album.class);
        album.setAlbumId(id);
        int result = albumMapper.updateAlbums(album);

        if(albumForm.getMusicFormList() != null){
            albumMapper.deleteMusics(id);
            List<Music> musicList = musicListBilled(albumForm, id);
            albumMapper.insertMusics(musicList);
        }

        return result;
    }
    public int deleteAlbums(Integer id) {
        findById(id);
        int result = albumMapper.deleteAlbums(id);
        return result;
    }

    private List<Music> musicListBilled(AlbumForm albumForm, Integer id) {
        List<Music> musicList = new ArrayList<>();
        for(MusicForm m : albumForm.getMusicFormList()){
            Music music = modelMapper.map(m, Music.class);
            music.setAlbumId(id);
            musicList.add(music);
        }
        return musicList;
    }
}