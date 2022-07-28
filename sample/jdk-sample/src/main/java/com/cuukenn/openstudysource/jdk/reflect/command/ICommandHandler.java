package com.cuukenn.openstudysource.jdk.reflect.command;

/**
 * @author changgg
 */
public interface ICommandHandler<ICmdType extends ICmd> {
    /**
     * 执行指定指令
     *
     * @param cmd 命令
     */
    void handler(ICmdType cmd);
}
