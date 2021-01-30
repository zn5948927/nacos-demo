package com.gd.gateway.internal.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * @Title:
 * @Description: 路由类配置
 * @Author
 * @Date 2021-01-27
 * @Version 0.0.1
 * @Company: 北京爱车家网络科技有限公司
 **/
@Configuration
public class GatewayConfiguration {
    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public static final long DEFAULT_TIMEOUT = 30000;

    public static String NACOS_SERVER_ADDR;

    public static String NACOS_NAMESPACE;

    public static String NACOS_ROUTE_DATA_ID;

    public static String NACOS_ROUTE_GROUP;

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    public void setNacosServerAddr(String nacosServerAddr) {
        NACOS_SERVER_ADDR = nacosServerAddr;
    }

    @Value("${spring.cloud.nacos.discovery.namespace}")
    public void setNacosNamespace(String nacosNamespace) {
        NACOS_NAMESPACE = nacosNamespace;
    }

    @Value("${gd.gateway.routes.data-id}")
    public void setNacosRouteDataId(String nacosRouteDataId) {
        NACOS_ROUTE_DATA_ID = nacosRouteDataId;
    }

    @Value("${gd.gateway.routes.group}")
    public void setNacosRouteGroup(String nacosRouteGroup) {
        NACOS_ROUTE_GROUP = nacosRouteGroup;
    }

    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        // Register the block exception handler for Spring Cloud Gateway.
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }
}
