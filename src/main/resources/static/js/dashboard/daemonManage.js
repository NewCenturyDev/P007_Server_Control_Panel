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

function requestStartDaemon(daemonId) {
    jQuery.ajax({
        url: location.origin + '/dashboard/daemon/start',
        type: "get",
        accept: "application/json",
        contentType: "application/json; charset=utf-8",
        cache: false,
        data: JSON.stringify({
            id: daemonId
        }),
        dataType: "json",
        success: (res) => {
            console.log(res);
            alert("데몬을 시작합니다");
            location.reload();
        },
        error: (xhr, error) => {
            console.log(xhr);
            $("#daemon-status").text("Unknown");
            alert(error);
        }
    });
}

function changeDaemonState(daemonId, state) {
    if (state !== "start" || state !== "stop" || state !== "restart") {
        console.error("Invalid daemon state");
    }
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
            alert("데몬을 시작합니다");
            location.reload();
        },
        error: (xhr, error) => {
            console.log(xhr);
            $("#daemon-status").text("Unknown");
            alert(error);
        }
    });
}
