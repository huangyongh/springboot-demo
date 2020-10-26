package com.hyh.web_flux.rotes;

import com.hyh.web_flux.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.MalformedURLException;

@Configuration
public class UserRoutes {
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> routerFunction(UserHandler userHandler){
        return RouterFunctions
                .route(RequestPredicates.GET("/api/users"),userHandler::getUserList)
                .and(RouterFunctions.route(RequestPredicates.GET("/api/user/{userId}"),userHandler::getUser));
    }

    /**
     * 资源路径映射
     * @return
     * @throws MalformedURLException
     */
    @Bean
    RouterFunction<ServerResponse> staticResourceRouter() throws MalformedURLException {
        return RouterFunctions.resources("/file/**",
                new FileUrlResource("F:/testImage/"));
    }

}
