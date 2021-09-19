package dev.muin.backend.domain.Tag;

enum Keyword {
    SNACK("과자"),
    DRINKS("음료수"),
    ICECREAM("아이스크림"),
    DISPOSABLES("일회용품"),
    RAMEN("라면");

    private String name;

    private Keyword(String name) {
        this.name = name;
    }
}
