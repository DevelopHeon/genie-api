package com.hh.study.genieapi.album.mapper;

import com.hh.study.genieapi.album.entity.Album;
import com.hh.study.genieapi.artist.entity.Artist;
import com.hh.study.genieapi.album.entity.Music;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AlbumMapper {
    List<Album> findAll(String albumSearchParam);

    int createAlbums(Album album);

    void insertMusics(List<Music> musicList);

    List<Artist> searchArtist(String searchParam);

    Optional<Album> findByIdToAlbum(Integer id);

    List<Music> findByIdToMusic(Integer id);

    int updateAlbums(Album album);

    int deleteAlbums(Integer id);

    void deleteMusics(Integer id);
}
