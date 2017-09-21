package com.project.xiaodong.mytimeapp.frame.task;


import com.project.xiaodong.mytimeapp.frame.task.core.ITask;
import com.project.xiaodong.mytimeapp.frame.task.core.TaskResult;
import com.project.xiaodong.mytimeapp.frame.utils.FileUtil;
import com.project.xiaodong.mytimeapp.frame.utils.PathUtil;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */
public class ClearCacheTask extends ITask {
    public ClearCacheTask(int id) {
        super(id);
    }

    @Override
    public TaskResult doTask() {
        TaskResult result = new TaskResult();
        result.isSuccess = FileUtil.delete(PathUtil.getAppCacheDir().toString());
        return result;
    }
}
