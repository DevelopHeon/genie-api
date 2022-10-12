package com.hh.study.genieapi.album.repository;

import com.hh.study.genieapi.entity.Album;
import com.hh.study.genieapi.entity.Artist;
import com.hh.study.genieapi.entity.Music;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AlbumMapper {
    List<Album> findAll(String albumSearchParam);

    void createAlbums(Album album);

    void insertMusics(List<Music> musicList);

    List<Artist> searchArtist(String searchParam);

    Optional<Album> findByIdToAlbum(Integer id);

    List<Music> findByIdToMusic(Integer id);

    void updateAlbums(Album album);

    void updateMusics(List<Music> music);

    void deleteAlbums(Integer id);
}
