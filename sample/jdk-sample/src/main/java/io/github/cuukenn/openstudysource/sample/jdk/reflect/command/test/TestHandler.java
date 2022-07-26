package io.github.cuukenn.openstudysource.sample.jdk.reflect.command.test;

import io.github.cuukenn.openstudysource.sample.jdk.reflect.command.ICmd;
import io.github.cuukenn.openstudysource.sample.jdk.reflect.command.ICommandHandler;
import lombok.Data;

/**
 * @author changgg
 */
public class TestHandler implements ICommandHandler<TestHandler.TestCmd> {
    @Override
    public void handler(TestCmd cmd) {
        System.out.println(cmd.getCmdStr());
    }

    @Data
    static class TestCmd implements ICmd {
        private String cmdStr;
    }
}
