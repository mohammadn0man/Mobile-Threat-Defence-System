package com.trial.mobilethreatdefence.DeviceUsage;

import android.app.ActivityManager;
import android.os.Build;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import androidx.annotation.RequiresApi;

import static android.content.Context.ACTIVITY_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class HardwareUsageDetails {
    public float readCpuUsage() {
        try {
            RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
            String load = reader.readLine();

            String[] toks = load.split(" ");

            long idle1 = Long.parseLong(toks[5]);
            long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            try {
                Thread.sleep(360);
            } catch (Exception e) {}

            reader.seek(0);
            load = reader.readLine();
            reader.close();

            toks = load.split(" ");

            long idle2 = Long.parseLong(toks[5]);
            long cpu2 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            return (float)(cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public float getCpuPer() { //for single process

        float cpuPer = 0;
        try {
            String[] cmd = {"top", "-n", "1"};
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));

            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                if (s.contains("your process name")) {
                    String [] arr = s.split(" ");
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i].contains("%")) {
                            s = arr[i].replace("%", "");
                            cpuPer = Float.parseFloat(s);
                            break;
                        }
                    }
                    //System.out.println(s);
                }
            }

            // read any errors from the attempted command
            //System.out.println("Here is the standard error of the command (if any):\n");
            //while ((s = stdError.readLine()) != null) {
            //System.out.println(s);
            //}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cpuPer;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public double readRamUsage(){
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
//        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        activityManager.getMemoryInfo(mi);
        double availableMegs = mi.availMem / 0x100000L;

//Percentage can be calculated for API 16+
        double percentAvail = mi.availMem / (double)mi.totalMem * 100.0;

        return percentAvail;

    }

}
