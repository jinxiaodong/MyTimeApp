package com.project.xiaodong.mytimeapp.frame.task.core;

import android.os.Process;

import com.project.xiaodong.mytimeapp.frame.eventbus.EventCenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */

public class TaskRunnable implements Runnable {

    private ITask mITask;

    public TaskRunnable(ITask ITask) {
        mITask = ITask;
    }

    @Override
    public void run() {

        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        EventBus.getDefault().post(new EventCenter<>(mITask.id, mITask.doTask()));
    }
}
