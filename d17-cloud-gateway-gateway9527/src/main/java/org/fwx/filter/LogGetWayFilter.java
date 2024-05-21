package org.fwx.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName LogGetWayFilter
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/19 17:37
 * @Version 1.0
 */
@Configuration
@Slf4j
public class LogGetWayFilter implements GlobalFilter, Ordered {
    /**
     * 对请求进行过滤，检查请求中是否包含有效的用户名。
     * 例：http://localhost:9527/payment/lb?username=fwx
     *
     * @param exchange 服务器web交换机，用于访问请求和响应信息。
     * @param chain 网关过滤器链，用于继续处理过滤链中的下一个过滤器，或者终止处理。
     * @return Mono<Void> 表示异步操作完成，不返回任何结果。
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("******* LogGetWayFilter"); // 日志记录，表示进入过滤器

        // 从请求的查询参数中获取用户名
        String username = exchange.getRequest().getQueryParams().getFirst("username");

        if (username == null) {
            log.info("******* 用户名不能为空"); // 日志记录，表示用户名缺失

            // 设置响应状态码为406（不可接受），并结束响应
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }

        // 如果用户名存在，继续处理下一个过滤器
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
