package com.example.memnuniyetanketi.Services;


import com.example.memnuniyetanketi.Model.Anket;
import com.example.memnuniyetanketi.Repository.AnketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnketService {

    @Autowired
    private AnketRepository anketRepository;

    public List<Anket> listAnket() {
        return anketRepository.findAll();
    }

    public Anket createAnket(Anket anket) {
        return anketRepository.save(anket);
    }

    public Anket getAnket(Long id) {
        return anketRepository.findById(id).get();
    }

    public void deleteAnket(Long id) {
        anketRepository.deleteById(id);
    }

    public Anket updateAnket( Anket anket) {
        return anketRepository.save(anket);
    }

    public List<Anket> searchAnket(String name){
        return anketRepository.findByNameContainingIgnoreCase(name);
    }

}
