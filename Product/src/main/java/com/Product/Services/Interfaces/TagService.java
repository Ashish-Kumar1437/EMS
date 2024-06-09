package com.Product.Services.Interfaces;

import com.Product.Models.Entities.TagEntity;

import java.util.List;

public interface TagService {

    Boolean addTags(List<TagEntity> tags);

    List<TagEntity> getTags();
}
