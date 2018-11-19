var express = require('express');
var bodyParser = require('body-parser');
var app = express();

var fs = require('fs');

app.use(bodyParser.json())



app.get('/getServices', function(req, res){
  fs.readFile('registry.json','utf8', function(err, data){
    //console.log('Hello');
    data = JSON.parse(data);
    res.end(JSON.stringify(data));
  });
})

app.post('/registerServer', function(req, res){
  fs.readFile('registry.json','utf8', function(err, data){
    // var server = Object.keys(req.body)[0];
    var obj = JSON.parse(data);
    console.log(obj);
    console.log(req.body);
    var key = Object.keys(req.body)[0];
    console.log(typeof(key));
    obj["Servers"][key] = req.body[key];
    console.log(obj);
    fs.writeFile('registry.json',JSON.stringify(obj),function(err, data){
      res.send({STATUS:"SUCCESS"});
    })
  })
  //   console.log(JSON.stringify(req.body));
})

var server = app.listen(3000, function(){
  var host = '127.0.0.1'
  console.log(host);
  var port = server.address().port
  console.log("Registry server listening at http://%s:%s", host, port);
})
