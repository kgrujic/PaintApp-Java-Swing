package paint.command;

public interface ICommand {
    public void execute();
    public void unexecute();
    public ICommand clone();
}
