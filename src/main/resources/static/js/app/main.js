var main = {
    init: function(){
        var _this = this;
        $("#btn-signup").on('click', function(event){    // 회원가입 버튼 클릭
           _this.signup();
            event.preventDefault();         // 이벤트 전파 방지
        });

        $('#btn-login').on('click', function(event){     // 로그인 버튼 클릭
            _this.login();
            event.preventDefault();         // 이벤트 전파 방지
        });
    },

    signup: function(){
        location.href = "/signup";
    },

    login: function(){
        if($('#inputId').val() == ''){          // 아이디 미 입력시 로그인 요청 안됨
            alert('아이디를 입력해주세요.');
            return;
        }

        if($('#inputPassword').val() == ''){    // 비밀번호 미 입력시 로그인 요청 안됨
            alert('비밀번호를 입력해주세요.');
            return;
        }

        var login_form = document.getElementsByClassName("form-signin")[0];
        login_form.submit();
    }

};

main.init();