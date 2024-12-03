package com.ratolla.twitter_simplified.enitities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "role_id")
    private Long id;

    private String name;

    public  enum  Values{
        BASIC(2L),

        ADMIN(1L);

        long roleId;

        Values(long roleId){
            this.roleId = roleId;
        }

    }

}
