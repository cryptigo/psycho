package util;

import java.awt.*;

public class HardwareInfo {

    // ----------------------------------------------------------
    // Screen
    // ----------------------------------------------------------
    public static int getScreenWidth() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        return (int) screenSize.getWidth();
    }

    public static int getScreenHeight() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        return (int) screenSize.getHeight();
    }

    // ----------------------------------------------------------
    // Operating System
    // ----------------------------------------------------------
    public static String getOperatingSystemName() {
        return System.getProperty("os.name");
    }

    public static String getOperatingSystemVersion() {
        return System.getProperty("os.version");
    }

    public static String getOperatingSystemArchitecture() {
        return System.getProperty("os.arch");
    }

    // ----------------------------------------------------------
    // CPU
    // ----------------------------------------------------------
    public static String getCpuModel() {
        String cpuInfo = System.getenv("PROCESSOR_IDENTIFIER");
        if (cpuInfo == null) {
            cpuInfo = System.getProperty("os.arch");
        }
        return cpuInfo;
    }

    public static int getNumberOfCpuCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static String getCpuArchitecture() {
        String cpuInfo = System.getProperty("os.arch");
        if (cpuInfo == null) {
            cpuInfo = System.getenv("PROCESSOR_ARCHITECTURE");
        }
        return cpuInfo;
    }


}
