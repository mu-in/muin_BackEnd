package dev.muin.backend.domain.Product;

/**
 * <h1>Category</h1>
 * 상품의 대분류입니다. (추후 추가 예정)
 *
 * @since 2021-09-20
 */
public enum Category {
    NOODLE("면류"),
    HMR("상온HMR"),
    DAILY_NECESSITY("생활용품"),
    SAUCE("소스"),
    DAIRY_PRODUCT("유제품"),
    DRINKS("음료"),
    QUASI_DRUG("의약외품"),
    ALCOHOL("주류"),
    CAFFEINE("커피차");

    private String name;

    Category(String name){
        this.name = name;
    }
}
