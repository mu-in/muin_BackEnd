package dev.muin.backend.domain.Product;

/**
 * <h1>Category</h1>
 * 상품의 대분류입니다. (추후 추가 예정)
 *
 * @since 2021-09-20
 */
public enum Category {
    SNACK("과자"),
    DRINKS("음료"),
    RAMEN("라면");

    private String name;

    Category(String name){
        this.name = name;
    }
}
