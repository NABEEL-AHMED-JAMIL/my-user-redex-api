package com.user.redex.business.service.impl;

import com.user.redex.business.converter.AuthorConverter;
import com.user.redex.business.converter.BookConverter;
import com.user.redex.business.document.Author;
import com.user.redex.business.dto.response.AuthorListResponse;
import com.user.redex.business.dto.request.AuthorRequest;
import com.user.redex.business.dto.response.GQLResponse;
import com.user.redex.business.dto.response.AuthorResponse;
import com.user.redex.business.enums.Status;
import com.user.redex.business.repository.AuthorRepository;
import com.user.redex.business.service.AuthorService;
import com.user.redex.manager.remote.RemoteFileExchange;
import com.user.redex.util.ReduxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Nabeel Ahmed
 */
@Service
@Resource(name="authorService")
public class AuthorServiceImpl implements AuthorService {

    private Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorConverter authorConverter;
    @Autowired
    private BookConverter bookConverter;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private RemoteFileExchange remoteFileExchange;

    public AuthorServiceImpl() {
    }

    /**
     * Method use to create the author/register
     * Basic validation on ui/form side here double check
     * @param request
     * @return QLResponse<AuthorResponse>
     * @throws Exception
     * */
    @Override
    public GQLResponse<AuthorResponse> createEntity(AuthorRequest request) throws Exception {
        logger.info("Request For New Author :- " + request);
        if (ReduxUtil.isNull(request.getFirstName())) {
            return new GQLResponse("Author firstName required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getLastName())) {
            return new GQLResponse("Author lastName required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getEmail())) {
            return new GQLResponse("Author email required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getUsername())) {
            return new GQLResponse("Author username required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getPassword())) {
            return new GQLResponse("Author password required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getBiography())) {
            return new GQLResponse("Author biography required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getNationality())) {
            return new GQLResponse("Author nationality required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getExpertise())) {
            return new GQLResponse("Author expertise required.", ReduxUtil.ERROR);
        }
        // db check author email and username
        if (this.authorRepository.findByEmail(request.getEmail()).isPresent()) {
            return new GQLResponse("Author email already exist.", ReduxUtil.ERROR);
        } else if (this.authorRepository.findByUsername(request.getUsername()).isPresent()) {
            return new GQLResponse("Author username already exist.", ReduxUtil.ERROR);
        }
        Author author = this.authorConverter.convertToAuthor(request, new Author());
        author.setStatus(Status.ACTIVE);
        author = this.authorRepository.save(author);
        AuthorResponse authorResponse = this.authorConverter.convertToAuthor(author);
        return new GQLResponse("Author save successfully.", ReduxUtil.SUCCESS, authorResponse);
    }

    /**
     * Method use to update the author
     * @param request
     * @return QLResponse<AuthorResponse>
     * @throws Exception
     * */
    @Override
    public GQLResponse<AuthorResponse> updateEntity(AuthorRequest request) throws Exception {
        logger.info("Request For Update Author :- " + request);
         if (ReduxUtil.isNull(request.getEmail())) {
            return new GQLResponse("Author email required.", ReduxUtil.ERROR);
        }
        Optional<Author> author = this.authorRepository.findByEmailAndStatusNot(request.getEmail(), Status.DELETE);
        // db check author email and username
        if (!author.isPresent()) {
            return new GQLResponse("Author email not found.", ReduxUtil.ERROR);
        }
        if (ReduxUtil.isNull(request.getFirstName())) {
            return new GQLResponse("Author firstName required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getLastName())) {
            return new GQLResponse("Author lastName required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getBiography())) {
            return new GQLResponse("Author biography required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getNationality())) {
            return new GQLResponse("Author nationality required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getExpertise())) {
            return new GQLResponse("Author expertise required.", ReduxUtil.ERROR);
        }
        author.get().setFirstName(request.getFirstName());
        author.get().setLastName(request.getLastName());
        author.get().setBiography(request.getBiography());
        author.get().setNationality(request.getNationality());
        author.get().setExpertise(request.getExpertise());
        this.authorRepository.save(author.get());
        AuthorResponse authorResponse = this.authorConverter.convertToAuthor(author.get());
        return new GQLResponse("Author update successfully.", ReduxUtil.SUCCESS, authorResponse);
    }

    /**
     * Method use to delete the author by id
     * @param id
     * @return QLResponse<AuthorResponse>
     * @throws Exception
     * */
    @Override
    public GQLResponse<AuthorResponse> deleteEntity(String id) throws Exception {
        logger.info("Request For Delete Author BY ID :- " + id);
        // check if author active | inactive and exist in db
        Optional<Author> author = this.authorRepository.findByIdAndStatusNot(id, Status.DELETE);
        if (!author.isPresent()) {
            return new GQLResponse("Author not found.", ReduxUtil.ERROR);
        }
        author.get().setStatus(Status.DELETE);
        this.authorRepository.save(author.get());
        return new GQLResponse("Author delete successfully.", ReduxUtil.SUCCESS);
    }

    /**
     * Method use to get the author by id
     * @param id
     * @return QLResponse<AuthorResponse>
     * @throws Exception
     * */
    @Override
    public GQLResponse<AuthorResponse> getEntity(String id) throws Exception {
        logger.info("Request For Get Author BY ID :- " + id);
        // check if author active | inactive and exist in db
        Optional<Author> author = this.authorRepository.findByIdAndStatusNot(id, Status.DELETE);
        if (!author.isPresent()) {
            return new GQLResponse("Author not found.", ReduxUtil.ERROR);
        }
        return new GQLResponse("Author fetch successfully.", ReduxUtil.SUCCESS,
            this.getAuthorResponse(author.get()));
    }

    /**
     * Method use to get all author
     * @return QLResponse<List<AuthorResponse>>
     * @throws Exception
     * */
    @Override
    public GQLResponse<AuthorListResponse> getAllEntities() throws Exception {
        logger.info("Request For Get All Authors :- ");
        List<AuthorResponse> authorResponses = this.authorRepository.findAllByStatusNot(Status.DELETE)
            .stream()
            .filter(author -> author.getStatus().equals(Status.ACTIVE))
            .map(author -> this.getAuthorResponse(author)).collect(Collectors.toList());
        return new GQLResponse("Authors fetch successfully.", ReduxUtil.SUCCESS,
            new AuthorListResponse(authorResponses));
    }

    /**
     * Method use to upload the author image
     * @param file
     * @param request
     * @return AuthorResponse
     * */
    @Override
    public GQLResponse<AuthorResponse> uploadAuthorImage(MultipartFile file, AuthorRequest request) throws Exception {
        logger.info("Request For upload author image :- " + request);
        if (ReduxUtil.isNull(request.getEmail())) {
            return new GQLResponse("Author email required.", ReduxUtil.ERROR);
        }
        Optional<Author> author = this.authorRepository.findByEmailAndStatusNot(request.getEmail(), Status.DELETE);
        if (!author.isPresent()) {
            return new GQLResponse("Author email not found.", ReduxUtil.ERROR);
        }
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        int dotIndex = originalFilename.lastIndexOf('.');
        if (dotIndex >= 0) {
            fileExtension = originalFilename.substring(dotIndex);
        }
        Map<String, Object> uploadResponse = this.remoteFileExchange.uploadToBucket(ReduxUtil.BUCKET,
            UUID.randomUUID().toString().concat(fileExtension), file.getInputStream(), true);
        author.get().setImage((String) uploadResponse.get(this.remoteFileExchange.PUBLIC_FILE));
        this.authorRepository.save(author.get());
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setEmail(author.get().getEmail());
        authorResponse.setImage(author.get().getImage());
        return new GQLResponse("Authors fetch successfully.", ReduxUtil.SUCCESS, authorResponse);
    }

    /**
     * Method use to get the author
     * @param author
     * @return AuthorResponse
     * */
    private AuthorResponse getAuthorResponse(Author author) {
        AuthorResponse authorResponse = this.authorConverter.convertToAuthor(author);
        if (!ReduxUtil.isNull(author.getBooks())) {
            authorResponse.setBooks(author.getBooks().stream()
                .map(book -> this.bookConverter.convertToBook(book))
                .collect(Collectors.toList()));
        }
        return authorResponse;
    }
}
