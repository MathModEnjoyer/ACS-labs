package com.example.demo.controller.rest;

import com.example.demo.dto.SailorDto;
import com.example.demo.service.SailorService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.ClassPathResource;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@RestController
@RequestMapping(value = "/rest/sailors")
@RequiredArgsConstructor
public class SailorRestController {
    private final SailorService sailorService;
    private final XmlMapper xmlMapper = new XmlMapper();

    @GetMapping(path = "", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findAllXml() throws TransformerException, IOException {
        xmlMapper.registerModule(new JavaTimeModule());
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        ClassPathResource xslt = new ClassPathResource("static/sailor.xslt");
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslt.getInputStream()));
        Source xmlSource = new StreamSource(new ByteArrayInputStream(xmlMapper.writeValueAsBytes(sailorService.findAll())));
        StringWriter out = new StringWriter();
        Result result = new StreamResult(out);
        transformer.transform(xmlSource, result);
        return ResponseEntity.status(HttpStatus.OK).body(out.toString());
    }

    @GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllJson() {
        return ResponseEntity.status(HttpStatus.OK).body(sailorService.findAll());
    }

    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        SailorDto sailorDto = sailorService.findById(id);
        if (sailorDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(sailorDto);
    }

    @PostMapping(path = "", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> save(@RequestBody SailorDto sailorDto) {
        sailorService.save(sailorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        sailorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody SailorDto sailorDto) {
        sailorDto.setId(id);
        sailorService.save(sailorDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
