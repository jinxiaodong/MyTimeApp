package com.project.xiaodong.mytimeapp.frame.task;


import com.project.xiaodong.mytimeapp.frame.task.core.ITask;
import com.project.xiaodong.mytimeapp.frame.task.core.TaskResult;
import com.project.xiaodong.mytimeapp.frame.utils.FileSizeCalculateUtil;
import com.project.xiaodong.mytimeapp.frame.utils.PathUtil;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */

public class CalculateCacheTask extends ITask {

    public CalculateCacheTask(int id) {
        super(id);
    }

    @Override
    public TaskResult doTask() {
        TaskResult result = new TaskResult();
        try {
            double size = FileSizeCalculateUtil
                    .calculateFileSize(PathUtil.getAppCacheDir().toString(), FileSizeCalculateUtil.UNIT_MB);
            result.obj = size + "MB";
            result.isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            result.obj = 0 + "MB";
            result.isSuccess = false;
        }
        return result;
    }
}
