package rs.pedjaapps.adam;

import org.apache.pivot.collections.LinkedList;
import org.apache.pivot.collections.List;
import org.apache.pivot.util.concurrent.Task;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.util.concurrent.TaskListener;

import java.io.IOException;

import rs.pedjaapps.adam.model.Device;

/**
 * Created by pedja on 17.10.14. 15.29.
 * This class is part of the adam
 * Copyright © 2014 ${OWNER}
 */
public class DeviceRefreshThread extends Thread
{
    public static final long DEFAULT_REFRESH_INTERVAL = 2000;
    private boolean running = true;
    private long refreshInterval = DEFAULT_REFRESH_INTERVAL;

    Task<List<Device>> task;
    TaskListener<List<Device>> callback;

    public DeviceRefreshThread(TaskListener<List<Device>> callback, long refreshInterval)
    {
        this.callback = callback;
        this.refreshInterval = refreshInterval;
        task = new Task<List<Device>>()
        {
            @Override
            public List<Device> execute() throws TaskExecutionException
            {
                java.util.List<Device> devices = null;
                try
                {
                    devices = ADBManager.getInstance().devices();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                org.apache.pivot.collections.List<Device> list = new LinkedList<Device>();
                for(Device device : devices)
                {
                    list.add(device);
                }
                return list;
            }
        };
    }
    public DeviceRefreshThread(TaskListener<List<Device>> callback)
    {
        this(callback, DEFAULT_REFRESH_INTERVAL);
    }

    @Override
    public void run()
    {
        while(running)
        {
            if(!task.isPending())
            {
                task.execute(callback);
            }
            try
            {
                Thread.sleep(DeviceRefreshThread.this.refreshInterval);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }

    public void quit()
    {
        running = false;
    }
}
