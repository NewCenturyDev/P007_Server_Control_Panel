function createDaemon() {
    const DaemonDetail = {
        name: $("#daemon-name").val(),
        version: $("#daemon-version").val(),
        technology: $("#daemon-technology").val(),
        imageName: $("#daemon-image-name").val(),
        containerName: $("#daemon-container-name").val(),
        port: $("#daemon-port").val(),
        projectPath: $("#daemon-projectPath").val(),
        binaryPath: $("#daemon-binaryPath").val(),
        dockerfile: $("#daemon-dockerfile").val(),
        composefile: $("#daemon-composefile").val(),
        description: $("#daemon-description").val(),
        portfolioUrl: $("#daemon-portfolioUrl").val()
    }
    jQuery.ajax({
        url: location.origin + '/dashboard/daemon',
        type: "post",
        accept: "application/json",
        contentType: "application/json; charset=utf-8",
        cache: false,
        data: JSON.stringify(DaemonDetail),
        dataType: "json",
        success: (res) => {
            console.log(res);
            alert("데몬 등록 성공");
            location.href = location.origin + '/dashboard';
        },
        error: (xhr, error) => {
            console.log(xhr);
            alert(error);
        }
    });
}

function updateDaemon(daemonId) {
    const DaemonDetail = {
        id: daemonId,
        name: $("#daemon-name").val(),
        version: $("#daemon-version").val(),
        technology: $("#daemon-technology").val(),
        imageName: $("#daemon-image-name").val(),
        containerName: $("#daemon-container-name").val(),
        port: $("#daemon-port").val(),
        projectPath: $("#daemon-projectPath").val(),
        binaryPath: $("#daemon-binaryPath").val(),
        dockerfile: $("#daemon-dockerfile").val(),
        composefile: $("#daemon-composefile").val(),
        description: $("#daemon-description").val(),
        portfolioUrl: $("#daemon-portfolioUrl").val()
    }
    jQuery.ajax({
        url: location.origin + '/dashboard/daemon',
        type: "put",
        accept: "application/json",
        contentType: "application/json; charset=utf-8",
        cache: false,
        data: JSON.stringify(DaemonDetail),
        dataType: "json",
        success: (res) => {
            console.log(res);
            alert("데몬 수정 성공");
            location.href = location.origin + '/dashboard';
        },
        error: (xhr, error) => {
            console.log(xhr);
            alert(error);
        }
    });
}
