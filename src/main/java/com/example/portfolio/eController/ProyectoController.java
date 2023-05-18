/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.portfolio.eController;

import com.example.portfolio.aEntity.Proyecto;
import com.example.portfolio.cService.ProyectoService;
import com.example.portfolio.dDTO.ProyectoDto;
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
@RequestMapping("/api/proyecto")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProyectoController {
   @Autowired
    ProyectoService proyectoService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = proyectoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}") //path 
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity("El proyecto no existe", HttpStatus.NOT_FOUND);
        }
        //En caso de que exista
        proyectoService.delete(id);
        return new ResponseEntity("Proyecto elimiando", HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProyectoDto proyectoDto){
        //Creamos el objeto vacio
        Proyecto proyecto = new Proyecto();
        //Completamos sus datos
        proyecto.setNombreProyecto(proyectoDto.getNombreProyecto());
        proyecto.setDescripcion(proyectoDto.getDescripcion());
        proyecto.setPersona(proyectoDto.getPersona());
        
        //Llamar al servicio para guardar
        proyectoService.save(proyecto);
        return new ResponseEntity("Proyecto creado con éxito", HttpStatus.OK);
        
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody ProyectoDto proyectoDto){
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity("El proyecto no existe", HttpStatus.NOT_FOUND);
        }
        //Si existe ese usuario
        //Llamamos al objeto
        Proyecto proyecto = proyectoService.getOne(id).get();
        //Completamos sus datos
        proyecto.setNombreProyecto(proyectoDto.getNombreProyecto());
        proyecto.setDescripcion(proyectoDto.getDescripcion());
        proyecto.setPersona(proyectoDto.getPersona());
        
        //Llamar al servicio para guardar
        proyectoService.save(proyecto);
        return new ResponseEntity("Proyecto actualizado con éxito", HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> detail(@PathVariable("id") Long id){
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity("El estudio no existe", HttpStatus.NOT_FOUND);
        }
        //Si existe en la base
        Proyecto estudio = proyectoService.getOne(id).get();
        return new ResponseEntity( estudio, HttpStatus.OK);
    } 
}
