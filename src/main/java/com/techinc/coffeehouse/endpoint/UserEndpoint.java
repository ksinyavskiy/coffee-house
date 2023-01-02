package com.techinc.coffeehouse.endpoint;

import com.techinc.coffeehouse.entity.User;
import com.techinc.coffeehouse.generated.AddUserRequest;
import com.techinc.coffeehouse.generated.AddUserResponse;
import com.techinc.coffeehouse.generated.DeleteUserRequest;
import com.techinc.coffeehouse.generated.DeleteUserResponse;
import com.techinc.coffeehouse.generated.GetAllUsersResponse;
import com.techinc.coffeehouse.generated.GetUserByIdRequest;
import com.techinc.coffeehouse.generated.GetUserByIdResponse;
import com.techinc.coffeehouse.generated.Status;
import com.techinc.coffeehouse.generated.UserResponse;
import com.techinc.coffeehouse.service.UserService;
import com.techinc.coffeehouse.util.UserMapper;
import java.util.List;
import java.util.stream.Collectors;
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
        User user = userService.getUser(request.getUserId());

        UserResponse userResponse = UserMapper.wrapUserForOutput(user);
        GetUserByIdResponse response = new GetUserByIdResponse();
        response.setUserResponse(userResponse);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "AddUserRequest")
    @ResponsePayload
    public AddUserResponse addUser(@RequestPayload AddUserRequest request) {
        User user = UserMapper.mapToJpaUser(request);

        userService.addUser(user);

        AddUserResponse response = new AddUserResponse();
        response.setResult(Status.OK);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "DeleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse removeUser(@RequestPayload DeleteUserRequest request) {
        User user = userService.getUser(request.getUserId());

        userService.removeUser(user);

        DeleteUserResponse response = new DeleteUserResponse();
        response.setResult(Status.OK);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "GetAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponse> userToOutputs = users.stream()
                                                .map(UserMapper::wrapUserForOutput)
                                                .collect(Collectors.toList());
        GetAllUsersResponse response = new GetAllUsersResponse();
        response.getUserResponse().addAll(userToOutputs);

        return response;
    }
}
