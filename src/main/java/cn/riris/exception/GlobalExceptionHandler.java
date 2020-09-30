package cn.riris.exception;


import cn.riris.web.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.sql.SQLException;
import java.util.Objects;

/**
 * @author riris
 * 2019/7/1 13:58
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ServletRequestBindingException.class)
    public R servletRequestBindingExceptionHandler(ServletRequestBindingException e) {
        log.error("error", e);
        return R.unauthorized(e.getMessage() + ",请检查参数名称是否符合格式.");
    }

    @ExceptionHandler(ValidatedIllegalArgumentException.class)
    public R validatedIllegalArgumentExceptionHandler(ValidatedIllegalArgumentException e) {
        log.error("error", e);
        return R.unauthorized(Objects.requireNonNull(e.getBindingResult()
                                                               .getFieldError())
                                              .getDefaultMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("error", e);
        return R.unauthorized(Objects.requireNonNull(e.getBindingResult()
                                                               .getFieldError())
                                              .getDefaultMessage());
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        log.error("error", e);
        return R.unauthorized("Required request body is missing,请求体无法解析,请检查请求体格式的有效性(或请求体内参数格式有误导致无法解析). ");
    }

    @ExceptionHandler(MultipartException.class)
    public R multipartExceptionHandler(MultipartException e) {
        log.error("error", e);
        return R.unauthorized(e.getMessage());
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public R serviceErrorHandler(AuthenticationCredentialsNotFoundException e) {
        log.error("error", e);
        return R.unauthorized(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public R serviceErrorHandler(UsernameNotFoundException e) {
        log.error("error", e);
        return R.unauthorized(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public R serviceErrorHandler(ResourceNotFoundException e) {
        log.error("error", e);
        return R.badRequest(e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public R serviceErrorHandler(AuthenticationException e) {
        log.error("error", e);
        return R.unauthorized(e.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public R forbiddenErrorHandler(ForbiddenException e) {
        log.error("error", e);
        return R.forbidden(e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R forbiddenErrorHandler(HttpRequestMethodNotSupportedException e) {
        log.error("error", e);
        return R.forbidden(e.getMessage());
    }

    @ExceptionHandler(CaptchaException.class)
    public R captchaErrorHandler(CaptchaException e) {
        log.error("error", e);
        return R.badRequest(e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public R serviceErrorHandler(ServiceException e) {
        log.error("error", e);
        return R.badRequest(e.getMessage());
    }

    @ExceptionHandler(DaoException.class)
    public R daoErrorHandler(DaoException e) {
        log.error("error", e);
        return R.badRequest(e.getMessage());
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public R sqlErrorHandler(Throwable e) {
        log.error("error", e);
        return R.internalServerError();
    }


    @ExceptionHandler(Throwable.class)
    public R globalErrorHandler(Throwable e) {
        log.error("internalServerError", e);
        return R.internalServerError("internalServerError : " + e.getMessage());
    }


}
