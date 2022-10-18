package com.hh.study.genieapi.album.mapper;

import com.hh.study.genieapi.album.dto.AlbumDetail;
import com.hh.study.genieapi.album.dto.AlbumList;
import com.hh.study.genieapi.album.dto.MusicDetail;
import com.hh.study.genieapi.album.dto.SerachArtistList;
import com.hh.study.genieapi.album.entity.Album;
import com.hh.study.genieapi.album.entity.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AlbumMapper {
    List<AlbumList> findAll(String keyword);

    int saveAlbums(Album album);

    void saveMusics(List<Music> musicList);

    List<SerachArtistList> searchArtist(String keyword);

    Optional<AlbumDetail> findByIdAlbumDetail(Integer id);

    int updateAlbums(@Param("album")Album album, @Param("id")Integer id);

    int deleteAlbums(Integer id);

    void deleteMusics(Integer id);
}
