var main = {
    init: function(){
        var _this = this;
        $('#btn-signup').on('click', function (event) {
            _this.signup();
            event.preventDefault();         // 이벤트 전파 방지 (이 코드는 필수)
        });
    },
    signup: function () {
        var data = {
          id: $('#inputId').val(),
          password: $('#inputPassword').val(),
          name: $('#inputName').val(),
          tel: $('#inputTel').val()
        };

        $.ajax({
            type: 'POST',
            url: '/member',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (response) {
            // response는 javascript object 타입임
           alert('회원가입을 환영합니다.');
           location.href= '/';
        }).fail(function () {
            alert('회원가입에 실패하셨습니다.');
            location.reload();
        });
    }
};


main.init();