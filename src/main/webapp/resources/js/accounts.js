var app = require('express')();
var http = require('http').createServer(app);
var path = require('path');
var kafka = require('kafka-node');
var io = require('socket.io').listen(http);

app.get('/streamAccounts', function(req, res){
    res.sendFile(path.resolve(__dirname + '/../../WEB-INF/views/accounts.html'));
});

app.get('/consumeFrontend', function(req, res) {
    var kafkaClient = new kafka.KafkaClient();
    var consumer = new kafka.Consumer(
        kafkaClient,
        [
            { topic: 'Accounts9',  groupId: 'demoFrontend' }
        ],
        {
            autoCommit: false
        }
    );

    consumer.on('message', function (message) {
        message.offset

        console.log(message.key);

        io.emit('account', message.key);
    });
});

http.listen(3000, function(){
    console.log('listening on *:3000');
});