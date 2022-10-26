package io.github.cuukenn.openstudysource.sample.jdk.spi.plugin;

import io.github.cuukenn.openstudysource.sample.jdk.spi.metadata.IPluginService;

/**
 * @author changgg
 */
public class CodePluginServiceImpl implements IPluginService {
    @Override
    public String getPluginName() {
        return "code-plugin";
    }
}
