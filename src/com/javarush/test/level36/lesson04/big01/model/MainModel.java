package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.model.service.UserService;
import com.javarush.test.level36.lesson04.big01.model.service.UserServiceImpl;

import java.util.List;

/**
 * Created by 1 on 02.10.2017.
 */
public class MainModel implements Model
{
    private UserService userService = new UserServiceImpl();
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData()
    {
        return modelData;
    }

    @Override
    public void loadUsers()
    {
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(filter(userService.getUsersBetweenLevels(1,100)));
    }

    @Override
    public void changeUserData(String name, long id, int level)
    {
        userService.createOrUpdateUser(name, id, level);
        modelData.setUsers(filter(userService.getUsersBetweenLevels(1,100)));
    }

    public void loadDeletedUsers() {
        modelData.setDisplayDeletedUserList(true);
        List<User> users = userService.getAllDeletedUsers();
        modelData.setUsers(users);
    }

    @Override
    public void deleteUserById(long id)
    {
        userService.deleteUser(id);
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(filter(userService.getUsersBetweenLevels(1,100)));
    }

    @Override
    public void loadUserById(long userId)
    {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }

    private List<User> filter(List<User> users)
    {
        return userService.filterOnlyActiveUsers(users);
    }
}
