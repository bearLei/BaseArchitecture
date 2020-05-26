package com.ubtech.base_lib.http.cache;

import androidx.core.util.Pools;

import com.ubtrobot.log.ALog;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @Description :
 * @Created Time : yinrongwu on 2019/8/19.
 * @Author: YinRong.Wu@ubtrobot.com
 * @VerSion : Jimu_3.0.4
 */

public final class EngineKey {
    private int hashCode;
    private StringBuffer mKeyBuffer;
    private static Pools.SynchronizedPool<EngineKey> engineKeyPool;

    private EngineKey() {
//        mKeyBuffer = new StringBuffer();
//        RequestBody body = request.body();
//        if (body != null) {
//            mKeyBuffer.append(body.toString());
//        }
//        mKeyBuffer.append(request.url().toString());
    }


    public static EngineKey obtain(Request request) {
        if (engineKeyPool == null) {
            engineKeyPool = new Pools.SynchronizedPool<>(5);
            EngineKey engineKey = new EngineKey();
            engineKey.setRequest(request);
            return engineKey;
        }
        EngineKey engineKey = engineKeyPool.acquire();
        if (engineKey != null) {
            engineKey.setRequest(request);
            ALog.E("从对象池中获取EngineKey");
            return engineKey;
        }
        ALog.E("从对象池中没有EngineKey，新创建");
        EngineKey newEngine = new EngineKey();
        newEngine.setRequest(request);
        return newEngine;
    }


    private void setRequest(Request request) {
        if (mKeyBuffer == null) {
            mKeyBuffer = new StringBuffer();
        }
        mKeyBuffer.delete(0, mKeyBuffer.length());
        RequestBody body = request.body();
        if (body != null) {

            String bodyStr = bodyToString(body);
            mKeyBuffer.append(bodyStr);
        }
        mKeyBuffer.append(request.url().toString());
    }

    public void release() {
        if (engineKeyPool == null) return;
        engineKeyPool.release(this);

    }

    @Override
    public int hashCode() {
        if (hashCode == 0) {
            int code = mKeyBuffer.toString().hashCode();
            hashCode = code ^ (code >>> 16);
        }
        return hashCode;
    }

    private String bodyToString(RequestBody body) {
        try {
            final RequestBody copy = body;
            final okio.Buffer buffer = new okio.Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}