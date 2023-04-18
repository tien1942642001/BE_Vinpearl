package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "history_search")
@Getter
@Setter
public class HistorySearch extends BaseEntity {

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "search_keyword")
    private String searchKeyword;
}
