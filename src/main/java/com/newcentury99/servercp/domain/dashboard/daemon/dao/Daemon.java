package com.newcentury99.servercp.domain.dashboard.daemon.dao;

import com.newcentury99.servercp.domain.dashboard.node.dao.Node;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "daemon_id")
    private Node node;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String technology;

    @Column(nullable = false)
    private String version;

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
}
