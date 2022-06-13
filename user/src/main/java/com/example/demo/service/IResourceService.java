package com.example.demo.service;

import com.example.demo.model.Resource;

import java.util.List;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:40
 * \* Description:
 * \
 */
public interface IResourceService {
    List<Resource> queryAllResources();

    Resource getResourceById(String id);

    void insertResource(Resource resource) throws Exception;

    void updateResource(Resource resource) throws Exception;

    void deleteResources(List<String> resourceIds);
}
