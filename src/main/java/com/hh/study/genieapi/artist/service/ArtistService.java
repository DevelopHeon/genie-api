package com.hh.study.genieapi.artist.service;

import com.github.pagehelper.PageHelper;
import com.hh.study.genieapi.artist.dto.ArtistDto;
import com.hh.study.genieapi.artist.repository.ArtistMapper;
import com.hh.study.genieapi.common.error.ArtistNotFoundException;
import com.hh.study.genieapi.entity.Artist;
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
    private final static int PAGE_SIZE=5;

    @Transactional(readOnly = true)
    public List<Artist> findAll(String searchParam, int pageNum) {
        PageHelper.startPage(pageNum, PAGE_SIZE);
        return artistMapper.findAll(searchParam);
    }

    public int save(ArtistDto artistDto) {
        Artist artist = modelMapper.map(artistDto, Artist.class);

        log.info("artist : " + artist.toString());
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

    public void updateArtist(ArtistDto artistDto, Integer id) {
       findById(id);

        Artist updateArtist = modelMapper.map(artistDto, Artist.class);
        updateArtist.setArtistId(id);
        artistMapper.updateArtist(updateArtist);
    }

    public void deleteArtist(Integer id) {
        findById(id);
        artistMapper.deleteArtist(id);
    }

}
