/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.portfolio.cService;

import com.example.portfolio.aEntity.Trabajo;
import com.example.portfolio.bRepository.TrabajoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TrabajoService {
    @Autowired
    TrabajoRepository trabajoRepository;
    
    public List<Trabajo> list(){
        return (List<Trabajo>) trabajoRepository.findAll();
    }
    
    public Optional<Trabajo> getOne(Long id){
        return trabajoRepository.findById(id);
    }
    
    public void save(Trabajo trabajo){
        trabajoRepository.save(trabajo);
    }
    
    public void delete(Long id){
        trabajoRepository.deleteById(id);
    }
    
    public boolean existsById(Long id){
        return trabajoRepository.existsById(id);
    }
}
