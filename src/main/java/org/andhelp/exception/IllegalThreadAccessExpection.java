package org.andhelp.exception;

/**
 * Thrown when an thread tries to access an resource which should not be accessed by an type of thread
 */
public class IllegalThreadAccessExpection extends RuntimeException
{
    public IllegalThreadAccessExpection()
    {
        super();
    }

    public IllegalThreadAccessExpection(final String detailMessage)
    {
        super(detailMessage);
    }

    public IllegalThreadAccessExpection(final String detailMessage, final Throwable throwable)
    {
        super(detailMessage, throwable);
    }

    public IllegalThreadAccessExpection(final Throwable throwable)
    {
        super(throwable);
    }
}
