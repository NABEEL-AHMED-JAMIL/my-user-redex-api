package com.user.redex.service.impl;

import com.user.redex.dto.AuthorDto;
import com.user.redex.repository.AuthorRepository;
import com.user.redex.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author Nabeel Ahmed
 */
@Service
@Resource(name="authorService")
public class AuthorServiceImpl implements AuthorService {

    private Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public AuthorDto createEntity(AuthorDto entity) {
        return null;
    }

    @Override
    public AuthorDto updateEntity(Long id, AuthorDto entity) {
        return null;
    }

    @Override
    public void deleteEntity(Long id) {

    }

    @Override
    public AuthorDto getEntity(Long id) {
        return null;
    }

    @Override
    public List<AuthorDto> getAllEntities() {
        return null;
    }
}
