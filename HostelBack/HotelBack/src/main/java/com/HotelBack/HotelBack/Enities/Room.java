package com.HotelBack.HotelBack.Enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rooms_id_gen")
    @SequenceGenerator(name = "rooms_id_gen", sequenceName = "rooms_id_seq", allocationSize = 1)
    @Column(name = "room_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Size(max = 10)
    @NotNull
    @Column(name = "room_number", nullable = false, length = 10)
    private String roomNumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "room_type", nullable = false, length = 50)
    private String roomType;

    @NotNull
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "imager")
    private String imager;

}