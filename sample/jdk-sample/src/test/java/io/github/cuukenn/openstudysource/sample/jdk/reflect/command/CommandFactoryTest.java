package io.github.cuukenn.openstudysource.sample.jdk.reflect.command;

import org.junit.Test;

/**
 * @author changgg
 */
public class CommandFactoryTest {
    @Test
    public void test_command_factory() {
        System.out.println(CommandFactory.getAllCommandHandler());
    }
}
