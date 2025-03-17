package com.example.reminder.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRequestDto {

    @NotNull(
            message = "Username must be not null.")
    @Length(
            max = 255,
            message = "Username length must be smaller than 255 symbols."
    )
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(
            message = "Password must be not null."
    )
    private String password;

}
