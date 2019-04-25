package com.example.myapplication;

import android.util.Log;

/**
 * @author xc
 * @time 19-4-25.
 */
public class DebugLog {
    public static void e(String msg, Exception e) {
        Log.e("log", msg, e);
    }
}
