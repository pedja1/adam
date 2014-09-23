package rs.pedjaapps.adam;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.LinkedList;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.ListViewSelectionListener;
import org.apache.pivot.wtk.Span;
import org.apache.pivot.wtk.Window;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import JDroidLib.android.controllers.ADBController;
import JDroidLib.android.device.Device;
import JDroidLib.exceptions.OSNotSupportedException;

/**
 * Created by pedja on 22.9.14. 11.27.
 * This class is part of the adam
 * Copyright © 2014 ${OWNER}
 */
public class MainWindow extends Window implements Bindable
{
    private ListView listView = null;

    @Override
    public void initialize(Map<String, Object> map, URL url, Resources resources)
    {
        listView = (ListView)map.get("listView");

        try
        {
            List<Device> devices = ADBController.getInstance().getConnectedDevices();
            org.apache.pivot.collections.List<Device> list = new LinkedList<Device>();
            for(Device device : devices)
            {
                list.add(device);
            }
            listView.setListData(list);
        }
        catch (IOException | InterruptedException | OSNotSupportedException e)
        {
            e.printStackTrace();
        }

        listView.getListViewSelectionListeners().add(new ListViewSelectionListener()
        {
            @Override
            public void selectedRangeAdded(ListView listView, int rangeStart, int rangeEnd)
            {

            }

            @Override
            public void selectedRangeRemoved(ListView listView, int rangeStart, int rangeEnd)
            {

            }

            @Override
            public void selectedRangesChanged(ListView listView, Sequence<Span> previousSelectedRanges)
            {
                if (previousSelectedRanges != null && previousSelectedRanges != listView.getSelectedRanges())
                {

                }
            }

            @Override
            public void selectedItemChanged(ListView listView, Object previousSelectedItem)
            {
                // No-op
            }
        });
    }
}
