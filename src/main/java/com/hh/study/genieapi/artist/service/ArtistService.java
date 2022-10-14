package com.hh.study.genieapi.artist.service;

import com.github.pagehelper.PageHelper;
import com.hh.study.genieapi.artist.dto.ArtistDto;
import com.hh.study.genieapi.artist.mapper.ArtistMapper;
import com.hh.study.genieapi.common.dto.SearchDto;
import com.hh.study.genieapi.common.error.ArtistNotFoundException;
import com.hh.study.genieapi.artist.entity.Artist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class ArtistService {

    private final ArtistMapper artistMapper;

    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<Artist> findAll(SearchDto artistSearch) {
        PageHelper.startPage(artistSearch.getPageNum(), artistSearch.getPageOption());
        return artistMapper.findAll(artistSearch.getSearchParam());
    }

    public int save(ArtistDto artistDto) {
        Artist artist = modelMapper.map(artistDto, Artist.class);

        artistMapper.save(artist);
        return artist.getArtistId();
    }

    @Transactional(readOnly = true)
    public Artist findById(Integer id) {
        Optional<Artist> optionalArtist = artistMapper.findById(id);
        if(!optionalArtist.isPresent()){
            throw new ArtistNotFoundException("아티스트를 찾을 수 없습니다.");
        }
        Artist artist = optionalArtist.get();
        return artist;
    }

    public int updateArtist(ArtistDto artistDto, Integer id) {
        findById(id);
        Artist updateArtist = modelMapper.map(artistDto, Artist.class);
        updateArtist.setArtistId(id);
        int result = artistMapper.updateArtist(updateArtist);
        return result;
    }

    public int deleteArtist(Integer id) {
        findById(id);
        int result = artistMapper.deleteArtist(id);
        return result;
    }

}
