package cloudPlatform.com.neu_edu.beans.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProInfoTableData {
    private final StringProperty productCode;
    private final StringProperty productName;
    private final StringProperty productType;
    private final StringProperty productSize;
    private final StringProperty productDescription;


    public ProInfoTableData(String productCode, String productName, String productType, String productSize, String productDescription) {
        this.productCode = new SimpleStringProperty(productCode);
        this.productName = new SimpleStringProperty(productName);
        this.productType =  new SimpleStringProperty(productType);
        this.productSize =  new SimpleStringProperty(productSize);
        this.productDescription =  new SimpleStringProperty(productDescription);

    }

    public String getProductCode() {
        return productCode.get();
    }

    public StringProperty productCodeProperty() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode.set(productCode);
    }

    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
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

    public String getProductSize() {
        return productSize.get();
    }

    public StringProperty productSizeProperty() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize.set(productSize);
    }

    public String getProductDescription() {
        return productDescription.get();
    }

    public StringProperty productDescriptionProperty() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription.set(productDescription);
    }

}
