package br.com.walkito.fichaOnline.controller;

import br.com.walkito.fichaOnline.model.entities.FunFact;
import br.com.walkito.fichaOnline.service.FunFactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/funfact")
public class FunFactController {
    @Autowired
    private FunFactService service;

    @GetMapping
    public ResponseEntity getAllFacts(){
        return service.getAllFacts();
    }

    @GetMapping(path = "/randomFact")
    public ResponseEntity getRandomFact(){
        return service.getRandomFact();
    }

    @PostMapping(path = "/create")
    public ResponseEntity createFact(@RequestBody @Valid FunFact funFact){
        return service.createFact(funFact);
    }

    @PutMapping(path = "/edit")
    public ResponseEntity editFact(@RequestBody @Valid FunFact funFact){
        return service.editFact(funFact);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity deleteFact(@RequestParam(name = "id") int id){
        return service.deleteFact(id);
    }
}
