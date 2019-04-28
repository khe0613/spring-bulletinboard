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
            url: '/members/' + data.id,
            dataType: 'json',           // 서버로부터 반환되는 데이터 형식
            contentType: 'application/json; charset=utf-8',     // 서버에 데이터를 보낼 때 사용(헤더)
            data: JSON.stringify(data)
        }).done(function (response) {
            // response는 javascript object 타입임
            if(response["result"] == "success"){
                alert('회원가입을 환영합니다.');
                location.href= '/';
            }else{
                alert('중복된 아이디가 존재합니다.');
            }

        }).fail(function () {
            alert('회원가입에 실패하셨습니다.');
            location.reload();
        });
    }
};


main.init();