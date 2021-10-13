package com.fdmgroup.FxTrading.service.register;

import java.util.List;
import java.util.Optional;

import com.fdmgroup.FxTrading.error.UniqueEmailException;
import com.fdmgroup.FxTrading.model.SecurityQuestion;
import com.fdmgroup.FxTrading.model.User;

public interface AbstractRegisterService {

	Optional<User>  registerNewUser(String first_name, String last_name, String username, String password, List<SecurityQuestion> questions) throws UniqueEmailException;

}
