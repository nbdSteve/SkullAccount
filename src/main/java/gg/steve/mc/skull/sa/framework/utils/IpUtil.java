package gg.steve.mc.skull.sa.framework.utils;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class IpUtil {

    public static String convertAddressToString(InetAddress address) {
        return String.valueOf(address).split("/")[1].split(":")[0];
    }

    public static String convertAddressToString(InetSocketAddress address) {
        return String.valueOf(address).split("/")[1].split(":")[0];
    }
}
