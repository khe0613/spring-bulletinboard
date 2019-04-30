var main = {
    init: function () {
        var _this = this;
        $('#btn-post-register').on('click', function (event) {
            _this.postRegister();
            event.preventDefault();     // 이벤트 전파 방지
        });
    },

    postRegister: function () {
        var data = {
            title: $('#post-register-title').val(),
            content: $('#post-register-content').val()
        };

        var confirm_post_register = confirm('게시물을 등록하시겠습니까?');
        if(confirm_post_register == true){
            $.ajax({
                type: 'POST',
                url: '/posts',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (response) {
                if(response['result'] == 'success'){
                    alert('게시물이 등록되었습니다.');
                    location.reload();
                }
            }).fail(function () {
                alert('게시물 등록에 실패하였습니다.');
            });

        }else{
            return;
        }



    }
};

main.init();