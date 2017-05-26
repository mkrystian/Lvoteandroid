package pl.edu.agh.student.mprezes.lvoteandroid.services.dto;

import org.threeten.bp.LocalDateTime;

import java.util.List;

/**
 * Created by majew on 25.05.2017.
 */

public class AccountDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;
    boolean activated;
    private String langKey;
    private String createdBy;
    LocalDateTime createdDate;
    private String lastModifiedBy;
    LocalDateTime lastModifiedDate;
    List<String> authorities;
}
