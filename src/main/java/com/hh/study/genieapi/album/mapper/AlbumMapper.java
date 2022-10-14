package com.hh.study.genieapi.album.mapper;

import com.hh.study.genieapi.album.dto.AlbumDetail;
import com.hh.study.genieapi.album.dto.AlbumList;
import com.hh.study.genieapi.album.dto.MusicDetail;
import com.hh.study.genieapi.album.dto.SerachArtistList;
import com.hh.study.genieapi.album.entity.Album;
import com.hh.study.genieapi.album.entity.Music;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AlbumMapper {
    List<AlbumList> findAll(String albumSearchParam);

    int createAlbums(Album album);

    void insertMusics(List<Music> musicList);

    List<SerachArtistList> searchArtist(String searchParam);

    Optional<AlbumDetail> findByIdToAlbum(Integer id);

    List<MusicDetail> findByIdToMusic(Integer id);

    int updateAlbums(Album album);

    int deleteAlbums(Integer id);

    void deleteMusics(Integer id);
}
