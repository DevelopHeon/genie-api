package com.hh.study.genieapi.common.aop;

import com.hh.study.genieapi.album.dto.AlbumForm;
import com.hh.study.genieapi.artist.dto.ArtistDetail;
import com.hh.study.genieapi.artist.mapper.ArtistMapper;
import com.hh.study.genieapi.common.error.ArtistNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Aspect
public class AlbumServiceAop {
    private final ArtistMapper artistMapper;

    @Before("execution(* com.hh.study.genieapi.album.service.AlbumService.*Albums(..)) && args(albumForm, ..))")
    public void artistFindCheck(AlbumForm albumForm){
        Integer id = albumForm.getArtistId();
        Optional<ArtistDetail> optionalArtist = artistMapper.findById(id);
        if(!optionalArtist.isPresent()){
            throw new ArtistNotFoundException("아티스트를 찾을 수 없습니다.");
        }
    }

}
