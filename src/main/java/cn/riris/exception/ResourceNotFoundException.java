package cn.riris.exception;

/**
 * 资源不存在
 *
 * @author : riris
 */
public class ResourceNotFoundException extends BaseException
{

    private static final long serialVersionUID = -9025744067892744590L;

    public ResourceNotFoundException()
    {
        super();
    }

    public ResourceNotFoundException(String message)
    {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause)
    {
        super(cause);
    }

    protected ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
