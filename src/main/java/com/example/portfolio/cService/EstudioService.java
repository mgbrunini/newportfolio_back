/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.portfolio.cService;

import com.example.portfolio.aEntity.Estudio;
import com.example.portfolio.bRepository.EstudioRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EstudioService {
    @Autowired
    EstudioRepository estudioRepository;
    
    public List<Estudio> list(){
        return (List<Estudio>) estudioRepository.findAll();
    }
    
    public Optional<Estudio> getOne(Long id){
        return estudioRepository.findById(id);
    }
    
    public void save(Estudio estudio){
        estudioRepository.save(estudio);
    }
    
    public void delete(Long id){
        estudioRepository.deleteById(id);
    }
    
    public boolean existsById(Long id){
        return estudioRepository.existsById(id);
    }
}
