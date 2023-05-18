/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.portfolio.eController;

import com.example.portfolio.aEntity.Conocimiento;
import com.example.portfolio.aEntity.Persona;
import com.example.portfolio.cService.ConocimientoService;
import com.example.portfolio.dDTO.ConocimientoDto;
import com.example.portfolio.dDTO.PersonaDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conocimiento")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ConocimientoController {
    @Autowired
    ConocimientoService conocimientoService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Conocimiento>> list(){
        List<Conocimiento> list = conocimientoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}") //path 
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if (!conocimientoService.existsById(id)) {
            return new ResponseEntity("El conocimiento no existe", HttpStatus.NOT_FOUND);
        }
        //En caso de que exista
        conocimientoService.delete(id);
        return new ResponseEntity("Conocimiento elimiando", HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ConocimientoDto conocimientoDto){
        //Creamos el objeto vacio
        Conocimiento conocimiento = new Conocimiento();
        //Completamos sus datos
        conocimiento.setNombreConocimiento(conocimientoDto.getNombreConocimiento());
        conocimiento.setDescripcion(conocimientoDto.getDescripcion());
        conocimiento.setPersona(conocimientoDto.getPersona());
        
        //Llamar al servicio para guardar
        conocimientoService.save(conocimiento);
        return new ResponseEntity("Conocimiento creado con éxito", HttpStatus.OK);
        
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody ConocimientoDto conocimientoDto){
        if (!conocimientoService.existsById(id)) {
            return new ResponseEntity("El conocimiento no existe", HttpStatus.NOT_FOUND);
        }
        //Si existe ese usuario
        //Llamamos al objeto
        Conocimiento conocimiento = conocimientoService.getOne(id).get();
        //Completamos sus datos
        conocimiento.setNombreConocimiento(conocimientoDto.getNombreConocimiento());
        conocimiento.setDescripcion(conocimientoDto.getDescripcion());
        conocimiento.setPersona(conocimientoDto.getPersona());
        
        //Llamar al servicio para guardar
        conocimientoService.save(conocimiento);
        return new ResponseEntity("Conocimiento actualizado con éxito", HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> detail(@PathVariable("id") Long id){
        if (!conocimientoService.existsById(id)) {
            return new ResponseEntity("El conocimiento no existe", HttpStatus.NOT_FOUND);
        }
        //Si existe en la base
        Conocimiento conocimiento = conocimientoService.getOne(id).get();
        return new ResponseEntity( conocimiento, HttpStatus.OK);
    }
}
