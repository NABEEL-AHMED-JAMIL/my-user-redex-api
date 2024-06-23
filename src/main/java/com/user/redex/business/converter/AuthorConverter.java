package com.user.redex.business.converter;

import com.user.redex.business.document.Author;
import com.user.redex.business.dto.request.AuthorRequest;
import com.user.redex.business.dto.response.AuthorResponse;
import com.user.redex.business.dto.response.GEnum;
import com.user.redex.util.ReduxUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author Nabeel Ahmed
 */
@Component
public class AuthorConverter {

    private Logger logger = LogManager.getLogger(AuthorConverter.class);

    /**
     * Method use to convert the author request to author
     * @param authorRequest
     * @param author
     * @return Author
     * */
    public Author convertToAuthor(AuthorRequest authorRequest, Author author) {
        logger.info("convertToAuthor => authorRequest to author.");
        author.setFirstName(authorRequest.getFirstName());
        author.setLastName(authorRequest.getLastName());
        author.setEmail(authorRequest.getEmail());
        author.setUsername(authorRequest.getUsername());
        author.setBiography(authorRequest.getBiography());
        author.setNationality(authorRequest.getNationality());
        author.setExpertise(authorRequest.getExpertise());
        author.setImage(authorRequest.getImage());
        return author;
    }

    /**
     * Method use to convert the author to author response
     * @param author
     * @return AuthorResponse
     * */
    public AuthorResponse convertToAuthor(Author author) {
        logger.info("convertToAuthor => author to authorResponse.");
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setFirstName(author.getFirstName());
        authorResponse.setLastName(author.getLastName());
        authorResponse.setEmail(author.getEmail());
        authorResponse.setUsername(author.getUsername());
        authorResponse.setBiography(author.getBiography());
        authorResponse.setNationality(author.getNationality());
        authorResponse.setExpertise(author.getExpertise());
        authorResponse.setImage(author.getImage());
        authorResponse.setCreatedAt(author.getCreatedAt());
        authorResponse.setUpdatedAt(author.getUpdatedAt());
        if (!ReduxUtil.isNull(author.getStatus())) {
            GEnum status = new GEnum(author.getStatus().getCode(),
                author.getStatus().getName(), author.getStatus());
            authorResponse.setStatus(status);
        }
        return authorResponse;
    }
}
