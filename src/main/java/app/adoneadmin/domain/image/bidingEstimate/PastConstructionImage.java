package app.adoneadmin.domain.image.bidingEstimate;

import app.adoneadmin.domain.estimate.BidingEstimate;
import app.adoneadmin.domain.image.Image;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(exclude = {"bidingEstimate"})
@DiscriminatorValue("pastConstruction")
@Entity
public class PastConstructionImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biding_estimate_id", nullable = false)
    private BidingEstimate bidingEstimate;

    protected PastConstructionImage(){}

    public PastConstructionImage(BidingEstimate bidingEstimate, String imagerUrl){
        super(imagerUrl);
        this.bidingEstimate = bidingEstimate;
        bidingEstimate.getPastConstructionImageList().add(this);
    }
}
