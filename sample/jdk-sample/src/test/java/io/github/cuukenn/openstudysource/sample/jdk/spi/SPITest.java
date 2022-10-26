package io.github.cuukenn.openstudysource.sample.jdk.spi;

import io.github.cuukenn.openstudysource.sample.jdk.spi.metadata.IPluginService;
import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @author changgg
 */
public class SPITest {
    @Test
    public void test_spi() {
        ServiceLoader<IPluginService> pluginServices = ServiceLoader.load(IPluginService.class);
        for (IPluginService pluginService : pluginServices) {
            System.out.println(pluginService.getPluginName());
        }
    }
}
