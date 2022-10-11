package com.hh.study.genieapi.artist.repository;

import com.hh.study.genieapi.entity.Artist;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArtistMapper {
    List<Artist> findAll(Pageable pageable);

    int getCount();

    int save(Artist artistDto);

    Optional<Artist> findById(Integer id);

    void updateArtist(Artist updateArtist);

    void deleteArtist(Integer id);

    int getSearchCount(String searchParam);

    List<Artist> getSearchAll(String searchDto);
}
