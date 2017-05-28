package pl.bajtas.whoshouldcall.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Bajtas on 25.05.2017.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Default user role not found")  // 404
public class DefaultUserRoleNotFound extends Exception {
}
