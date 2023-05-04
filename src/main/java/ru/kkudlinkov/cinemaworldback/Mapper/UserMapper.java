package ru.kkudlinkov.cinemaworldback.Mapper;

import groovy.lang.Lazy;
import jdk.jfr.Label;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kkudlinkov.cinemaworldback.Domain.dto.UserEditDTO;
import ru.kkudlinkov.cinemaworldback.Domain.dto.UserRegisterDTO;
import ru.kkudlinkov.cinemaworldback.Domain.model.User;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userRegisterDTO.getPassword()))")
    @Mapping(target = "role", constant = "ROLE_USER")
    @Mapping(target = "films", ignore = true)
    @Mapping(target = "image", constant = "https://yt3.ggpht.com/a/AGF-l78AzseOtv4fYGdmRtS7CtaL4wJZLKuFwsi54g=s900-c-k-c0xffffffff-no-rj-mo")
    public abstract User registerDTOToUser(UserRegisterDTO userRegisterDTO);

    public abstract UserEditDTO userToUserEditDTO(User user);

}