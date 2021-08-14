package flyerwge.cms.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 首页banner表
 * </p>
 *
 * @author gw
 * @since 2021-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="CrmBanner对象", description="首页banner表")
public class CrmBanner implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "标题")
    private String title;

//    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

//    @ApiModelProperty(value = "链接地址")
    private String linkUrl;

//    @ApiModelProperty(value = "排序")
    private Integer sort;

//    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

//    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

//    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;


}
