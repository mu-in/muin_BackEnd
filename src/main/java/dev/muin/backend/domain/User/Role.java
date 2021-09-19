package dev.muin.backend.domain.User;

/**
 * <h1>Role</h1>
 * 사용자의 역할로 권한에 영향을 미칩니다.<br>
 * - {@code USER}: 사용자 기본 권한<br>
 * - {@code MANAGER}: 고객이면서 점주의 권한
 *
 * @since 2021-09-20
 */
public enum Role {
    MANAGER("점주"),
    USER("고객");

    private String name;

    Role(String name) {
        this.name = name;
    }
}
