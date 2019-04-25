package com.example.dynamicCmd;

import android.os.Environment;
import android.util.Log;

import com.example.myapplication.AppDelegate;
import com.example.myapplication.DebugLog;
import com.example.myapplication.FileUtils;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * @author xc
 * @time 19-4-16.
 */
public class DynamicCmdManager {
    private static final String TAG = "DynamicCmdManager";
    private static DynamicCmdManager sInstance;

    private DynamicCmdManager() {
    }

    public synchronized static DynamicCmdManager getInstance() {
        if (sInstance == null) {
            sInstance = new DynamicCmdManager();
        }

        return sInstance;
    }

    public void invoke() {
        File downloadFile = new File(Environment.getExternalStorageDirectory() + "/test/dynamic_dex.jar");

        if (!downloadFile.exists()) {
            Log.e(TAG, downloadFile.getAbsolutePath() + " not exists");
            return;
        }

        File cacheDir = AppDelegate.sApplication.getCacheDir();
        final File jarFile = new File(cacheDir, "dynamic_dex.jar");
        FileUtils.copyFile(downloadFile.getAbsolutePath(), jarFile.getAbsolutePath());

        if (!jarFile.exists()) {
            Log.e(TAG, jarFile.getAbsolutePath() + " not exists");
            return;
        }

        DexClassLoader dexClassLoader = new DexClassLoader(jarFile.getAbsolutePath(), cacheDir.getAbsolutePath(), null,
                getClass().getClassLoader());
        try {
            Class clazz = dexClassLoader.loadClass("com.example.dynamicCmd.cmd");
            CmdInvoker invoker = (CmdInvoker) clazz.newInstance();
            invoker.invoke();
        } catch (Exception e) {
            DebugLog.e("dynamic cmd invoke exception", e);
        }
    }
}
