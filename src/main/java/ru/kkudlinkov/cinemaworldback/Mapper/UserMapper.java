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
    @Mapping(target = "image", constant = "https://www.pinclipart.com/picdir/big/165-1653686_female-user-icon-png-download-user-colorful-icon.png")
//    TODO:Добавить описание человека
    public abstract User registerDTOToUser(UserRegisterDTO userRegisterDTO);

    public abstract UserEditDTO userToUserEditDTO(User user);

}