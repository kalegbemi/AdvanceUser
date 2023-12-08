package com.example.AdvanceUser.model;

import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
@Data
@Resource
public class AdvancedUserResource extends RepresentationModel<AdvancedUserResource> {

    private AdvancedUser advancedUser;
}
