package com.ne.starter.utils;

import com.ne.starter.models.User;
import org.modelmapper.ModelMapper;

public class Mapper {
    public static ModelMapper modelMapper = new ModelMapper();

    public static User getUserFromDTO(Object object) {
        return modelMapper.map(object, User.class);
    }

}
