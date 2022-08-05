package com.cuukenn.openstudysource.sample.jdk.assist;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
    String value();
}