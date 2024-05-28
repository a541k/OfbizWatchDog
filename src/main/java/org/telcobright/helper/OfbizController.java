package org.telcobright.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLOutput;

public class OfbizController {


    static final String OFBIZ_PLUGIN_DIR = "/Desktop/OFBIZ_PLUGIN";
    static final String OFBIZ_HOST = "localhost";
    static final int OFBIZ_PORT = 10523;


    public boolean isOfbizRunning() {
        final String ofbizStatus = "OFBIZ_STATUS: ";
        try (Socket socket = new Socket(OFBIZ_HOST, OFBIZ_PORT)) {
            System.out.println(ofbizStatus + "RUNNING");
            return true;
        } catch (IOException e) {
            System.out.println(ofbizStatus + "NOT_RUNNING");
            return false;
        }
    }


    public void startOfbiz() throws IOException, InterruptedException {
        if (!isOfbizRunning()) {
            System.out.println("Trying to start OFBiz");
            String userHome = System.getProperty("user.home");
            String ofbizDir = userHome + OFBIZ_PLUGIN_DIR;
            String[] command = {
                    "gnome-terminal",
                    "--",
                    "bash",
                    "-c",
                    String.format("cd '%s' && ./gradlew ofbiz; exec bash", ofbizDir)
            };

            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            System.out.println("Started OFBiz in a new terminal");
        } else {
            System.out.println("OFBiz is already running.");
        }
    }


    public void stopOfbiz() throws IOException, InterruptedException {
        System.out.println("Trying to stop OFBiz");
        String userHome = System.getProperty("user.home");
        String ofbizDir = userHome + OFBIZ_PLUGIN_DIR;
        String[] command = {
                "gnome-terminal",
                "--",
                "bash",
                "-c",
                String.format("cd '%s' && ./gradlew --stop; exec bash", ofbizDir)
        };

        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        System.out.println("Stopped OFBiz");
    }
}
