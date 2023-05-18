/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.portfolio.eController;

import com.example.portfolio.aEntity.Trabajo;
import com.example.portfolio.cService.TrabajoService;
import com.example.portfolio.dDTO.TrabajoDto;
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
@RequestMapping("/api/trabajo")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TrabajoController {
    @Autowired
    TrabajoService trabajoService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Trabajo>> list(){
        List<Trabajo> list = trabajoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}") //path 
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if (!trabajoService.existsById(id)) {
            return new ResponseEntity("El trabajo no existe", HttpStatus.NOT_FOUND);
        }
        //En caso de que exista
        trabajoService.delete(id);
        return new ResponseEntity("Trabajo elimiando", HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TrabajoDto trabajoDto){
        //Creamos el objeto vacio
        Trabajo trabajo = new Trabajo();
        //Completamos sus datos
        trabajo.setNombreTrabajo(trabajoDto.getNombreTrabajo());
        trabajo.setDescripcion(trabajoDto.getDescripcion());
        trabajo.setPersona(trabajoDto.getPersona());
        
        //Llamar al servicio para guardar
        trabajoService.save(trabajo);
        return new ResponseEntity("Trabajo creado con éxito", HttpStatus.OK);
        
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody TrabajoDto trabajoDto){
        if (!trabajoService.existsById(id)) {
            return new ResponseEntity("El trabajo no existe", HttpStatus.NOT_FOUND);
        }
        //Si existe ese usuario
        //Llamamos al objeto
        Trabajo trabajo = trabajoService.getOne(id).get();
        //Completamos sus datos
        trabajo.setNombreTrabajo(trabajoDto.getNombreTrabajo());
        trabajo.setDescripcion(trabajoDto.getDescripcion());
        trabajo.setPersona(trabajoDto.getPersona());
        
        //Llamar al servicio para guardar
        trabajoService.save(trabajo);
        return new ResponseEntity("Trabajo actualizado con éxito", HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Trabajo> detail(@PathVariable("id") Long id){
        if (!trabajoService.existsById(id)) {
            return new ResponseEntity("El estudio no existe", HttpStatus.NOT_FOUND);
        }
        //Si existe en la base
        Trabajo estudio = trabajoService.getOne(id).get();
        return new ResponseEntity( estudio, HttpStatus.OK);
    } 
}
