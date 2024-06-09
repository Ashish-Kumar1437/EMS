package com.Product.Controller;

import com.Product.Models.Entities.TagEntity;
import com.Product.Services.Implementation.TagServiceImp;
import com.Product.Services.Interfaces.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@CrossOrigin("*")
@AllArgsConstructor
public class TagController {

    TagService tagServiceImp;

    @PostMapping("/add")
    boolean addTags(@RequestBody List<TagEntity> tags){
        return tagServiceImp.addTags(tags);
    }

    @GetMapping("")
    List<TagEntity> getTags(){
        return tagServiceImp.getTags();
    }
}
