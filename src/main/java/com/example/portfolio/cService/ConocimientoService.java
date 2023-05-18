/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.portfolio.cService;

import com.example.portfolio.aEntity.Conocimiento;
import com.example.portfolio.bRepository.ConocimientoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ConocimientoService {
    @Autowired
    ConocimientoRepository conocimientoRepository;
    
    public List<Conocimiento> list(){
        return (List<Conocimiento>) conocimientoRepository.findAll();
    }
    
    public Optional<Conocimiento> getOne(Long id){
        return conocimientoRepository.findById(id);
    }
    
    public void save(Conocimiento conocimiento){
        conocimientoRepository.save(conocimiento);
    }
    
    public void delete(Long id){
        conocimientoRepository.deleteById(id);
    }
    
    public boolean existsById(Long id){
        return conocimientoRepository.existsById(id);
    }
}
