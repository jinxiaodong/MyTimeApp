
package com.project.xiaodong.mytimeapp.frame.presenter;

import android.content.Context;

import java.util.HashMap;

/**
 * @description 数据提供者基类
 */
public class Presenter<T> {
    /*******************************************************************************
     * Public/Protected Variables
     *******************************************************************************/

    public HashMap<String, Object> getmParams() {
        if (mParams == null) {
            mParams = new HashMap<>();
        }
        return mParams;
    }

    /*******************************************************************************
     * Private Variables
     *******************************************************************************/
    protected HashMap<String, Object> mParams;
    protected T mvpView;
    protected Context mContext;
    /*******************************************************************************
     *	Overrides From Base
     *******************************************************************************/

    /*******************************************************************************
     * Public/Protected Methods
     *******************************************************************************/
    public Presenter(Context context, T mvpView) {
        this.mContext = context;
        this.mvpView = mvpView;
        mParams = new HashMap<>();
    }

    public HashMap<String, Object> put(String key, Object value) {
        if (mParams == null) {
            mParams = new HashMap<String, Object>();
        }
        mParams.put(key, value);
        return mParams;
    }
    /*******************************************************************************
     *	Private Methods
     *******************************************************************************/

    /*******************************************************************************
     *	Internal Class,Interface
     *******************************************************************************/
}
