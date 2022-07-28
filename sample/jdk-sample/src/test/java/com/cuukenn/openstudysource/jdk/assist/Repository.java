package com.cuukenn.openstudysource.jdk.assist;

public interface Repository<R, T> {
    R findOne(T t);
}