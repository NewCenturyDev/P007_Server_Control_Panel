function register() {
    const userInfo = {
        host: "220.85.251.6",
        username: $('#username').val(),
        password: $('#password').val()
    };

    jQuery.ajax({
        url: location.origin + '/register',
        type: "post",
        accept: "application/json",
        contentType: "application/json; charset=utf-8",
        cache: false,
        data: JSON.stringify(userInfo),
        dataType: "json",
        success: (res) => {
            console.log(res);
        },
        error: (err) => {
            alert(err.responseJSON._metadata.message);
        }
    });
}
