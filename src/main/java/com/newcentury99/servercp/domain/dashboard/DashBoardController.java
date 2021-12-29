package com.newcentury99.servercp.domain.dashboard;

import com.newcentury99.servercp.domain.dashboard.daemon.DaemonService;
import com.newcentury99.servercp.domain.dashboard.daemon.dao.Daemon;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class DashBoardController {
    private static final Logger logger = LogManager.getLogger();
    private final DaemonService daemonService;


    @GetMapping("/dashboard")
    public String mainView(Model model) {
        try {
            List<Daemon> daemonList = daemonService.fetchDaemonList();
            model.addAttribute("daemonListIsEmpty", daemonList.isEmpty());
            model.addAttribute("daemonList", daemonList);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return "dashboard/daemonList.html";
    }

    @GetMapping("/dashboard/daemon_manage")
    public String daemonManageView(Model model, @RequestParam(name = "id") Long daemonId) {
        try {
            Daemon daemon = daemonService.fetchDaemonById(daemonId);
            String daemonLog = daemonService.fetchDaemonLogById(daemonId);
            model.addAttribute("daemon", daemon);
            model.addAttribute("daemonLog", daemonLog);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return "dashboard/daemonManage.html";
    }

    @GetMapping("/dashboard/daemon_editor")
    public String daemonEditorView(Model model, @RequestParam(name = "id", required = false) Long daemonId) {
        try {
            if (daemonId != null) {
                Daemon daemon = daemonService.fetchDaemonById(daemonId);
                model.addAttribute("daemon", daemon);
                model.addAttribute("isNewDaemon", false);
            } else {
                model.addAttribute("daemon", new Daemon());
                model.addAttribute("isNewDaemon", true);
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return "dashboard/daemonEditor.html";
    }
}
