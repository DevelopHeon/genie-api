package com.hh.study.genieapi.album.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumDto {

    @NotEmpty(message = "아티스트명은 필수 값입니다.")
    private String artistName;
    @NotEmpty(message = "앨범명은 필수 값입니다.")
    private String albumTitle;
    @NotEmpty(message = "발매일은 필수 값입니다.")
    private LocalDate releaseDate;
    private String artistExplanation;
    private String genre;
    private String albumAuthor;
    private List<MusicDto> musicDtos;
}
