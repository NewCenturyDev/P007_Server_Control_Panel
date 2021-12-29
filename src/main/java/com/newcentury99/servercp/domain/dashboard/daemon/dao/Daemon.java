package com.newcentury99.servercp.domain.dashboard.daemon.dao;

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
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private String technology;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String containerName;

    @Column(nullable = false)
    private String port;

    @Column(nullable = false)
    private String projectPath;

    @Column(nullable = false)
    private String binaryPath;

    @Lob
    @Column(nullable = false)
    private String dockerfile;

    @Lob
    @Column(nullable = false)
    private String composefile;

    @Column
    private String description;
    @Column
    private String portfolioUrl;
}
