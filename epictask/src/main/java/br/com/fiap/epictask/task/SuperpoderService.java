package br.com.fiap.epictask.task;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperpoderService {

    private final SuperpoderRepository superpoderRepository;

    public SuperpoderService(SuperpoderRepository superpoderRepository){
        this.superpoderRepository = superpoderRepository;
    }

    public List<Superpoder> getAllSuperpoderes(){
        return superpoderRepository.findAllByOrderByNivelInutilidadeDesc();
    }

}
