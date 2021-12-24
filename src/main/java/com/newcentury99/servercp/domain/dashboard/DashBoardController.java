package com.newcentury99.servercp.domain.dashboard;

import com.newcentury99.servercp.domain.dashboard.daemon.dto.*;
import com.newcentury99.servercp.domain.dashboard.node.dao.Node;
import com.newcentury99.servercp.domain.dashboard.node.dto.*;
import com.newcentury99.servercp.domain.dashboard.daemon.DaemonService;
import com.newcentury99.servercp.domain.dashboard.node.NodeService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class DashBoardController {
    private static final Logger logger = LogManager.getLogger();
    private final NodeService nodeService;


    @GetMapping("/dashboard")
    public String mainView(Model model) {
        List<Node> nodeList = nodeService.fetchNodeList();
        model.addAttribute("nodeList", nodeList);
        return "dashboard/index.html";
    }

    @GetMapping("/dashboard/daemon_detail")
    public String daemonDetailView(Model model) {
        return "dashboard/daemonDetail.html";
    }

    @GetMapping("/dashboard/daemon_editor")
    public String daemonEditorView(Model model) {
        return "dashboard/daemonEditor.html";
    }
}
