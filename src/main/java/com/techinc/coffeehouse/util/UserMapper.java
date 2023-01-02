package com.techinc.coffeehouse.util;

import com.techinc.coffeehouse.entity.User;
import com.techinc.coffeehouse.generated.AddUserRequest;
import com.techinc.coffeehouse.generated.UserResponse;
import java.time.LocalDate;
import javax.xml.datatype.XMLGregorianCalendar;

public final class UserMapper {
    private UserMapper() {
        throw new UnsupportedOperationException("Cannot create an object of this class");
    }

    public static User mapToJpaUser(AddUserRequest request) {
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        XMLGregorianCalendar userDetailsBirthDate = request.getBirthDate();
        LocalDate userBirthDate = LocalDate.of(userDetailsBirthDate.getYear(),
                                               userDetailsBirthDate.getMonth(),
                                               userDetailsBirthDate.getDay());
        user.setBirthDate(userBirthDate);
        return user;
    }

    public static UserResponse wrapUserForOutput(User user) {
        UserResponse userToOutput = new UserResponse();
        userToOutput.setUserId(user.getUserId());
        userToOutput.setLogin(user.getLogin());
        userToOutput.setEmail(user.getEmail());

        return userToOutput;
    }
}
