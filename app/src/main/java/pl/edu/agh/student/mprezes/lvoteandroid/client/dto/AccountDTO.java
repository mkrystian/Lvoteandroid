package pl.edu.agh.student.mprezes.lvoteandroid.client.dto;

import org.threeten.bp.LocalDateTime;

import java.util.List;

/**
 * @author Krystian Majewski
 * @since 25.05.2017
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
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
    private List<String> authorities;
}
