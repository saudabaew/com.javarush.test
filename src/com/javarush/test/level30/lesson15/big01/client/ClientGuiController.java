package com.javarush.test.level30.lesson15.big01.client;

/**
 * Created by 1 on 08.09.2017.
 */
public class ClientGuiController extends Client
{
    private ClientGuiModel model = new ClientGuiModel();
    private ClientGuiView view = new ClientGuiView(this);

    public static void main(String[] args)
    {
        new ClientGuiController().run();
    }

    public class GuiSocketThread extends SocketThread{
        @Override
        protected void processIncomingMessage(String message)
        {
            model.setNewMessage(message);
            view.refreshMessages();
        }

        @Override
        protected void informAboutDeletingNewUser(String userName)
        {
            model.deleteUser(userName);
            view.refreshUsers();
        }

        @Override
        protected void informAboutAddingNewUser(String userName)
        {
            model.addUser(userName);
            view.refreshUsers();
        }

        @Override
        protected void notifyConnectionStatusChanged(boolean clientConnected)
        {
            view.notifyConnectionStatusChanged(clientConnected);
        }
    }

    public ClientGuiModel getModel(){
        return model;
    }

    @Override
    protected String getUserName()
    {
        return view.getUserName();
    }

    @Override
    protected int getServerPort()
    {
        return view.getServerPort();
    }

    @Override
    protected String getServerAddress()
    {
       return view.getServerAddress();
    }

    @Override
    public void run()
    {
        getSocketThread().run();
    }

    @Override
    protected SocketThread getSocketThread()
    {
        return new GuiSocketThread();
    }
}
