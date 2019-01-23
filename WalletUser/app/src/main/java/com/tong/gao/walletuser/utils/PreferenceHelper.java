package com.tong.gao.walletuser.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;


import com.tong.gao.walletuser.AppApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * 本地存储工具类 可以存储基础数据类型及复杂对象
 * Created by terry on 7/21/16.
 */

public class PreferenceHelper {

    static final String SHARE_NAME = "app_share_preference";

    private static Object syncObj = new Object();

    private static PreferenceHelper mPH;

    private SharedPreferences.Editor edit = null;

    private Context mContext;

    private PreferenceHelper(Context context) {
        this.mContext = context;
    }

    private Context getMcontext() {
        if (mContext == null) {
            mContext = AppApplication.getMyContext();
        }
        return mContext;
    }

    public static PreferenceHelper getInstance() {
        try {
            if (mPH == null) {
                mPH = new PreferenceHelper(AppApplication.getMyContext());
            }
            return mPH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private SharedPreferences getPreference() {
        return getMcontext().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
    }

    public boolean getBooleanShareData(String key, boolean defaultBoolean) {
        if (getMcontext() == null) {
            return defaultBoolean;
        }
        return getPreference().getBoolean(key, defaultBoolean);
    }

    public int getIntShareData(String key, int defaultInt) {
        if (getMcontext() == null) {
            return defaultInt;
        }
        return getPreference().getInt(key, defaultInt);
    }

    public long getLongShareData(String paramString, long paramLong) {
        if (getMcontext() == null) {
            return paramLong;
        }
        return getPreference().getLong(paramString, paramLong);
    }

    public String getStringShareData(String key, String defaultString) {
        if (getMcontext() == null) {
            return defaultString;
        }
        return getPreference().getString(key, defaultString);
    }

    public boolean putBooleanValue(String key, boolean paramBoolean) {
        if (getMcontext() != null) {
            synchronized (syncObj) {
                if (edit == null) {
                    edit = getPreference().edit();
                }
                edit.putBoolean(key, paramBoolean).commit();
                return true;
            }
        }
        return false;
    }

    public boolean putIntValue(String key, int paramInt)
    {
        if (getMcontext() != null) {
            synchronized (syncObj)
            {
                if (this.edit == null) {
                    this.edit = getPreference().edit();
                }
                this.edit.putInt(key, paramInt).commit();
                return true;
            }
        }
        return false;
    }

    public void putLongValue(String key, long paramLong)
    {
        if (getMcontext() != null) {
            synchronized (syncObj)
            {
                if (this.edit == null) {
                    this.edit = getPreference().edit();
                }
                this.edit.putLong(key, paramLong).commit();
            }
        }
    }

    public void putStringValue(String key, String paramString)
    {
        if (getMcontext() != null) {
            synchronized (syncObj)
            {
                if (this.edit == null) {
                    this.edit = getPreference().edit();
                }
                this.edit.putString(key, paramString).commit();
            }
        }
    }




    /**
     * load object
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getObject(String key, Class<T> clazz) {
        if (getMcontext() != null) {
            if (getPreference().contains(key)) {
                String objectVal = getPreference().getString(key, null);
                byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(bais);
                    T t = (T) ois.readObject();
                    return t;
                } catch (StreamCorruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bais != null) {
                            bais.close();
                        }
                        if (ois != null) {
                            ois.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * save object
     * @param key
     * @param object
     */
    public void putObject(String key, Object object) {
        if (getMcontext() != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(baos);
                out.writeObject(object);
                String objectVal = new String(Base64.encode(baos.toByteArray(),
                        Base64.DEFAULT));
                SharedPreferences.Editor editor = getPreference().edit();
                editor.putString(key, objectVal);
                editor.commit();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (baos != null) {
                        baos.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void clearObjectForKey(String key)
    {
        if (getMcontext() != null) {
            synchronized (syncObj)
            {
                if (this.edit == null) {
                    this.edit = getPreference().edit();
                }
                this.edit.remove(key).commit();
            }
        }
    }


    public void clear() {
        try {
            if (getMcontext() != null) {
                synchronized (syncObj) {
                    if (edit == null) {
                        edit = getPreference().edit();
                    }
                    edit.clear().commit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class PreferenceKey {

        public static final String KEY_N0_REMAIN_0AB = "KEY_N0_REMAIN_0AB"; //首次说明 dialog 不再提醒
        public static final String KEY_N0_REMAIN = "KEY_N0_REMAIN";             //扫码转账 不再提醒


        public PreferenceKey() {
        }
    }

}
