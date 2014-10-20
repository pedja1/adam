package rs.pedjaapps.adam;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;

import java.io.IOException;

/**
 * Created by pedja on 22.9.14. 15.28.
 * This class is part of the adam
 * Copyright © 2014 ${OWNER}
 */
public class MainApplication implements Application
{
    public static void main(String[] args) throws IOException
    {
        DesktopApplicationContext.main(MainApplication.class, args);

        /*ADBManager manager = ADBManager.getInstance();
        List<String> devices = manager.devices();
        for(String s : devices)
        {
            System.out.println(s + "\n");
        }

        System.out.println("exit");*/
    }

    private Window window;

    @Override
    public void startup(Display display, Map<String, String> map) throws Exception
    {
        BXMLSerializer bxmlSerializer = new BXMLSerializer();
        window = (Window)bxmlSerializer.readObject(MainWindow.class, "/layout/main.bxml");
        window.setSize(360, 200);
        window.open(display);
    }

    @Override
    public boolean shutdown(boolean b) throws Exception
    {
        return false;
    }

    @Override
    public void suspend() throws Exception
    {

    }

    @Override
    public void resume() throws Exception
    {

    }
}
