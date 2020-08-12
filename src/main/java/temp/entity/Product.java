package temp.entity;

import com.axe.trace.sys.entity.BaseEntity;

// 商品捐赠
public class Product extends BaseEntity {

    // 商品名称
    private String productName;

    // 购买重量
    private String productWeight;

    // 购买价格
    private float productPrice;

    // 捐赠对象
    private ProductDonateTo productDonateTo;

    // 捐赠金额
    private float productDonatePrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public ProductDonateTo getProductDonateTo() {
        return productDonateTo;
    }

    public void setProductDonateTo(ProductDonateTo productDonateTo) {
        this.productDonateTo = productDonateTo;
    }

    public float getProductDonatePrice() {
        return productDonatePrice;
    }

    public void setProductDonatePrice(float productDonatePrice) {
        this.productDonatePrice = productDonatePrice;
    }

}
