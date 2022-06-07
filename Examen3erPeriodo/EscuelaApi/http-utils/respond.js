function respond(res, status, data){
    res.writeHead(status, {'Content-type':'application/json'});
    
    if (data){
        res.end(JSON.stringify(data));
    }else{
        res.end();
    }
}

module.exports = respond;