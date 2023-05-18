/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.portfolio.eController;

import com.example.portfolio.aEntity.Estudio;
import com.example.portfolio.cService.EstudioService;
import com.example.portfolio.dDTO.EstudioDto;
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
@RequestMapping("/api/estudio")
@CrossOrigin(origins = {"http://localhost:4200"})
public class EstudioController {
    @Autowired
    EstudioService estudioService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Estudio>> list(){
        List<Estudio> list = estudioService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}") //path 
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if (!estudioService.existsById(id)) {
            return new ResponseEntity("El estudio no existe", HttpStatus.NOT_FOUND);
        }
        //En caso de que exista
        estudioService.delete(id);
        return new ResponseEntity("Estudio elimiando", HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EstudioDto estudioDto){
        //Creamos el objeto vacio
        Estudio estudio = new Estudio();
        //Completamos sus datos
        estudio.setNombreEstudio(estudioDto.getNombreEstudio());
        estudio.setDescripcion(estudioDto.getDescripcion());
        estudio.setPersona(estudioDto.getPersona());
        
        //Llamar al servicio para guardar
        estudioService.save(estudio);
        return new ResponseEntity("Estudio creado con éxito", HttpStatus.OK);
        
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody EstudioDto estudioDto){
        if (!estudioService.existsById(id)) {
            return new ResponseEntity("El estudio no existe", HttpStatus.NOT_FOUND);
        }
        //Si existe ese usuario
        //Llamamos al objeto
        Estudio estudio = estudioService.getOne(id).get();
        //Completamos sus datos
        estudio.setNombreEstudio(estudioDto.getNombreEstudio());
        estudio.setDescripcion(estudioDto.getDescripcion());
        estudio.setPersona(estudioDto.getPersona());
        
        //Llamar al servicio para guardar
        estudioService.save(estudio);
        return new ResponseEntity("Estudio actualizado con éxito", HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Estudio> detail(@PathVariable("id") Long id){
        if (!estudioService.existsById(id)) {
            return new ResponseEntity("El estudio no existe", HttpStatus.NOT_FOUND);
        }
        //Si existe en la base
        Estudio estudio = estudioService.getOne(id).get();
        return new ResponseEntity( estudio, HttpStatus.OK);
    }
}
