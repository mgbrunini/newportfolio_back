/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.portfolio.cService;

import com.example.portfolio.aEntity.Proyecto;
import com.example.portfolio.bRepository.ProyectoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProyectoService {
    @Autowired
    ProyectoRepository proyectoRepository;
    
    public List<Proyecto> list(){
        return (List<Proyecto>) proyectoRepository.findAll();
    }
    
    public Optional<Proyecto> getOne(Long id){
        return proyectoRepository.findById(id);
    }
    
    public void save(Proyecto proyecto){
        proyectoRepository.save(proyecto);
    }
    
    public void delete(Long id){
        proyectoRepository.deleteById(id);
    }
    
    public boolean existsById(Long id){
        return proyectoRepository.existsById(id);
    }
}
