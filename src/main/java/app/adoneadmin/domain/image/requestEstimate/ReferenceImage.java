package app.adoneadmin.domain.image.requestEstimate;

import app.adoneadmin.domain.estimate.requestEstimate.RequestEstimate;
import app.adoneadmin.domain.image.Image;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(exclude = {"requestEstimate"})
@DiscriminatorValue("reference")
@Entity
public class ReferenceImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_estimate_id", nullable = false)
    private RequestEstimate requestEstimate;

    protected ReferenceImage(){}

    public ReferenceImage(RequestEstimate requestEstimate, String imagerUrl){
        super(imagerUrl);
        this.requestEstimate = requestEstimate;
        requestEstimate.getReferenceImageList().add(this);
    }
}
