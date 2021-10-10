package dev.muin.backend.web.response;

import dev.muin.backend.domain.User.Role;
import dev.muin.backend.domain.User.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    private String uuid;
    private String email;
    private String imageUrl;
    private String name;
    private Role role;

    public static UserResponse of(User user){
        return UserResponse.builder()
                .uuid(user.getUuid())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
