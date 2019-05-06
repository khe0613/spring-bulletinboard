var main = {
    init: function () {
        var _this = this;
        $('#btn-post-register').on('click', function (event) {
            _this.postRegister();
            event.preventDefault();     // 이벤트 전파 방지
        });

        $('#btn-posts-search').on('click', function (event) {
            _this.postSearch();
            event.preventDefault();     // 이벤트 전파 방지
        })
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
                    location.href = '/posts';
                }
            }).fail(function () {
                alert('게시물 등록에 실패하였습니다.');
            });

        }else{
            return;
        }

    },

    postSearch: function () {
        var data = {
            type: $('#post-search-type').val(),
            keyword: $('#post-search-keyword').val()
        };

        if(data.keyword == ''){
            alert('검색어를 입력해주세요');
            return;
        }

        if(data.type == 'title'){                           // 제목으로 검색
            var destUrl = '/posts/title/' + data.keyword;
        }else{                                              // 작성자로 검색
            var destUrl = '/posts/writer/' + data.keyword;
        }

       location.href = destUrl;



    }
};

main.init();