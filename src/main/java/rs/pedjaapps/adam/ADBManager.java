package rs.pedjaapps.adam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rs.pedjaapps.adam.model.BuildProp;
import rs.pedjaapps.adam.model.Device;

/**
 * Created by pedja on 20.10.14. 12.16.
 * This class is part of the adam
 * Copyright © 2014 ${OWNER}
 */
public class ADBManager
{
    private static ADBManager instance;
    Pattern devicesPattern, buildPropPattern;

    private String sdkDir = "/home/pedja/sdk/";

    private HashMap<String, Device> deviceHistory;

    public static ADBManager getInstance()
    {
        if(instance == null)
        {
            instance = new ADBManager();
        }
        return instance;
    }

    {
        devicesPattern = Pattern.compile("(\\S+?)\\s+(\\S+)");
        buildPropPattern = Pattern.compile("(\\S+?)=(.+)");
        deviceHistory = new HashMap<>();
    }

    public List<Device> devices() throws IOException
    {
        List<Device> devices = new ArrayList<>();

        ProcessBuilder builder = new ProcessBuilder(sdkDir + "platform-tools/adb", "devices");
        builder.redirectErrorStream(true);
        Process process = builder.start();
        InputStream stdout = process.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
        String line;

        while((line = reader.readLine()) != null)
        {
            Matcher matcher = devicesPattern.matcher(line);
            if(matcher.matches())
            {
                String serial = matcher.group(1);
                Device device;
                if(deviceHistory.get(serial) == null)//first time this device has been attached
                {
                    device = new Device(serial, DeviceState.fromString(matcher.group(2)));
                    BuildProp bp = readBuildProp(serial);
                    device.setBuildProp(bp);
                    deviceHistory.put(device.getSerial(), device);
                }
                else
                {
                    device = deviceHistory.get(serial);
                }
                devices.add(device);
            }

        }
        return devices;
    }

    private BuildProp readBuildProp(String serial) throws IOException
    {
        BuildProp bp = new BuildProp();
        ProcessBuilder builder = new ProcessBuilder(sdkDir + "platform-tools/adb", "-s", serial, "shell", "cat", "/system/build.prop");
        builder.redirectErrorStream(true);
        Process process = builder.start();
        InputStream stdout = process.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
        String line;

        while((line = reader.readLine()) != null)
        {
            Matcher matcher = buildPropPattern.matcher(line);
            if(matcher.matches())
            {
                bp.put(matcher.group(1), matcher.group(2));
            }
        }
        return bp;
    }

}
