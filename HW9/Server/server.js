var express = require('express');
var request = require('request');
var bodyParser = require('body-parser');
var path = require('path');

var app = express();
var ebayId = 'KeshavKa-searchPa-PRD-816e2f5cf-4f9dedea';

var gId = {
	id: '009008570453899811991:qpr9s7mjze0',
	key: 'AIzaSyAd_9ikQMP4EtOdqMDxhQS-r9vnZu0IaPI'
}

// app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

app.use(express.static(path.join(__dirname, 'public')));

app.get('/ebaySearchForm', (req, res) => {
	console.log("INIT: " + req.query.init);
	console.log("END: " + req.query.end);
	var searchURL = req.query.init + ebayId + "&" + req.query.end;
	// var searchURL = req.body.init + ebayId + req.body.end;
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

app.get('/singleItemJSON', (req, res) => {
	// var singleURL = req.body.sin1 + ebayId + req.body.sin2;
	console.log("S1: " + req.query.sin1);
	console.log("S2: " + req.query.sin2);
	var singleURL = req.query.sin1 + ebayId + "&" + req.query.sin2;
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

app.get('/similarItemJSON', (req, res) => {
	// var similarURL = req.body.mis1 + ebayId + req.body.mis2;
	// var similarURL = req.query.init + ebayId + "&" + req.query.end;

	// var similarURL = req.query.init + "&" + req.query.init['SERVICE-NAME'] 
	// 	+ "&" + req.query.init['SERVICE-VERSION'] + "&" + ebayId + req.query.end[RESPONSE-DATA-FORMAT'] 
	console.log("QUERY: ", req.query);
	var similarURL = "http://svcs.ebay.com/MerchandisingService?OPERATION-NAME=getSimilarItems&SERVICE-NAME=MerchandisingService&SERVICE-VERSION=1.1.0&CONSUMER-ID=";
	similarURL += ebayId + "&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&itemId=" + req.query.init + "&maxResults=20";
	console.log("URL: ", similarURL);

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
			// res.setHeader('content-type', 'application/json');
			// res.json(data);
			res.send(data);
		}
	})
})

app.get('/photoGen', (req, res) => {
	console.log(req.query)
	var googleURL = 'https://www.googleapis.com/customsearch/v1?q=' + req.query.q + '&cx=' + gId.id + '&imgSize=huge&imgType=news&num=8&searchType=image&key=' + gId.key;
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