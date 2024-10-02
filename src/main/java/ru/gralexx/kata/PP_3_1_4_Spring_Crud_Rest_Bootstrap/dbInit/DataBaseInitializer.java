package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.dbInit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataBaseInitializer {

    @Autowired
    public DataBaseInitializer(DataBaseInitService dataBaseInitService) {
        dataBaseInitService.initRoles();
        dataBaseInitService.initUsers();
    }

}