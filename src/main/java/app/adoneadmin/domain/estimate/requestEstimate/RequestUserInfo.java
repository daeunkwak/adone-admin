package app.adoneadmin.domain.estimate.requestEstimate;

import app.adoneadmin.domain.businessCategory.BusinessCategory;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Embeddable //jpa 내장타입
@Getter
public class RequestUserInfo {

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_category_id")
    private BusinessCategory businessCategory;

    @Column(nullable = false)
    private String storeLocation;

    protected RequestUserInfo(){};

    public RequestUserInfo(String companyName, String username, String phone, Long businessCategoryId, String storeLocation){
        this.companyName = companyName;
        this.username = username;
        this.phone = phone;
        this.businessCategory = BusinessCategory.builder().businessCategoryId(businessCategoryId).build();
        this.storeLocation = storeLocation;
    }

}