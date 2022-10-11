package com.hh.study.genieapi.artist.repository;

import com.hh.study.genieapi.entity.Artist;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArtistMapper {
    List<Artist> findAll(String searchParam);

    int save(Artist artistDto);

    Optional<Artist> findById(Integer id);

    void updateArtist(Artist updateArtist);

    void deleteArtist(Integer id);

}
