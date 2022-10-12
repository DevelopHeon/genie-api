package com.hh.study.genieapi.album.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumDto {

    private Integer artistId;
    @NotEmpty(message = "앨범명은 필수 값입니다.")
    private String albumTitle;
    @NotNull
    private LocalDate releaseDate;
    private String albumExplanation;
    private String genre;
    private String albumAuthor;
    private List<MusicDto> musicDtoList;
}
