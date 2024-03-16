package xyz.iffyspeak.window;

public abstract class Window {
    private long handle;
    public abstract void run();

    public long getHandle()
    {
        return handle;
    }
    public void setHandle(long hnd)
    {
        this.handle = hnd;
    }
}
