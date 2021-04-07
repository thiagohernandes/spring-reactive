package com.reactive.blocking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blocking")
@Slf4j
public class Exemplo1Blocking {

    @GetMapping()
    public ResponseEntity<List<Integer>> getNumeros() throws Exception {
        try {
            List<Integer> lista = new ArrayList<>();
            for (int x = 0; x < 10; x++) {
                Thread.sleep(1000L);
                lista.add(x);
            }
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            log.error("Problemas: {}", e);
            throw new Exception("Erro na chamada!!");
        }
    }
}
