package rs.pedjaapps.adam;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.util.concurrent.Task;
import org.apache.pivot.util.concurrent.TaskListener;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.Window;

import java.net.URL;

import rs.pedjaapps.adam.model.Device;

/**
 * Created by pedja on 22.9.14. 11.27.
 * This class is part of the adam
 * Copyright © 2014 ${OWNER}
 */
public class MainWindow extends Window implements Bindable, TaskListener<List<Device>>
{
    private ListView listView = null;

    @Override
    public void initialize(Map<String, Object> map, URL url, Resources resources)
    {
        listView = (ListView)map.get("listView");

        DeviceRefreshThread deviceRefreshThread = new DeviceRefreshThread(this);
        deviceRefreshThread.start();

        listView.getListViewSelectionListeners().add(new ListViewSelectionListenerImpl()
        {

        });
    }

    @Override
    public void taskExecuted(Task<List<Device>> task)
    {
        listView.setListData(task.getResult());
    }

    @Override
    public void executeFailed(Task<List<Device>> task)
    {

    }
}
