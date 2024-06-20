package com.user.redex.service.impl;

import com.user.redex.dto.BookDto;
import com.user.redex.repository.BookRepository;
import com.user.redex.service.BookService;
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
@Resource(name="bookService")
public class BookServiceImpl implements BookService {

    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository patientRepository;

    @Override
    public BookDto createEntity(BookDto entity) {
        return null;
    }

    @Override
    public BookDto updateEntity(Long id, BookDto entity) {
        return null;
    }

    @Override
    public void deleteEntity(Long id) {
    }

    @Override
    public BookDto getEntity(Long id) {
        return null;
    }

    @Override
    public List<BookDto> getAllEntities() {
        return null;
    }
}
