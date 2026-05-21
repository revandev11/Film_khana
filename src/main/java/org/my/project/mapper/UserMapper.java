package org.my.project.mapper;

import org.my.project.dto.request.RegisterRequest;
import org.my.project.dto.request.UserCreateRequest;
import org.my.project.dto.response.UserResponse;
import org.my.project.entity.User;
import org.my.project.enums.Roles;

public class UserMapper {
public  static User ToEntity(UserCreateRequest request){
    if(request==null) return null;
    User user=new User();
    user.setUserName(request.getUserName());
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());
    user.setRoles(request.getRoles());
    return user;
}
public  static User FromUserToEntity(RegisterRequest request){
    if(request==null) return null;
    User user=new User();
    user.setUserName(request.getUserName());
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());
    return user;
}
public static UserResponse ToDto(User user){
    if (user==null)return  null;
    UserResponse userResponse=new UserResponse();
    userResponse.setId(user.getId());
    userResponse.setUserName(user.getUserName());
    userResponse.setEmail(user.getEmail());

    return userResponse;
}


}
