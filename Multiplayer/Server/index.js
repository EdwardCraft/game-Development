var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var players = [];
var port = 8080



server.listen(port, function(){
	console.log("Server is now running...");

});

io.total = 0;
io.on('connection', function(socket){

	console.log("Player Connected !");
	io.total += 1;
	socket.emit('socketID' , { id: socket.id });
	socket.emit('getPlayers', players);
	socket.broadcast.emit('newPlayer', {id: socket.id});

	socket.on('playerMove', function(data){
		data.id = socket.id;
		socket.broadcast.emit('playerMove', data);
		for(var i = 0; i < players.lenght; i++){
			if(players[i].id == data.id){
				players[i].x = data.x;
				players[i].y = data.y;
			}
		}

	});

	 socket.on('disconnect' ,function(){
	 	console.log("Player Disconnect");
	 	io.total -= 1;
	 	socket.broadcast.emit('playerDisconnected', {id: socket.id});
	 	for(var i = 0; i < players.lenght; i++ ){
	 		if(players[i].id == socket.id){
	 			players[i] = null;
	 			break
	 		}
	 	}
	 	for(var i = 0; i < players.lenght; i++ ){
	 		players[i] = players[i + 1];
	 	}
	 	players.length -= 1
	 	emptyArray()
	 });
  
	  players.push(new player(socket.id, 83, 528, 1 , 0))



});





function player(id, x, y, facing , velocityX){
	this.id = id;
	this.x = x;
	this.y = y;
	this.facing = facing;
	this.velocityX = velocityX;
}

function emptyArray(){
	if(io.total == 0){
	   	players.length = 0; // good!
 	}

}