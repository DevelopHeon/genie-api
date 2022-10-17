package com.hh.study.genieapi.album.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hh.study.genieapi.album.dto.*;
import com.hh.study.genieapi.album.entity.Album;
import com.hh.study.genieapi.album.entity.Music;
import com.hh.study.genieapi.album.mapper.AlbumMapper;
import com.hh.study.genieapi.artist.service.ArtistService;
import com.hh.study.genieapi.common.dto.SearchDto;
import com.hh.study.genieapi.common.error.AlbumNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class AlbumService {
    private final AlbumMapper albumMapper;
    private final ModelMapper modelMapper;
    private final ArtistService artistService;

    @Transactional(readOnly = true)
    public PageInfo<AlbumList> findAll(SearchDto searchDto) {
        PageHelper.startPage(searchDto.getPageNum(), searchDto.getPageSize());
        List<AlbumList> albumLists = albumMapper.findAll(searchDto.getKeyword());
        PageInfo<AlbumList> albums = new PageInfo<>(albumLists, 10);
        return albums;
    }

    public int save(AlbumForm albumForm) {
        artistService.findById(albumForm.getArtistId());

        Album album = modelMapper.map(albumForm, Album.class);
        albumMapper.saveAlbums(album);
        if(albumForm.getMusicFormList() != null){
            List<Music> musicList = musicFormToMusic(albumForm.getMusicFormList(), album.getAlbumId());
            albumMapper.saveMusics(musicList);
        }
        int result = album.getAlbumId();
        return result;
    }

    @Transactional(readOnly = true)
    public PageInfo<SerachArtistList> searchArtist(SearchDto searchDto) {
        PageHelper.startPage(searchDto.getPageNum(), searchDto.getPageSize());
        List<SerachArtistList> serachArtistLists = albumMapper.searchArtist(searchDto.getKeyword());
        PageInfo<SerachArtistList> artists = new PageInfo<>(serachArtistLists, 10);
        return artists;
    }

    @Transactional(readOnly = true)
    public AlbumDetail findById(Integer id) {
        Optional<AlbumDetail> optionalAlbumDetail = albumMapper.findByIdToAlbum(id);
        if(!optionalAlbumDetail.isPresent()){
            throw new AlbumNotFoundException("앨범을 찾을 수 없습니다.");
        }
        AlbumDetail albumDetail = optionalAlbumDetail.get();
        List<MusicDetail> musicList = albumMapper.findByIdToMusic(id);
        if(!musicList.isEmpty()){
            albumDetail.setMusicList(musicList);
        }
        return albumDetail;
    }

    public int updateAlbums(AlbumForm albumForm, Integer id) {
        artistService.findById(albumForm.getArtistId());
        findByIdCheck(id);

        Album album = modelMapper.map(albumForm, Album.class);
        int result = albumMapper.updateAlbums(album, id);

        if(albumForm.getMusicFormList() != null){
            albumMapper.deleteMusics(id);
            List<Music> musicList = musicFormToMusic(albumForm.getMusicFormList(), id);
            albumMapper.saveMusics(musicList);
        }

        return result;
    }

    public int deleteAlbums(Integer id) {
        findByIdCheck(id);
        int result = albumMapper.deleteAlbums(id);
        return result;
    }

    private void findByIdCheck(Integer id) {
        albumMapper.findByIdToAlbum(id)
                .orElseThrow(() -> new AlbumNotFoundException("앨범을 찾을 수 없습니다."));
    }

    private List<Music> musicFormToMusic(List<MusicForm> musicFormList, Integer id) {
        return musicFormList.stream()
                .map(musicForm -> {
                   Music music = modelMapper.map(musicForm, Music.class);
                   music.setAlbumId(id);
                   return music;
                }).collect(Collectors.toList());
    }
}