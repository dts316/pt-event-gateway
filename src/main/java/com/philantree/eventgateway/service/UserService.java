package com.philantree.eventgateway.service;

import com.philantree.eventgateway.exception.UserException;
import com.philantree.eventgateway.model.User;
import com.philantree.eventgateway.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;

    public Optional<User> getUser(Long id) throws Exception{
//        String status = saveTestData();
//        log.info("status: {}", status);
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(it -> {
            log.info("found user: {}", it.print());
        });

        if(optionalUser.isPresent()){
            return optionalUser;
        }else{
            throw new UserException(HttpStatus.NOT_FOUND,"User not found");
        }

//        return Optional
//                .ofNullable(userRepository.findById(id))
//                .orElseThrow(() -> new Exception("User not found with ID - " + id));
//        return optionalUser.orElse(null);
//        return optionalUser.orElse(new User("000", "Unknown User"));
    }

    public Optional<User> checkCredentials(String accessKey, String secretKey,Long id, Long timestamp)
        throws Exception{
        //update this
        if(accessKey != null && secretKey != null){
            return Optional
                    .ofNullable(userRepository.findById(id))
                    .orElseThrow();
        }else{
            throw new Exception("No Access or Secret key in header");
        }
    }
}
