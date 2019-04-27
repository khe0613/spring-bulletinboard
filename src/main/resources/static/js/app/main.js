var main = {
    init: function(){
        var _this = this;
        $("#btn-signup").on('click', function(){    // 회원가입 버튼 클릭
           _this.signup();
            event.preventDefault();         // 이벤트 전파 방지
        });

        $('#btn-login').on('click', function(){     // 로그인 버튼 클릭
            _this.login();
            event.preventDefault();         // 이벤트 전파 방지
        });
    },

    signup: function(){
        location.href = "/signup";
    },

    login: function(){
        var data = {
            id: $('#inputId').val(),
            password: $('#inputPassword').val()
        };
/*
        $.ajax({
            type: 'GET',
            url: '/member/' + data.id,
            dataType: 'json',                                    // 서버로부터 반환되는 데이터 형식
            contentType: 'application/json; charset=utf-8',     // 서버에 데이터를 보낼 때 사용(헤더)
            data: JSON.stringify(data)
        }).done(function (response) {
            // response는 javascript object 타입임
            alert('로그인 환영합니다.');
            location.href= '/';
        }).fail(function () {
            alert('아이디/비밀번호가 일치하지 않습니다.');
            location.reload();
        });
        */

    }
};

main.init();