var express = require('express');
var request = require('request');
var bodyParser = require('body-parser');
var path = require('path');

var app = express();
var ebayId = '<Sensitive Key Information>';

var gId = {
	id: '<Sensitive ID Information>',
	key: '<Sensitive Key Information>'
}

// app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

app.use(express.static(path.join(__dirname, 'public')));

app.get('/ebaySearchForm', (req, res) => {
	var searchURL = req.query.init + ebayId + req.query.end;
	console.log(searchURL);
	request.get({
		url: searchURL,
		json: true,
		headers: {'User-Agent': 'request'}
	}, (err, response, data) => {
		if (err) 
			console.log("Error in data!");
		else if (res.statusCode !== 200)
			console.log('Status: ', response.statusCode);
		else {
			res.setHeader('content-type', 'application/json');
			res.json(data);
		}
	})
});

app.post('/singleItemJSON', (req, res) => {
	var singleURL = req.body.sin1 + ebayId + req.body.sin2;
	console.log(singleURL);
	request.get({
		url: singleURL,
		json: true,
		headers: {'User-Agent': 'request'}
	}, (err, response, data) => {
		if (err) 
			console.log("Error in data!");
		else if (res.statusCode !== 200)
			console.log('Status: ', response.statusCode);
		else {
			res.setHeader('content-type', 'application/json');
			res.json(data);
		}
	})
})

app.post('/similarItemJSON', (req, res) => {
	var similarURL = req.body.mis1 + ebayId + req.body.mis2;
	console.log(similarURL);
	request.get({
		url: similarURL,
		// json: true,
		headers: {'User-Agent': 'request'}
	}, (err, response, data) => {
		if (err) 
			console.log("Error in data!");
		else if (res.statusCode !== 200)
			console.log('Status: ', response.statusCode);
		else {
			console.log(data);
			res.setHeader('content-type', 'application/json');
			res.json(data);
		}
	})
})

app.post('/photoGen', (req, res) => {
	var googleURL = 'https://www.googleapis.com/customsearch/v1?q=' + req.body.keyword + '&cx=' + gId.id + '&imgSize=huge&imgType=news&num=8&searchType=image&key=' + gId.key;
 	console.log(googleURL);
 	request.get({
		url: googleURL,
		json: true,
		headers: {'User-Agent': 'request'}
	}, (err, response, data) => {
		if (err) 
			console.log("Error in data!");
		else if (res.statusCode !== 200)
			console.log('Status: ', response.statusCode);
		else {
			res.setHeader('content-type', 'application/json');
			res.json(data);
		}
	})
})

app.listen(8081, function() {
	console.log('Server is listening at port: 8081...');
});
