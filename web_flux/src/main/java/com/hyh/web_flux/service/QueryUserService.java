package com.hyh.web_flux.service;

import com.hyh.web_flux.entity.Man;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class QueryUserService {
    private final Map<String,Man> data = new ConcurrentHashMap<>();

    @PostConstruct
    void init(){
        Man man = new Man("1","mick",90,"1234683132");
        Man man1 = new Man("2","lmy",78,"454548856565");
        Man man2 = new Man("3","hdsjn",78,"151545454");
        data.put(man.getId(),man);
        data.put(man1.getId(),man1);
        data.put(man2.getId(),man2);
    }

    public Flux<Man> list(){
        return Flux.fromIterable(this.data.values());
    }

    public Flux<Man> getById(final Flux<String> ids){
        return ids.flatMap(id -> Mono.justOrEmpty(this.data.get(id)));
    }

    public Mono<Man> grtById(final String id){
        return Mono.justOrEmpty(this.data.get(id))
                .switchIfEmpty(Mono.error(new RuntimeException()));
    }

    public Flux<Man> createOrUpdate(final Flux<Man> mans){
        return mans.doOnNext(man -> this.data.put(man.getId(),man));
    }

    public Mono<Man> createOrUpdate(final Man man){
        this.data.put(man.getId(),man);
        return Mono.just(man);
    }

    public Mono<Man> delete(final String id){
        return Mono.justOrEmpty(this.data.remove(id));
    }

}
