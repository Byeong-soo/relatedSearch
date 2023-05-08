package com.jobseeker.relatedSearch.repository;

import com.jobseeker.relatedSearch.domain.InvertedIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InvertedIndexRepository {

    private final EntityManager em;

    public void save(InvertedIndex invertedIndex) {
        if (invertedIndex.getId() == null) {
            em.persist(invertedIndex);
        } else {
            em.merge(invertedIndex);
        }
    }


    public Optional<InvertedIndex> findWord(String word) {
        List<InvertedIndex> name = em.createQuery("select II from InvertedIndex II where II.word = :name", InvertedIndex.class)
                .setParameter("name", word)
                .getResultList();
        return name.stream().findAny();
    }

}
