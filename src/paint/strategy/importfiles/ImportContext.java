package paint.strategy.importfiles;

public class ImportContext {
    private ImportStrategy strategy;

    public void setImportStrategy(ImportStrategy importStrategy){
        this.strategy = importStrategy;
    }

    public void importFile(){
        strategy.importFile();
    }
}
