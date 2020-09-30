package cn.riris.exception;

/**
 * dao异常
 *
 * @author : bryce
 */
public class DaoException extends BaseException
{

    private static final long serialVersionUID = -7725259973997808045L;

    public DaoException()
    {
        super();
    }

    public DaoException(String message)
    {
        super(message);
    }

    public DaoException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DaoException(Throwable cause)
    {
        super(cause);
    }

    protected DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
