var main = {
    init: function () {
        var _this = this;
        $('#btn-modify-member-info').on('click', function (event) {
            _this.modifyMemberInfo();
            event.preventDefault();         // 이벤트 전파 방지
        });
        
        $('#btn-leave-member').on('click', function (event) {
            _this.leaveMember();
            event.preventDefault();         // 이벤트 전파 방지
        });
    },

    modifyMemberInfo: function () {        // 회원정보 수정
        var data = {
            password: $('#myPassword').val(),
            tel: $('#myTel').val()
        };

        $.ajax({
           type: 'PUT',
           url: '/members/' + $('#myId').val(),
           dataType: 'json',
           contentType: 'application/json; charset=utf-8',
           data: JSON.stringify(data)
        }).done(function (response) {
           // response는 javascript object 타입임
            if(response["result"] == "success"){
                alert('회원정보 수정이 정상적으로 처리되었습니다.');
                location.href= '/members/' + data.id;
            }
        }).fail(function () {
            alert('회원정보 수정에 실패하였습니다.')
        });
    },

    leaveMember: function () {          // 회원 탈퇴

    }
};

main.init();