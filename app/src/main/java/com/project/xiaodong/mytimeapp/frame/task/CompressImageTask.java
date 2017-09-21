package com.project.xiaodong.mytimeapp.frame.task;


import com.project.xiaodong.mytimeapp.frame.task.core.ITask;
import com.project.xiaodong.mytimeapp.frame.task.core.TaskResult;
import com.project.xiaodong.mytimeapp.frame.utils.ImageCompressUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */

public class CompressImageTask extends ITask {
    private String[] filePaths;

    private CompressImageTask(int id) {
        super(id);
    }

    public CompressImageTask(int taskId, String... filePaths) {
        super(taskId);
        this.filePaths = filePaths;
    }

    @Override
    public TaskResult doTask() {
        List<String> outPaths = new ArrayList<>();
        TaskResult result = new TaskResult();
        result.obj = outPaths;
        try {
            if (null != filePaths && filePaths.length > 0) {
                for (String path : filePaths) {
                    outPaths.add(ImageCompressUtil.compressImage(path));
                }
            }
            result.isSuccess = true;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.isSuccess = false;
        }
        return result;
    }
}
