package io.github.cuukenn.openstudysource.sample.jdk.reflect.command;

import lombok.Data;

/**
 * @author changgg
 */
public class HelloHandler implements ICommandHandler<HelloHandler.HelloCmd> {
    @Override
    public void handler(HelloCmd cmd) {
        System.out.println("hello");
    }

    @Data
    static class HelloCmd implements ICmd {
    }
}
