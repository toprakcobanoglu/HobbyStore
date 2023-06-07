package com.shop.HobbyStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicAlbumDto extends ProductDto   {
    private String singerName;
    private int numberOfSongs;
}
