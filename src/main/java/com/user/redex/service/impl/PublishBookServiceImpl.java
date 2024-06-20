package com.user.redex.service.impl;

import com.user.redex.repository.AuthorRepository;
import com.user.redex.repository.BookRepository;
import com.user.redex.service.PublishBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nabeel Ahmed
 */
@Service
public class PublishBookServiceImpl implements PublishBookService {

    private Logger logger = LoggerFactory.getLogger(PublishBookServiceImpl.class);

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository patientRepository;


}
