package com.cuukenn.openstudysource.jdk.spi.plugin;

import com.cuukenn.openstudysource.jdk.spi.metadata.IPluginService;

/**
 * @author changgg
 */
public class CodePluginServiceImpl implements IPluginService {
    @Override
    public String getPluginName() {
        return "code-plugin";
    }
}
