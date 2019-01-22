var app = require('express')();
var http = require('http').createServer(app);
var path = require('path');
var kafka = require('kafka-node');
var io = require('socket.io').listen(http);

var cache = {};

app.get('/streamAccounts', function(req, res){
    res.sendFile(path.resolve(__dirname + '/../../WEB-INF/views/accounts.html'));
});

app.get('/consumeFrontend', function(req, res) {
    var kafkaClient = new kafka.KafkaClient();

    var consumer = new kafka.Consumer(
        kafkaClient,
        [
            { topic: 'Account3',  groupId: 'demoFrontend' }
        ],
        {
            autoCommit: false
        }
    );

    consumer.on('message', function (message) {
        if (cache.hasOwnProperty(message.offset)) {
            console.log(`Duplicate message offset "${message.offset}"`, ++cache[message.offset]);
            return;
        }

        cache[message.offset] = 0;

        console.log(message.key);

        io.emit('account', message.key);
    });
});

http.listen(3000, function(){
    console.log('listening on *:3000');
});