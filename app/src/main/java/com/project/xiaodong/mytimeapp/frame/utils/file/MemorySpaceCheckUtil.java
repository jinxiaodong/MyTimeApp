package com.project.xiaodong.mytimeapp.frame.utils.file;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.os.Environment;
import android.os.StatFs;

import com.project.xiaodong.mytimeapp.frame.application.BaseApplication;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by xiaodong.jin on 2017/10/21.
 */
@SuppressLint({"SdCardPath"})
public class MemorySpaceCheckUtil {
    public MemorySpaceCheckUtil() {
    }

    private static long getAvailableSize(String path) {
        StatFs fileStats = new StatFs(path);
        fileStats.restat(path);
        return (long) fileStats.getAvailableBlocks() * (long) fileStats.getBlockSize();
    }

    private static long getTotalSize(String path) {
        StatFs fileStats = new StatFs(path);
        fileStats.restat(path);
        return (long) fileStats.getBlockCount() * (long) fileStats.getBlockSize();
    }

    public static boolean checkSDCard() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static long getSDAvailableSize() {
        return Environment.getExternalStorageState().equals("mounted") ? getAvailableSize(Environment.getExternalStorageDirectory().toString()) : 0L;
    }

    public static long getSystemAvailableSize() {
        return getAvailableSize("/data");
    }

    public static boolean hasEnoughMemory(String filePath) {
        File file = new File(filePath);
        long length = file.length();
        return !filePath.startsWith("/sdcard") && !filePath.startsWith("/mnt/sdcard") ? getSystemAvailableSize() > length : getSDAvailableSize() > length;
    }

    public static long getSDTotalSize() {
        return Environment.getExternalStorageState().equals("mounted") ? getTotalSize(Environment.getExternalStorageDirectory().toString()) : 0L;
    }

    public static long getSysTotalSize() {
        return getTotalSize("/data");
    }

    public static String getAvailMemory() {
        ActivityManager am = (ActivityManager) BaseApplication.getInstance().getBaseContext().getSystemService("activity");
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return mi.availMem / 1024L / 1024L + "";
    }

    public static String getTotalMemory() {
        Method _readProclines = null;

        try {
            Class e = Class.forName("android.os.Process");
            Class[] parameterTypes = new Class[]{String.class, String[].class, long[].class};
            _readProclines = e.getMethod("readProcLines", parameterTypes);
            Object[] arglist = new Object[3];
            String[] mMemInfoFields = new String[]{"MemTotal:", "MemFree:", "Buffers:", "Cached:"};
            long[] mMemInfoSizes = new long[mMemInfoFields.length];
            mMemInfoSizes[0] = 30L;
            mMemInfoSizes[1] = -30L;
            arglist[0] = new String("/proc/meminfo");
            arglist[1] = mMemInfoFields;
            arglist[2] = mMemInfoSizes;
            if (_readProclines != null) {
                _readProclines.invoke((Object) null, arglist);
                return "" + mMemInfoSizes[0] / 1024L;
            } else {
                return "-1";
            }
        } catch (Exception var6) {
            return "-1";
        }
    }
}

