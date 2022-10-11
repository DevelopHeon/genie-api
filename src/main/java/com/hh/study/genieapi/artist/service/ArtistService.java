package com.hh.study.genieapi.artist.service;

import com.hh.study.genieapi.artist.dto.ArtistDto;
import com.hh.study.genieapi.artist.repository.ArtistMapper;
import com.hh.study.genieapi.common.error.ArtistNotFoundException;
import com.hh.study.genieapi.entity.Artist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ArtistService {

    private final ArtistMapper artistMapper;

    private final ModelMapper modelMapper;

    public List<Artist> findAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 5);
        return artistMapper.findAll(pageable);
    }

    public int getCount() {
        return artistMapper.getCount();
    }

    public int save(ArtistDto artistDto) {
        Artist artist = modelMapper.map(artistDto, Artist.class);

        log.info("artist : " + artist.toString());
        artistMapper.save(artist);
        return artist.getArtistId();
    }

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

    public int getSearchCount(String searchParam) {
        return artistMapper.getSearchCount(searchParam);
    }

    public List<Artist> findSearchAll(String searchParam) {
        return artistMapper.getSearchAll(searchParam);
    }

}
