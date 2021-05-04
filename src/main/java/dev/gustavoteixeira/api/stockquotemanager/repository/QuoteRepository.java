package dev.gustavoteixeira.api.stockquotemanager.repository;

import dev.gustavoteixeira.api.stockquotemanager.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {

    List<QuoteEntity> findByStockId(String id);
    
}
