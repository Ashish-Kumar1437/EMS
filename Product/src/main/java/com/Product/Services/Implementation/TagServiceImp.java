package com.Product.Services.Implementation;

import com.Product.Models.Entities.TagEntity;
import com.Product.Repositories.TagRepository;
import com.Product.Services.Interfaces.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagServiceImp implements TagService {

    TagRepository tagRepository;

    @Override
    public Boolean addTags(List<TagEntity> tags) {
        tagRepository.saveAll(tags);
        return true;
    }

    @Override
    public List<TagEntity> getTags(){
        return tagRepository.findAll();
    }
}
