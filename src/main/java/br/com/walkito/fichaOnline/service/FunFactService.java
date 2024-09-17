package br.com.walkito.fichaOnline.service;

import br.com.walkito.fichaOnline.model.entities.FunFact;
import br.com.walkito.fichaOnline.model.repositorys.FunFactRepository;
import br.com.walkito.fichaOnline.service.exception.ExceptionConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@Service
public class FunFactService {
    @Autowired
    FunFactRepository repository;

    public ResponseEntity getAllFacts(){
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity getRandomFact(){
        Optional<FunFact> finalFunFact;
        try{
            Optional<FunFact> funFact = repository.findLastId();
            int maxID = 0;

            if(funFact.isPresent()){
                maxID = funFact.get().getId();
            }

            finalFunFact  = selectFunFac(maxID);

            while(finalFunFact.isEmpty()){
                finalFunFact = selectFunFac(maxID);
            }

        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
            return ResponseEntity.ok(finalFunFact);
    }

    public ResponseEntity createFact(FunFact funFact){
        FunFact newFunFact;
        try{
            newFunFact = repository.save(funFact);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok().body(newFunFact);
    }

    public ResponseEntity editFact(FunFact funFact) {
        FunFact editedFunFact;
        try {
            editedFunFact = repository.save(funFact);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok(editedFunFact);
    }

    public ResponseEntity deleteFact(int id){
        try{
            Optional<FunFact> funFact = repository.findById(id);
            if(funFact.isPresent()){
                repository.delete(funFact.get());
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok(true);
    }

    private Optional<FunFact> selectFunFac(int maxID){
        Random random = new Random();
        int randomID = random.nextInt(0, maxID+1);
        return repository.findById(randomID);
    }
}
