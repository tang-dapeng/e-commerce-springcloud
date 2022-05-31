package com.imooc.ecommerce.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.NacosConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 通过nacos 下发动态路由配置，监听 Nacos 中配置变更
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/16
 */
@Slf4j
@Component
@DependsOn({"gatewayConfig"})
public class DynamicRouteServiceImplNacos {
    /** Nacos 配置服务 */
    private ConfigService configService;

    /**  */
    @Resource
    private  DynamicRouteServiceImpl dynamicRouteService;

    /**
     * bean在容器中构造完成之后会执行 init() 方法
     */
    @PostConstruct
    private void init() {
        log.info("gateway route init ... ");
        try {
            configService = initConfigService();
            if (null == configService) {
                log.error("init config service fail");
            }
            String config = configService.getConfig(
                    GatewayConfig.NACOS_ROUTE_DATA_ID,
                    GatewayConfig.NACOS_GROUP,
                    GatewayConfig.DEFAULT_TIMEOUT);
            log.info("get gateway config: [{}]",config);
            List<RouteDefinition> list = JSON.parseArray(config,RouteDefinition.class);
            list.stream().forEach(item -> {
                log.info("init gateway config: [{}]",item.toString());
                dynamicRouteService.addRouteDefinition(item);
            });

            dynamicRouteByNacosListener(GatewayConfig.NACOS_ROUTE_DATA_ID,GatewayConfig.NACOS_GROUP);
        } catch (NacosException e) {
            log.error("gateway route init some error: [{}]",e.getMessage(),e);
        }


    }

    /**
     * 初始化 nacos config
     * @return
     */
    private ConfigService initConfigService() {
        try{
            Properties properties = new Properties();
            properties.setProperty("serverAddr",GatewayConfig.NACOS_SERVER_ADDR);
            properties.setProperty("namespace",GatewayConfig.NACOS_NAMESPACE);
            return configService = NacosFactory.createConfigService(properties);

        } catch (Exception e) {
            log.error("init gateway nacos config errot: [{}]",e.getMessage(),e);
            return null;
        }
    }

    /**
     * 监听 nacos 动态路由配置变化，并发布到gateway
     */
    private void dynamicRouteByNacosListener(String dataId,String group) {
        try {
            // 给 nacos 客户端配置一个监听器
            configService.addListener(dataId, group, new Listener() {
                /**
                 * 自己提供线程池执行操作
                 * @return
                 */
                @Override
                public Executor getExecutor() {
                    return null;
                }

                /**
                 * 监听器收到配置更新
                 * @param s
                 */
                @Override
                public void receiveConfigInfo(String s) {
                    log.info("start to update config: [{}]",s);
                    List<RouteDefinition> definitionList = JSON.parseArray(s,RouteDefinition.class);
                    dynamicRouteService.updateList(definitionList);
                }
            });
        } catch (NacosException e) {
            log.error("");
        }


    }

}
