function deleteDaemon(daemonId) {
    if(window.confirm("정말로 이 프로젝트 데몬을 등록 해제하시겠습니까?")) {
        requestDeleteDaemon(daemonId);
    }
}

function requestDeleteDaemon(daemonId) {
    jQuery.ajax({
        url: location.origin + '/dashboard/daemon',
        type: "delete",
        accept: "application/json",
        contentType: "application/json; charset=utf-8",
        cache: false,
        data: JSON.stringify({
            id: daemonId
        }),
        dataType: "json",
        success: (res) => {
            console.log(res);
            alert("데몬 등록 해제 성공");
            location.href = location.origin + '/dashboard';
        },
        error: (xhr, error) => {
            console.log(xhr);
            alert(error);
        }
    });
}

function getDaemonStatus(daemonId) {
    jQuery.ajax({
        url: location.origin + '/dashboard/daemon/status?id=' + daemonId,
        type: "get",
        accept: "application/json",
        contentType: "application/json; charset=utf-8",
        cache: false,
        success: (res) => {
            $("#daemon-status").text(res.daemonStatus);
        },
        error: (xhr, error) => {
            console.log(xhr);
            $("#daemon-status").text("Unknown");
            alert(error);
        }
    });
}

function getDaemonLogs(daemonId) {
    jQuery.ajax({
        url: location.origin + '/dashboard/daemon/log?id=' + daemonId,
        type: "get",
        accept: "application/json",
        contentType: "application/json; charset=utf-8",
        cache: false,
        success: (res) => {
            $("#daemon-docker-log").val(res.daemonLog);
        },
        error: (xhr, error) => {
            console.log(xhr);
            alert(error);
        }
    });
}

function getDaemonLogFile(daemonId) {
    window.open(location.origin + '/dashboard/daemon/logfile?id=' + daemonId);
}

function changeDaemonState(daemonId, state) {
    const message = {
        start: "시작",
        stop: "중지",
        restart: "재시작"
    }
    if (state !== "start" || state !== "stop" || state !== "restart") {
        console.error("Invalid daemon state");
    }
    alert("데몬을 " + message[state] + "합니다");
    jQuery.ajax({
        url: location.origin + '/dashboard/daemon/' + state,
        type: "post",
        accept: "application/json",
        contentType: "application/json; charset=utf-8",
        cache: false,
        data: JSON.stringify({
            id: daemonId
        }),
        dataType: "json",
        success: (res) => {
            console.log(res);
            alert("데몬을 " + message[state] + "했습니다");
            location.reload();
        },
        error: (xhr, error) => {
            console.log(xhr);
            $("#daemon-status").text("Unknown");
            alert(error);
        }
    });
}
