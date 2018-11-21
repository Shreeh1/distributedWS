var express = require('express');
var bodyParser = require('body-parser');
var app = express();

var fs = require('fs');
const spawn = require('child_process').spawn;
app.use(bodyParser.json())



app.get('/getServices', function(req, res){
  fs.readFile('registry.json','utf8', function(err, data){
    //console.log('Hello');
    data = JSON.parse(data);
    res.end(JSON.stringify(data));
  });
})

app.get('/getServices/add',function(req, res){
  fs.readFile('registry.json', 'utf8', function(err, data){
    data = JSON.parse(data);
    var foundJSONlist = [];
    for(var i in data['Servers']){
      services = data['Servers'][i]['services'];
      if(services.indexOf('GetSumRequest') > -1){
        foundJSONlist.push(data['Servers'][i]);
      }
    }
    if(foundJSONlist) {
      res.setHeader('Content-Type', 'application/json');
      res.send(foundJSONlist); console.log(foundJSONlist);
    }
    else {res.status(500).send("Not Found");}
  });
})

app.get('/getServices/subtract',function(req, res){
  fs.readFile('registry.json', 'utf8', function(err, data){
    data = JSON.parse(data);
    var foundJSONlist = [];
    for(var i in data['Servers']){
      services = data['Servers'][i]['services'];
      if(services.indexOf('GetDifferenceRequest') > -1){
        foundJSONlist.push(data['Servers'][i]);
      }
    }
    if(foundJSONlist) {res.send(foundJSONlist); console.log(foundJSONlist);}
    else {res.status(500).send("Not Found");}
  });
})

app.get('/getServices/multiply',function(req, res){
  fs.readFile('registry.json', 'utf8', function(err, data){
    data = JSON.parse(data);
    var foundJSONlist = [];
    for(var i in data['Servers']){
      services = data['Servers'][i]['services'];
      if(services.indexOf('GetProductRequest') > -1){
        foundJSONlist.push(data['Servers'][i]);
      }
    }
    if(foundJSONlist) {res.send(foundJSONlist); console.log(foundJSONlist);}
    else {res.status(500).send("Not Found");}
  });
})

app.get('/getServices/square',function(req, res){
  fs.readFile('registry.json', 'utf8', function(err, data){
    data = JSON.parse(data);
    var foundJSONlist = [];
    for(var i in data['Servers']){
      services = data['Servers'][i]['services'];
      if(services.indexOf('GetSquareRequest') > -1){
        foundJSONlist.push(data['Servers'][i]);
      }
    }
    if(foundJSONlist) {res.send(foundJSONlist); console.log(foundJSONlist);}
    else {res.status(500).send("Not Found");}
  });
})

app.get('/getServices/cube',function(req, res){
  fs.readFile('registry.json', 'utf8', function(err, data){
    data = JSON.parse(data);
    var foundJSONlist = [];
    for(var i in data['Servers']){
      services = data['Servers'][i]['services'];
      if(services.indexOf('GetCubeRequest') > -1){
        foundJSONlist.push(data['Servers'][i]);
      }
    }
    if(foundJSONlist) {res.send(foundJSONlist); console.log(foundJSONlist);}
    else {res.status(500).send("Not Found");}
  });
})

app.get('/getServices/divide',function(req, res){
  fs.readFile('registry.json', 'utf8', function(err, data){
    data = JSON.parse(data);
    var foundJSONlist = [];
    for(var i in data['Servers']){
      services = data['Servers'][i]['services'];
      if(services.indexOf('GetDivideRequest') > -1){
        foundJSONlist.push(data['Servers'][i]);
      }
    }
    if(foundJSONlist) {res.send(foundJSONlist); console.log(foundJSONlist);}
    else {res.status(500).send("Not Found");}
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
  const heartbeat = spawn('python3',['heartbeat.py']);
  })
