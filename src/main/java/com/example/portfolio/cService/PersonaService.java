/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.portfolio.cService;

import com.example.portfolio.aEntity.Persona;
import com.example.portfolio.bRepository.PersonaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;
    
    public List<Persona> list(){
        return (List<Persona>) personaRepository.findAll();
    }
    
    public Optional<Persona> getOne(Long id){
        return personaRepository.findById(id);
    }
    
    public void save(Persona persona){
        personaRepository.save(persona);
    }
    
    public void delete(Long id){
        personaRepository.deleteById(id);
    }
    
    public boolean existsById(Long id){
        return personaRepository.existsById(id);
    }
}
