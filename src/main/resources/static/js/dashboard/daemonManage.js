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
