package cn.riris.exception;

/**
 * 权限
 *
 * @author : bryce
 */
public class ForbiddenException extends BaseException
{

    private static final long serialVersionUID = 7830100378725409010L;

    public ForbiddenException()
    {
        super();
    }

    public ForbiddenException(String message)
    {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ForbiddenException(Throwable cause)
    {
        super(cause);
    }

    protected ForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
