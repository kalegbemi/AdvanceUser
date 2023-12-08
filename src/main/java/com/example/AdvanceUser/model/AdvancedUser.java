package com.example.AdvanceUser.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Entity(name = "advanced_user")
public class AdvancedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "full_name")
    @Length(min = 6,message = "Enter your first and last name")
    private String fullName;

    @Email(message = "Enter a valid email")
    private String email;

    @Length(min = 4,message = "Enter a valid gender")
    private String gender;

    @Column(name = "phone_number")
    @Pattern(regexp = "[0-9]{11}")
    private String phoneNumber;

    public AdvancedUser(String fullName, String email, String gender,   String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }


}
