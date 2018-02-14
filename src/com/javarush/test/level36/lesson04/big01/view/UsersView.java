package com.javarush.test.level36.lesson04.big01.view;

import com.javarush.test.level36.lesson04.big01.controller.Controller;
import com.javarush.test.level36.lesson04.big01.model.ModelData;

/**
 * Created by 1 on 02.10.2017.
 */
public class UsersView implements View
{
    private Controller controller;

    @Override
    public void refresh(ModelData modelData)
    {
        if (modelData.isDisplayDeletedUserList()) System.out.println("All deleted users:");
            else System.out.println("All users:");
        for (int i = 0; i < modelData.getUsers().size(); i++)
        {
            System.out.print('\t');
            System.out.println(modelData.getUsers().get(i));
        }
        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void fireEventShowAllUsers()
    {
        controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }

    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }
}
