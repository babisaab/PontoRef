package utils;

import java.net.*;
import java.util.Objects;

public class NetworkManager {

    public static String myIP() {
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            return socket.getLocalAddress().getHostAddress();
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }

    public static Boolean sameNetwork(String source, String remote) {
        if ("127.0.0.1".equals(remote) || "0:0:0:0:0:0:0:1".equals(remote)) {
            return true;
        }
        String[] parts = source.split("/");
        int prefix;
        if (parts.length < 2) prefix = 0;
        else prefix = Integer.parseInt(parts[1]);
        Inet4Address a = null;
        Inet4Address a1 = null;
        try {
            a = (Inet4Address) InetAddress.getByName(parts[0]);
            a1 = (Inet4Address) InetAddress.getByName(remote);
        } catch (UnknownHostException ignored) {
        }
        byte[] b = Objects.requireNonNull(a).getAddress();
        int ipInt = ((b[0] & 0xFF) << 24) | ((b[1] & 0xFF) << 16) | ((b[2] & 0xFF) << 8) | ((b[3] & 0xFF));
        byte[] b1 = Objects.requireNonNull(a1).getAddress();
        int ipInt1 = ((b1[0] & 0xFF) << 24) | ((b1[1] & 0xFF) << 16) | ((b1[2] & 0xFF) << 8) | ((b1[3] & 0xFF));
        int mask = -(1 << (32 - prefix));
        return (ipInt & mask) == (ipInt1 & mask);
    }
}