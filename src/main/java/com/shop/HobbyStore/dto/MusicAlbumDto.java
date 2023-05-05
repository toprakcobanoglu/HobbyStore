package com.shop.HobbyStore.dto;

import lombok.Data;

@Data
public class MusicAlbumDto extends ProductDto{
    private String singerName;
    private int numberOfSongs;
}
