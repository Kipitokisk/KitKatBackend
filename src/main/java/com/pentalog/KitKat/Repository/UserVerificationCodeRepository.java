package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.UserVerificationCode;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserVerificationCodeRepository extends KeyValueRepository<UserVerificationCode, String> {
}
