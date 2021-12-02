function toast(message, type, time) {
    bootoast.toast({
        message: message,
        type: type,
        position: 'top',
        icon: null,
        timeout: time || 3,
        animationDuration: 300,
        dismissible: true
    });
}

//处理异步返回导致的问题
//利用Promise函数
//返回的依旧是歌promise，
// resolve返回成功之后的promise reject失败之后的promise  promise有then和catch来接受失败和成功的情况
function request(option) {
    const {url, data, method} = option
    return new Promise((resolve, reject) => {
        $.ajax({
            url,
            data,
            method,
            dataType: 'json',
            success(rs) {
                resolve(rs)
            },
            error(error){
                reject(error)
            }
        })
    })
}
