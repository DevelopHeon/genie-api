package com.hh.study.genieapi.album.service;

import com.github.pagehelper.PageHelper;
import com.hh.study.genieapi.album.dto.AlbumDto;
import com.hh.study.genieapi.album.dto.MusicDto;
import com.hh.study.genieapi.album.mapper.AlbumMapper;
import com.hh.study.genieapi.common.dto.SearchDto;
import com.hh.study.genieapi.common.error.AlbumNotFoundException;
import com.hh.study.genieapi.album.entity.Album;
import com.hh.study.genieapi.artist.entity.Artist;
import com.hh.study.genieapi.album.entity.Music;
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
    public List<Album> findAll(SearchDto albumSearchDto) {
        PageHelper.startPage(albumSearchDto.getPageNum(), albumSearchDto.getPageOption());
        return albumMapper.findAll(albumSearchDto.getSearchParam());
    }

    public int createAlbums(AlbumDto albumDto) {

        Album album = modelMapper.map(albumDto, Album.class);
        albumMapper.createAlbums(album);

        if(albumDto.getMusicDtoList() != null){
            List<Music> musicList = musicListBilled(albumDto, album.getAlbumId());
            albumMapper.insertMusics(musicList);
        }
        int result = album.getAlbumId();
        return result;
    }

    @Transactional(readOnly = true)
    public List<Artist> searchArtist(SearchDto artistSerachDto) {
        PageHelper.startPage(artistSerachDto.getPageNum(), artistSerachDto.getPageOption());
        return albumMapper.searchArtist(artistSerachDto.getSearchParam());
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

    public int updateAlbums(AlbumDto albumDto, Integer id) {
        findById(id);
        Album album = modelMapper.map(albumDto, Album.class);
        album.setAlbumId(id);
        int result = albumMapper.updateAlbums(album);

        if(albumDto.getMusicDtoList() != null){
            albumMapper.deleteMusics(id);
            List<Music> musicList = musicListBilled(albumDto, id);
            albumMapper.insertMusics(musicList);
        }

        return result;
    }
    public int deleteAlbums(Integer id) {
        findById(id);
        int result = albumMapper.deleteAlbums(id);
        return result;
    }

    private List<Music> musicListBilled(AlbumDto albumDto, Integer id) {
        List<Music> musicList = new ArrayList<>();
        for(MusicDto m : albumDto.getMusicDtoList()){
            Music music = modelMapper.map(m, Music.class);
            music.setAlbumId(id);
            musicList.add(music);
        }
        return musicList;
    }
}