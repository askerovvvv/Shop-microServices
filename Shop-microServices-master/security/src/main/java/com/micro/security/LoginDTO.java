package com.micro.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class LoginDTO {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String token;
}
