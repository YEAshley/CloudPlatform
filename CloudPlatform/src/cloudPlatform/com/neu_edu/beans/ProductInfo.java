package cloudPlatform.com.neu_edu.beans;

/**
 * 产品信息类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class ProductInfo {
    private String productCode;
    private String productName;
    private String productType;
    private String productSize;
    private String productDescription;

    public ProductInfo(String productCode, String productName, String productType, String productSize, String productDescription) {
        this.productCode = productCode;
        this.productName = productName;
        this.productType = productType;
        this.productSize = productSize;
        this.productDescription = productDescription;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

}
