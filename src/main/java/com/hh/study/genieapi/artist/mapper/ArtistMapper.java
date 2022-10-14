package com.hh.study.genieapi.artist.mapper;

import com.hh.study.genieapi.artist.dto.ArtistDetail;
import com.hh.study.genieapi.artist.dto.ArtistList;
import com.hh.study.genieapi.artist.entity.Artist;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArtistMapper {
    List<ArtistList> findAll(String searchParam);

    int save(Artist artistDto);

    Optional<ArtistDetail> findById(Integer id);

    int updateArtist(Artist updateArtist);

    int deleteArtist(Integer id);

}