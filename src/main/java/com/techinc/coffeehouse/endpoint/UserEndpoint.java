package com.techinc.coffeehouse.endpoint;

import com.techinc.coffeehouse.entity.User;
import com.techinc.coffeehouse.generated.GetUserByIdRequest;
import com.techinc.coffeehouse.generated.GetUserByIdResponse;
import com.techinc.coffeehouse.generated.SingleUser;
import com.techinc.coffeehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {
    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(namespace = "http://coffeehouse.com/users", localPart = "GetUserByIdRequest")
    @ResponsePayload
    public GetUserByIdResponse getSpecifiedUser(@RequestPayload GetUserByIdRequest request) {
        User user = userService.getUserById(request.getUserId());

        SingleUser singleUser = wrapUserForResponse(user);
        GetUserByIdResponse response = new GetUserByIdResponse();
        response.setUser(singleUser);

        return response;
    }

    private SingleUser wrapUserForResponse(User user) {
        SingleUser singleUser = new SingleUser();
        singleUser.setUserId(user.getUserId());
        singleUser.setLogin(user.getLogin());
        singleUser.setEmail(user.getEmail());

        return singleUser;
    }
}
