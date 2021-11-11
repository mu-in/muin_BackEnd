package dev.muin.backend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StockStatusDto {
    private int soldOut;
    private int shortage;
}