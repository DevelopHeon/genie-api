package com.hh.study.genieapi.album.repository;

import com.hh.study.genieapi.album.dto.MusicDto;
import com.hh.study.genieapi.entity.Album;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlbumMapper {
    int getCount();

    List<Album> findAll();

    void createAlbums(Album album);

    void insertMusics(List<MusicDto> musicList);
}
