package com.example.security.dto.response;

import com.example.security.component.ObjectConverter;
import com.example.security.source.entity.ProductDetail;
import com.example.security.core.CoreBase;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Data
public class ProductResponse {

    private Long productId;
    private Object productCode;
    private Object productNameEn;
    private Object productNameKh;
    private Object productDescription;
    private Object createBy;
    private Object createDate;
    private Object updateBy;
    private Object updateDate;

    private Object productDetail = new ArrayList<>();

    @JsonCreator
    public ProductResponse(Object data) {
        var values = (LinkedHashMap) data;

        this.setProductId(Long.valueOf(String.valueOf(values.get("productId"))));
        this.setProductCode(ObjectConverter.getData(values, "productCode", String.class));
        this.setProductNameEn(ObjectConverter.getData(values, "productNameEn", String.class));
        this.setProductNameKh(ObjectConverter.getData(values, "productNameKh", String.class));
        this.setProductDescription(ObjectConverter.getData(values, "productDescription", String.class));
        this.setCreateBy(ObjectConverter.getData(values, "createBy", String.class));
        this.setCreateDate(ObjectConverter.getData(values, "createDate", String.class));
        this.setUpdateBy(ObjectConverter.getData(values, "updateBy", String.class));
        this.setUpdateDate(ObjectConverter.getData(values, "updateDate", String.class));

        List<ProductDetail> detail = CoreBase.productDetailRepo.findByProductId(this.getProductId());
        if (!detail.isEmpty()) {
            this.setProductDetail(ObjectConverter.convertArrayToArrayObject(detail, new TypeReference<List<ProductDetail>>() {}));
        }
    }

}
