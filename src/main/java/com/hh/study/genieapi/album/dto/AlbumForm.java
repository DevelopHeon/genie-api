package com.hh.study.genieapi.album.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumForm {

    @NotNull(message = "artistId는 null일 수 없습니다.")
    private Integer artistId;
    @NotBlank(message = "앨범명은 필수 값입니다.")
    private String albumTitle;
    @NotNull
    private LocalDate releaseDate;
    private String albumExplanation;
    private String genre;
    @Valid
    private List<MusicForm> musicFormList;
}
