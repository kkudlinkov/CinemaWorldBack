package ru.kkudlinkov.cinemaworldback.Domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import ru.kkudlinkov.cinemaworldback.Domain.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoDTO {
    private boolean isAuth;
    private User user;
}