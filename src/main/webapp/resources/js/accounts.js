var app = require('express')();
var http = require('http').createServer(app);
var path = require('path');
var kafka = require('kafka-node');
var io = require('socket.io').listen(http);

app.get('/streamAccounts', function(req, res){
    res.sendFile(path.resolve(__dirname + '/../../WEB-INF/views/accounts.html'));
});

app.get('/consumeFrontend', function(req, res){
    var consumer = new kafka.Consumer(
        new kafka.KafkaClient(),
        [
            { topic: 'Accounts4',  groupId: 'demo' }
        ],
        {
            autoCommit: false
        }
    );

    consumer.on('message', function (message) {
        io.emit('account', message.key);

        console.log(message.key + '0002');
    });
});

http.listen(3000, function(){
    console.log('listening on *:3000');
});