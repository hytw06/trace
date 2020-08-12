package temp.entity;

import com.axe.trace.sys.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

// 种子
public class Agriculture extends BaseEntity {

    @ApiModelProperty(value = "种子信息", name = "agricultureSeed")
    private String agricultureSeed;

    @ApiModelProperty(value = "种子来源", name = "agricultureOrigin")
    private String agricultureOrigin;

    @ApiModelProperty(value = "种子介绍", name = "agricultureImages")
    private String agricultureImages;

    @ApiModelProperty(value = "养殖方式", name = "agricultureBreedMethods")
    private String agricultureBreedMethods;

    public String getAgricultureSeed() {
        return agricultureSeed;
    }

    public void setAgricultureSeed(String agricultureSeed) {
        this.agricultureSeed = agricultureSeed;
    }

    public String getAgricultureOrigin() {
        return agricultureOrigin;
    }

    public void setAgricultureOrigin(String agricultureOrigin) {
        this.agricultureOrigin = agricultureOrigin;
    }

    public String getAgricultureImages() {
        return agricultureImages;
    }

    public void setAgricultureImages(String agricultureImages) {
        this.agricultureImages = agricultureImages;
    }

    public String getAgricultureBreedMethods() {
        return agricultureBreedMethods;
    }

    public void setAgricultureBreedMethods(String agricultureBreedMethods) {
        this.agricultureBreedMethods = agricultureBreedMethods;
    }

}
