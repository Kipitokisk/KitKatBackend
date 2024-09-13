package com.pentalog.KitKat.DTO;

import com.pentalog.KitKat.Entities.*;
import com.pentalog.KitKat.Entities.User.User;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;

public class UserDTO {
    private Integer userId;
    private BitSet avatar;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String positionName;
    private String seniorityName;
    private String cityName;
    private String languages;
    private BitSet cv;
    private String roleName;
}
