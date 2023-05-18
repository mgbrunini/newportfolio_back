package com.example.portfolio.eController;

import com.example.portfolio.aEntity.Persona;
import com.example.portfolio.cService.PersonaService;
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
@RequestMapping("/api/persona")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PersonaController {
    @Autowired
    PersonaService personaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}") //path 
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if (!personaService.existsById(id)) {
            return new ResponseEntity("El usuario no existe", HttpStatus.NOT_FOUND);
        }
        //En caso de que exista
        personaService.delete(id);
        return new ResponseEntity("Persona elimianda", HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PersonaDto personaDto){
        //Creamos el objeto vacio
        Persona persona = new Persona();
        //Completamos sus datos
        persona.setNombre(personaDto.getNombre());
        persona.setApellido(personaDto.getApellido());
        persona.setCorreo(personaDto.getCorreo());
        persona.setNombreUsuario(personaDto.getNombreUsuario());
        persona.setContraseña(personaDto.getContraseña());
        persona.setAcercaDe(personaDto.getAcercaDe());
        
        //Llamar al servicio para guardar
        personaService.save(persona);
        return new ResponseEntity("Persona creada con éxito", HttpStatus.OK);
        
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody PersonaDto personaDto){
        if (!personaService.existsById(id)) {
            return new ResponseEntity("El usuario no existe", HttpStatus.NOT_FOUND);
        }
        //Si existe ese usuario
        //Llamamos al objeto
        Persona persona = personaService.getOne(id).get();
        //Completamos sus datos
        persona.setNombre(personaDto.getNombre());
        persona.setApellido(personaDto.getApellido());
        persona.setCorreo(personaDto.getCorreo());
        persona.setNombreUsuario(personaDto.getNombreUsuario());
        persona.setContraseña(personaDto.getContraseña());
        persona.setAcercaDe(personaDto.getAcercaDe());
        
        //Llamar al servicio para guardar
        personaService.save(persona);
        return new ResponseEntity("Persona actualizada con éxito", HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> detail(@PathVariable("id") Long id){
        if (!personaService.existsById(id)) {
            return new ResponseEntity("El usuario no existe", HttpStatus.NOT_FOUND);
        }
        //Si existe en la base
        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity( persona, HttpStatus.OK);
    }
}
