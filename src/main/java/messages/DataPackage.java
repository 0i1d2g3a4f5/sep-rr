package messages;

/**
 * @author Sarp Cagin Erdogan
 * @author Mark Ringer
 */
public class DataPackage {
    private int packageType;
    private String packageBody;

    public DataPackage(int packageType, String packageBody){
        this.packageType = packageType;
        this.packageBody = packageBody;
    }

    public String getPackageBody() {
        return packageBody;
    }

    public int getPackageType() {
        return packageType;
    }
}
