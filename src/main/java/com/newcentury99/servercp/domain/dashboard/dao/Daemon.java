package com.newcentury99.servercp.domain.dashboard.dao;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Daemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daemon_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;

    @Lob
    @Column(nullable = false)
    private String dockerfile;

    @Lob
    @Column(nullable = false)
    private String composefile;

    @Column
    private String description;
}
