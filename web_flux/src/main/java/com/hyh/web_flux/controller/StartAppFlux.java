package com.hyh.web_flux.controller;

import com.hyh.web_flux.entity.Man;
import com.hyh.web_flux.service.QueryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Objects;

@RestController
public class StartAppFlux {

    @Autowired
    private QueryUserService queryUserService;

    @GetMapping("/get_flux")
    public Flux<Man> getFlux(){
        return queryUserService.list();
    }

    @GetMapping("/get_flux_ids/{ids}")
    public Flux<Man> getFluxBuId(@PathVariable("ids") final String idStr){
        String[] testStr = idStr.split(",");
        Flux<String> ids = Flux.fromArray(testStr);
        return queryUserService.getById(ids);
    }

    @PostMapping("/get_mono")
    public Mono<Man> getMono(@RequestPart("id") String id){
        return queryUserService.grtById(id);
    }

    @PostMapping("/create_flux")
    public Flux<Man> createFlux(@RequestBody final Flux<Man> mans){
        return queryUserService.createOrUpdate(mans);
    }

    @PostMapping("/update_mono/{id}")
    public Mono<Man> updateMono(@PathVariable("id") final String id,@RequestBody final Man man){
        Objects.requireNonNull(man);
        man.setId(id);
        return queryUserService.createOrUpdate(man);
    }

    @GetMapping("/delete_mono/{id}")
    public Mono<Man> delete(@PathVariable("id") final String id){
        return queryUserService.delete(id);
    }


}
