package dev.muin.backend.web.response;

import dev.muin.backend.domain.User.Role;
import dev.muin.backend.domain.User.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse{
    private String uuid;  // Base32-encoded
    private String email;
    private String name;
    private Role role;

    public static UserResponse of(User user, String encodedUuid){
        return UserResponse.builder()
                .uuid(encodedUuid)
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
