package cloudPlatform.com.neu_edu.beans.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProTypeTableData {
   private final StringProperty productType;

    public ProTypeTableData(String productType) {
        this.productType = new SimpleStringProperty(productType);
    }

    public String getProductType() {
        return productType.get();
    }

    public StringProperty productTypeProperty() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType.set(productType);
    }
}
