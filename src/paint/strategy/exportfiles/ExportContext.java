package paint.strategy.exportfiles;



public class ExportContext {
    private ExportStrategy strategy;

    public void setExportStrategy(ExportStrategy exportStrategy){
        this.strategy = exportStrategy;
    }

    public void exportFile(){
        strategy.exportFile();
    }
}
