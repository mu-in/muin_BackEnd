package dev.muin.backend.domain.Store;

import lombok.Getter;

@Getter
public enum Keyword {
    SNACK("과자"),
    DRINKS("음료수"),
    ICECREAM("아이스크림"),
    DISPOSABLES("일회용품"),
    RAMEN("라면");

    private String name;

    Keyword(String name) {
        this.name = name;
    }

    public static Keyword fromString(String target) throws IllegalArgumentException{
        for(Keyword keyword: Keyword.values()) {
            if(keyword.name.equals(target)) return keyword;
        }
        throw new IllegalArgumentException("No enum constant: " + target);
    }
}
