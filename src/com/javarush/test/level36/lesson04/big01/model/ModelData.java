package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;

import java.util.List;

/**
 * Created by 1 on 02.10.2017.
 */
public class ModelData
{
    private User activeUser;
    private boolean displayDeletedUserList;
    private List<User> users;

    public boolean isDisplayDeletedUserList()
    {
        return displayDeletedUserList;
    }

    public void setDisplayDeletedUserList(boolean displayDeletedUserList)
    {
        this.displayDeletedUserList = displayDeletedUserList;
    }

    public User getActiveUser()
    {
        return activeUser;
    }

    public void setActiveUser(User activeUser)
    {
        this.activeUser = activeUser;
    }


    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }
}
