package com.cuukenn.openstudysource.sample.caffeine.management;

import com.cuukenn.openstudysource.sample.caffeine.mxbean.CaffeineSamplerMXBean;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * 注册MXBean
 *
 * @author changgg
 */
public final class CaffeineMXbeanRegister {
    private CaffeineMXbeanRegister() {
    }

    /**
     * 注册MXBean
     */
    public static void register(CaffeineSamplerMXBean mxBean) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("javax.cache:type=caffeineCacheSampler,name=" + mxBean.getCacheName());
        mbs.registerMBean(mxBean, objectName);
    }
}
