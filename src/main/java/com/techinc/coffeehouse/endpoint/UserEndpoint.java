package com.techinc.coffeehouse.endpoint;

import com.techinc.coffeehouse.entity.User;
import com.techinc.coffeehouse.generated.AddUserRequest;
import com.techinc.coffeehouse.generated.AddUserResponse;
import com.techinc.coffeehouse.generated.GetUserByIdRequest;
import com.techinc.coffeehouse.generated.GetUserByIdResponse;
import com.techinc.coffeehouse.generated.Status;
import com.techinc.coffeehouse.generated.UserToOutput;
import com.techinc.coffeehouse.service.UserService;
import java.time.LocalDate;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE = "http://coffeehouse.com/users";

    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "GetUserByIdRequest")
    @ResponsePayload
    public GetUserByIdResponse getSpecifiedUser(@RequestPayload GetUserByIdRequest request) {
        User user = userService.getUserById(request.getUserId());

        UserToOutput singleUser = wrapUserForOutput(user);
        GetUserByIdResponse response = new GetUserByIdResponse();
        response.setUser(singleUser);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "AddUserRequest")
    @ResponsePayload
    public AddUserResponse addUser(@RequestPayload AddUserRequest request) {
        User user = mapToJpaUser(request);

        userService.addUser(user);

        AddUserResponse response = new AddUserResponse();
        response.setResult(Status.OK);

        return response;
    }

    private User mapToJpaUser(AddUserRequest request) {
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

    private UserToOutput wrapUserForOutput(User user) {
        UserToOutput userToOutput = new UserToOutput();
        userToOutput.setUserId(user.getUserId());
        userToOutput.setLogin(user.getLogin());
        userToOutput.setEmail(user.getEmail());

        return userToOutput;
    }
}
