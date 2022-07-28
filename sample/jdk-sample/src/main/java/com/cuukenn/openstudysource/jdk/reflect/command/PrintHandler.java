package com.cuukenn.openstudysource.jdk.reflect.command;

import lombok.Data;

/**
 * @author changgg
 */
public class PrintHandler implements ICommandHandler<PrintHandler.PrintCmd> {
    @Override
    public void handler(PrintCmd cmd) {
        System.out.println(cmd.getCmdStr());
    }

    @Data
    static class PrintCmd implements ICmd {
        private String cmdStr;
    }
}
