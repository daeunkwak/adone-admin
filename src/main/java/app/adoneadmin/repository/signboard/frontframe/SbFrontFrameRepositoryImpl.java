package app.adoneadmin.repository.signboard.frontframe;

import app.adoneadmin.domain.signboard.QSbFrontFrame;
import app.adoneadmin.vo.signboard.FrontFrameVo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.adoneadmin.domain.signboard.QSbFrontFrame.sbFrontFrame;

@Repository
@RequiredArgsConstructor
public class SbFrontFrameRepositoryImpl implements SbFrontFrameRepositoryQuerydsl {

    private final JPAQueryFactory jpaQueryFactory;

//    @Override
//    public List<FrontFrameVo> findFrontFrame(String materialType) {
//
//        return jpaQueryFactory.selectFrom(sbFrontFrame.id, sbFrontFrame.standard, sbFrontFrame.)
//                .where(sbFrontFrame."materialType.isNotNull())
//
//    }
}
