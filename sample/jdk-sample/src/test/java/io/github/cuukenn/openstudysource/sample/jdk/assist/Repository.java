package io.github.cuukenn.openstudysource.sample.jdk.assist;

public interface Repository<R, T> {
    R findOne(T t);
}
