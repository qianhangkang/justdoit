package com.hongkong.demo.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务执行类型，0--立即执行，1--延时执行一次，2--利用cron表达式执行.
 *
 * @author HANGKANG
 * @date 2018/5/31 下午2:31
 */
@AllArgsConstructor
@Getter
public enum TaskTypeEnum {
    NOW(0,"立即执行"),
    LATER(1,"延时执行一次"),
    CRON(2,"利用cron表达式执行"),
    ;

    private int type;
    private String msg;

    public static TaskTypeEnum findEnumByType(int type) {
        for (TaskTypeEnum e :
                TaskTypeEnum.values()) {
            if (e.getType() == type) {
                return e;
            }
        }
        return null;
    }
}
