package service;

import db.Database;
import model.User;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.RequestHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberJoinService {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    public boolean createUser(Map<String, String> params) {
        if(!validation(params)) return false;
        String userId = params.get("userId");
        String password = params.get("password");
        String name = params.get("name");
        String email = params.get("email");

        User user = new User(userId, password, name, email);
        logger.debug("New User : {}", user);
        Database.addUser(user);
        return true;
    }

    private static boolean validation(Map<String, String> params) {
        boolean valid = true;
        for (String value : params.values()) {
            if(value.equals("")) {
                return false;
            }
        }
        if (Database.findUserById(params.get("userId")) != null) {
            return false;
        }
        return true;
    }
}
