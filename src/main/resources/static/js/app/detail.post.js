var main = {
    init: function () {
        var _this = this;
        $('#btn-modify-post').on('click', function (event) {
            _this.modifyPost();
            event.preventDefault();      // 이벤트 전파 방지
        });

        $('#btn-delete-post').on('click', function (event) {
            _this.deletePost();
            event.preventDefault();     // 이벤트 전파 방지
        })

    },

    modifyPost: function () {
        var data = {
            title: $('#post-modify-title').val(),
            content: $('#post-modify-content').val()
        };

        var confirm_modify_post = confirm('게시글을 수정하시겠습니까?');
        if(confirm_modify_post == true){
            $.ajax({
                type: 'PUT',
                url: '/posts/' + $('#post-number').val(),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function(response){
                if(response['result'] == 'success'){
                    alert('게시물이 수정되었습니다.');
                    location.reload();
                }
            }).fail(function () {
                alert('게시글 수정에 실패하였습니다.');
            });
        }else{
            return;
        }

    },

    deletePost: function () {
        var confirm_delete_post = confirm('게시글을 삭제하시겠습니까?');
        if(confirm_delete_post == true){
            $.ajax({
                type: 'DELETE',
                url: '/posts/' + $('#post-number').val(),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
            }).done(function(response){
                if(response['result'] == 'success'){
                    alert('게시물이 삭제되었습니다.');
                    location.href = '/posts';
                }
            }).fail(function () {
                alert('게시글 삭제에 실패하였습니다.');
            });
        }else{
            return;
        }

    }
};

main.init();