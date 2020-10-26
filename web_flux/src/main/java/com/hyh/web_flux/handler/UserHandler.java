package com.hyh.web_flux.handler;

import com.hyh.web_flux.entity.User;
import com.hyh.web_flux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    @Autowired
    private UserService userService;

    public Mono<ServerResponse> getUserList(ServerRequest request){
        Flux<User> userFlux = userService.findUserList();
        return ServerResponse.ok().body(userFlux,User.class);
    }

    public Mono<ServerResponse> getUser(ServerRequest request){
        String userId = request.pathVariable("userId");
        Mono<User> userMono = userService.findUserById(userId);
        return ServerResponse.ok().body(userMono,User.class);
    }
}
