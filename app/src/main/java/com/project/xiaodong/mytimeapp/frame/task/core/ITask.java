package com.project.xiaodong.mytimeapp.frame.task.core;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */

public abstract class ITask {
    public int id;

    public ITask(int id){
        this.id = id;
    }

public abstract  TaskResult doTask();

}
