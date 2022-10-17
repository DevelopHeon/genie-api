package com.hh.study.genieapi.artist.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hh.study.genieapi.artist.dto.ArtistDetail;
import com.hh.study.genieapi.artist.dto.ArtistForm;
import com.hh.study.genieapi.artist.dto.ArtistList;
import com.hh.study.genieapi.artist.entity.Artist;
import com.hh.study.genieapi.artist.mapper.ArtistMapper;
import com.hh.study.genieapi.common.dto.SearchDto;
import com.hh.study.genieapi.common.error.ArtistNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class ArtistService {

    private final ArtistMapper artistMapper;

    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public PageInfo<ArtistList> findAll(SearchDto searchDto) {
        PageHelper.startPage(searchDto.getPageNum(), searchDto.getPageSize());
        List<ArtistList> artistLists = artistMapper.findAll(searchDto.getKeyword());
        PageInfo<ArtistList> artistsList = new PageInfo<>(artistLists, 10);
        return artistsList;
    }

    public int save(ArtistForm artistForm) {
        Artist artist = modelMapper.map(artistForm, Artist.class);
        artistMapper.save(artist);
        return artist.getArtistId();
    }

    @Transactional(readOnly = true)
    public ArtistDetail findById(Integer id) {
        return artistMapper.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException("아티스트를 찾을 수 없습니다."));
    }

    public int updateArtist(ArtistForm artistForm, Integer id) {
        findById(id);
        Artist updateArtist = modelMapper.map(artistForm, Artist.class);
        int result = artistMapper.updateArtist(updateArtist, id);
        return result;
    }

    public int deleteArtist(Integer id) {
        findById(id);
        int result = artistMapper.deleteArtist(id);
        return result;
    }

}
