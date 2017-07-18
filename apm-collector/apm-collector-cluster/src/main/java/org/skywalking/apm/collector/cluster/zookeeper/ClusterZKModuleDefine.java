package org.skywalking.apm.collector.cluster.zookeeper;

import org.skywalking.apm.collector.client.zookeeper.ZookeeperClient;
import org.skywalking.apm.collector.cluster.ClusterModuleGroupDefine;
import org.skywalking.apm.collector.core.client.Client;
import org.skywalking.apm.collector.core.cluster.ClusterDataInitializer;
import org.skywalking.apm.collector.core.cluster.ClusterModuleDefine;
import org.skywalking.apm.collector.core.cluster.ClusterModuleRegistrationReader;
import org.skywalking.apm.collector.core.cluster.ClusterModuleRegistrationWriter;
import org.skywalking.apm.collector.core.module.ModuleConfigParser;

/**
 * @author pengys5
 */
public class ClusterZKModuleDefine extends ClusterModuleDefine {

    public static final String MODULE_NAME = "zookeeper";

    @Override protected String group() {
        return ClusterModuleGroupDefine.GROUP_NAME;
    }

    @Override public String name() {
        return MODULE_NAME;
    }

    @Override public boolean defaultModule() {
        return false;
    }

    @Override protected ModuleConfigParser configParser() {
        return new ClusterZKConfigParser();
    }

    @Override protected Client createClient() {
        return new ZookeeperClient(ClusterZKConfig.HOST_PORT, ClusterZKConfig.SESSION_TIMEOUT);
    }

    @Override protected ClusterDataInitializer dataInitializer() {
        return new ClusterZKDataInitializer();
    }

    @Override public ClusterModuleRegistrationWriter registrationWriter() {
        return new ClusterZKModuleRegistrationWriter(getClient());
    }

    @Override public ClusterModuleRegistrationReader registrationReader() {
        return null;
    }
}
