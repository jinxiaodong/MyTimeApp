package com.project.xiaodong.mytimeapp.frame.task.core;

import com.project.xiaodong.mytimeapp.frame.utils.LogUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */

public class TaskManager {

    private volatile static TaskManager sTaskManager = null;

    private ExecutorService mExecutorService;

    public TaskManager() {
        int threadCount = TheadCountCalculator.calculateBestThreadCount();
        LogUtil.d("calculateBestThreadCount" + threadCount);
        mExecutorService = Executors.newFixedThreadPool(threadCount);
    }
    public static TaskManager getInstance() {
        if (null == sTaskManager) {
            synchronized (TaskManager.class) {
                if (null == sTaskManager) {
                    sTaskManager = new TaskManager();
                }
            }
        }
        return sTaskManager;
    }
    public void excute(ITask task) {
        mExecutorService.submit(new TaskRunnable(task));
    }

}
