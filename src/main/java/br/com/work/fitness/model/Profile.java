package br.com.work.fitness.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Data
@Document
public class Profile implements GrantedAuthority {

    @Id
    private String id;
    private String authority;
}
