package cn.riris.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author riris
 * 2019/7/1 15:58
 */
@NoArgsConstructor
@Getter
public class BaseException extends RuntimeException
{

    private static final long serialVersionUID = -5119907353169629539L;

    protected String msg;
    protected String code;

    public BaseException(String code, String msgFormat, Object... args)
    {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);

    }

    public BaseException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BaseException(Throwable cause)
    {
        super(cause);
    }

    public BaseException(String message)
    {
        super(message);
        this.msg = message;
    }

    public BaseException newInstance(String msgFormat, Object... args)
    {
        return new BaseException(this.code, msgFormat, args);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
