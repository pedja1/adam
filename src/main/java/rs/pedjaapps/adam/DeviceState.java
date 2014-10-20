/*
 * Copyright (C) 2014 beatsleigher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package rs.pedjaapps.adam;


/**
 * Represents a device's state.
 * @author beatsleigher, someone else whom I don't know of...
 */
public enum DeviceState {

    device, offline, recovery, fastboot, unknown, bootloader, host, sideload, noperm, unauthorized;

    public static DeviceState fromString(String value)
    {
        if(value == null)return unknown;
        for(DeviceState state : values())
        {
            if(state.toString().equals(value.toLowerCase()))
            {
                return state;
            }
        }
        return unknown;
    }

    //this is taken from "transport.c" from adb source ("https://github.com/android/platform_system_core/blob/master/adb/transport.c")

    /*switch(t->connection_state){
     case CS_OFFLINE: return "offline";
     case CS_BOOTLOADER: return "bootloader";
     case CS_DEVICE: return "device";
     case CS_HOST: return "host";
     case CS_RECOVERY: return "recovery";
     case CS_SIDELOAD: return "sideload";
     case CS_NOPERM: return "no permissions";
     case CS_UNAUTHORIZED: return "unauthorized";
     default: return "unknown";
     }*/

    /**
     * Get human readable representation of the device state
     *
     * @param state DeviceState constant to be converted to human readable
     *              string
     *
     * @return
     */
    public static String toString(DeviceState state) {
        switch (state) {
            case device:
                return "DEVICE";
            case offline:
                return "OFFLINE";
            case recovery:
                return "RECOVERY";
            case fastboot:
                return "FASTBOOT";
            case bootloader:
                return "BOOTLOADER";
            case host:
                return "HOST";
            case sideload:
                return "SIDELOAD";
            case noperm:
                return "NO PERMISSIONS";
            case unauthorized:
                return "UNAUTHORIZED";
            default:
                return "UNKNOWN";
        }
    }
    

}
