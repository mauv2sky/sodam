package com.samsung.sodam.db.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.response.ClientListResponse;
import com.samsung.sodam.api.response.CounselorListResponse;
import com.samsung.sodam.api.response.QClientListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.samsung.sodam.db.entity.QClient.client;
import static com.samsung.sodam.db.entity.QConsultSession.consultSession;
import static com.samsung.sodam.db.entity.QReview.review;

@Repository
@RequiredArgsConstructor
public class ClientResponseCustomRepositoryImpl implements ClientResponseCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ClientListResponse> getAllClientList(String userId, Pageable pageable) {
        List<ClientListResponse> list = queryFactory
                .select(new QClientListResponse(
                        client.id,
                        client.name,
                        client.email,
                        client.tel
                ))
                .from(client).join(consultSession).on(client.id.eq(consultSession.clientId))
                .where(consultSession.counselorId.eq(userId))
                .distinct()
                .orderBy(client.id.desc())
                .fetch();


        return new PageImpl<>(list, pageable, list.size());
    }

//    @Override
//    public Page<ClientListResponse> getClientList(String userId, Pageable pageable) {
//        List<ClientListResponse> list = queryFactory
//                .select(new QClientListResponse(
//                        client.id,
//                        client.name,
//                        client.email,
//                        client.tel
//                ))
//                .from(client).join(consultSession).on(client.id.eq(consultSession.clientId))
//                .where(consultSession.counselorId.eq(userId))
//                .distinct()
//                .orderBy(client.id.desc())
//                .fetch();
//
//
//        return new PageImpl<>(list, pageable, list.size());
//    }
//    private ClientListResponse getClientListResponse(String clientId) {
//        if(clientId == null || client.id.equals("")) return null;
//        return client.id.eq(clientId);
//    }

    @Override
    public List<CounselorListResponse> getReviewByAvg() {
        return queryFactory.from(review).groupBy(review.counselorId).select(
                Projections.bean(
                        CounselorListResponse.class,
                        review.id,
                        review.counselorId,
                        review.stars.avg().as("avg")
                )
        ).fetch();
    }

}
