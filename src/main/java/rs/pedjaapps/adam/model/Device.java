package rs.pedjaapps.adam.model;

import rs.pedjaapps.adam.DeviceState;

/**
 * Created by pedja on 20.10.14. 12.42.
 * This class is part of the adam
 * Copyright © 2014 ${OWNER}
 */
public class Device
{
    private String serial;
    private DeviceState state;

    private BuildProp buildProp;

    public Device(String serial, DeviceState state)
    {
        this.serial = serial;
        this.state = state;
    }

    public BuildProp getBuildProp()
    {
        return buildProp;
    }

    public void setBuildProp(BuildProp buildProp)
    {
        this.buildProp = buildProp;
    }

    public DeviceState getState()
    {
        return state;
    }

    public void setState(DeviceState state)
    {
        this.state = state;
    }

    public String getSerial()
    {
        return serial;
    }

    public void setSerial(String serial)
    {
        this.serial = serial;
    }

    @Override
    public String toString()
    {
        if(buildProp == null || buildProp.getDeviceName() == null)
        {
            return serial;
        }
        else
        {
            return String.format("%s [%s]", buildProp.getDeviceName(), serial);
        }
    }
}
