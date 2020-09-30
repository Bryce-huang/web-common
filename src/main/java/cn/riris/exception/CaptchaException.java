package cn.riris.exception;

/**
 * @author : bryce
 */
public class CaptchaException extends BaseException
{
    private static final long serialVersionUID = 6945055519897087744L;

    public CaptchaException()
    {
        super();
    }

    public CaptchaException(String message)
    {
        super(message);
    }

    public CaptchaException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public CaptchaException(Throwable cause)
    {
        super(cause);
    }

    protected CaptchaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
