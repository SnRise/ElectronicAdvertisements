package ru.itmo.electronic.advertisements.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * @author Madiyar Nurgazin
 */
@Data
@EqualsAndHashCode
@Entity
@RequiredArgsConstructor
@Table(name = "regions")
public class Region {
    @Id
    @Column(name = "region_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
}
