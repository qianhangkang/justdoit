package com.hongkong.demo.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 执行状态，0--未执行，1--执行成功，2--执行失败.
 *
 * @author HANGKANG
 * @date 2018/5/30 下午6:57
 */

@AllArgsConstructor
@Getter
public enum TaskStatusEnum {
    NOT_PERFORME(0,"未执行"),
    SUCCESS(1,"执行成功"),
    FAIL(2,"执行失败"),
    ;

    private int type;
    private String msg;

}
