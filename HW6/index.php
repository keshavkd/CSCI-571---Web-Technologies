<!DOCTYPE html>
<html>
	<head>
		<meta charset = utf-8>
		<title>Homework 6</title>
		<style type = "text/css"> 
			
			html, body {
				height: 100%;
			}

			body {
				text-align: center;
				margin-top: 20px;
			}

			#prodheader {
				border-bottom: 2px solid lightgray;
				margin-right: 5px;
				margin-left: 5px;
			}

			#prodheader p {
				font-style: italic;
				font-size: 34px;
				margin: 0px;
				margin-bottom: 5px;
			}

			.box {
				width: 100%;
				margin: 0 auto;
				max-width: 600px;
				height: 280px;
				border: 3px solid lightgray;
				background-color: #FAFAFA;
			}

			.contents {
				text-align: left;
				margin-left: 15px;
			}

			.labels {
				margin-top: 10px;
			}

			label {
				font-weight: bold;
			}

			.buttons {
				margin-top: 20px;
				text-align: center;
			}

			#frame1 {
				width: 100%;
				margin: 20px auto;
				max-width: 750px;
				border: 2px solid gray;
				background-color: lightgray;
				padding: 0px;
			}

			#frame2 {
				width: 100%;
				/*max-width: 1200px;*/
				margin: 20px auto;
				padding: 0px;
			}

			#frame2 table {
				width: 1200px;
				border: 2px solid lightgray;
				border-collapse: collapse; 
				margin: 0px auto;
				padding: 0px;
			}

			#frame2 tr, #frame2 td, #frame2 th {
				border: 2px solid lightgray;
				border-collapse: collapse; 
				margin: 0px;
				padding: 0px;
			}

			#frame2 table td img {
				padding: 1px;
				display: block;
			}

			#frame3 {
				width: 100%;
				/*max-width: 700px;*/
				margin: 20px auto;
				padding: 0px;
			}

			#frame3 table, #frame3 tr, #frame3 td, #frame3 th {
				border: 2px solid lightgray;
				border-collapse: collapse; 
				margin: 0px;
				padding: 0px;
			}

			#frame3 table {
				margin: 0px auto;
			}

			#frame3 table tr td, #frame3 table tr th {
				padding: 3px;
			}

			a:hover {
				color: gray;
			}

			#frame4 table {
				border: 2px solid lightgray;
				border-collapse: collapse;
				margin: 0px auto;
			}

			#frame4 table tr, #frame4 table td {
				border: 0px;
				padding: 5px;
			}

			#tx {
				display: inline-block;
				width: 180px;
			}

			#tx:hover {
				color: gray;
			}

			#tdx {
				background-color: lightgray;
			}

			#tbx th, #tbx td {
				white-space: nowrap;
  				overflow: hidden;
			}

			#tbx {
				table-layout: inherit;
				width: 80%;
			}

		</style>

	</head>
	<body>

		<?php
			$phpKeyword = "";
			$phpCategory = "0";
			$phpCondition1 = "false";
			$phpCondition2 = "false";
			$phpCondition3 = "false";
			$phpType1 = "false";
			$phpType2 = "false";
			$phpEnableSearch = "false";
			$phpDistance = "";
			$phpHere = "false";
			$phpZipBtn = "false";
			$phpZipCode = "";
			
			$phpStatus = "default";
			$phpCorrectResponse = "default";
			$phpResponse = "default";

			if (isset($_POST['subm'])) {
				$phpKeyword = $_POST['keyw'];
				$phpCategory = $_POST['cat'];
				if (!empty($_POST['cond'])) {
					foreach ($_POST['cond'] as $cond) {
						if ($cond == "n")
							$phpCondition1 = "true";
						else if ($cond == "u")
							$phpCondition2 = "true";
						else 
							$phpCondition3 = "true";
					}
				}
				if (!empty($_POST['ship'])) {
					foreach ($_POST['ship'] as $ship) {
						if ($ship == "p")
							$phpType1 = "true";
						else
							$phpType2 = "true";
					}
				}
				if (isset($_POST['ens'])) {
					$phpEnableSearch = "true";
					if (!empty($_POST['miles']))
						$phpDistance = $_POST['miles'];
					if ($_POST['radiogp'] == "h")
						$phpHere = "true";
					else {
						$phpZipBtn = "true";
						$phpZipCode = $_POST['zipc'];
					}
				}

				if (!empty($_POST['CQ'])) {
					$phpCleanQuery = "";
					$phpCleanQuery = str_replace("&SECURITY-APPNAME=", "&SECURITY-APPNAME=<Insert AppId Here>", $_POST['CQ']);
					$phpResponse = file_get_contents($phpCleanQuery);
					$phpCorrectResponse = "true";
				}
				$phpStatus = "Done";
			}

			$phpForm2Response = true;
			$phpForm2Status = true;
			$phpImportContents = "default";
			$phpSimilarResponse = "default";

			if (isset($_POST['SFJSub'])) {
				$phpForm2Query = str_replace("&appid=", "&appid=KeshavKa-searchPa-PRD-816e2f5cf-4f9dedea", $_POST['SFJval']);
				$phpForm2Response = file_get_contents($phpForm2Query);
				$phpForm2Response = json_encode($phpForm2Response);
				$phpSimilarQuery = str_replace("&CONSUMER-ID=", "&CONSUMER-ID=KeshavKa-searchPa-PRD-816e2f5cf-4f9dedea", $_POST['SIval']);
				$phpSimilarResponse = file_get_contents($phpSimilarQuery);

				$phpKeyword = $_POST['keyworddup'];
				$phpCategory = $_POST['categorydup'];
				if (!empty($_POST['conddup'])) {
					foreach ($_POST['conddup'] as $cond) {
						if ($cond == "n")
							$phpCondition1 = "true";
						else if ($cond == "u")
							$phpCondition2 = "true";
						else 
							$phpCondition3 = "true";
					}
				}
				if (!empty($_POST['shipdup'])) {
					foreach ($_POST['shipdup'] as $ship) {
						if ($ship == "p")
							$phpType1 = "true";
						else
							$phpType2 = "true";
					}
				}
				if (isset($_POST['ensdup'])) {
					$phpEnableSearch = "true";
					if (!empty($_POST['milesdup']))
						$phpDistance = $_POST['milesdup'];
					try {
						if (isset($_POST['radiogpdupX'])) 
							$phpHere = "true";
						else {
							$phpZipBtn = "true";
							$phpZipCode = $_POST['zipcdup'];
						}
					} catch (Exception $e) {
						$phpZipBtn = "true";
						$phpZipCode = $_POST['zipcdup'];
					}
				}

				$phpForm2Status = "false";
			}
		?>

		<div class = "box">
			<form id = "prodsearch" method = "POST" onsubmit = "return sendRequest()">
				<div id = "prodheader">
					<p>Product Search</p>
				</div>
				<div class = "contents">
					<div class = "labels">
						<label for = "key">Keyword</label>
						<input type = "text" name = "keyw" id = "key" required>
					</div>
					<div class = "labels">
						<label for = "cat">Category</label>
						<select name = "cat" id = "categ" style = "width: 230px">
							<option value = "0">All Categories</option>
							<option value = "1" disabled>-------------------------------------------------</option>
							<option value = "550">Art</option>
							<option value = "2984">Baby</option>
							<option value = "267">Books</option>
							<option value = "11450">Clothing, Shoes & Accessories</option>
							<option value = "58058">Computers/Tablets & Networking</option>
							<option value = "26395">Health & Beauty</option>
							<option value = "11233">Music</option>
							<option value = "1249">Video Games & Consoles</option>
						</select>
					</div>
					<div class = "labels">
						<label for = "cond">Condition</label>
						<input type = "checkbox" name = "cond[]" id = "new" value = "n" style = "margin-left: 20px">New
						<input type = "checkbox" name = "cond[]" id = "used" value = "u" style = "margin-left: 20px">Used
						<input type = "checkbox" name = "cond[]" id = "und" value = "x" style = "margin-left: 20px">Unspecified
					</div>
					<div class = "labels">
						<label for = "ship">Shipping Options</label>
						<input type = "checkbox" name = "ship[]" id = "pick" value = "p" style = "margin-left: 40px">Local Pickup
						<input type = "checkbox" name = "ship[]" id = "fship" value = "f" style = "margin-left: 40px">Free Shipping
					</div>
					<div class = "labels">
						<input type = "checkbox" id = "gps" value = "g" name = "ens" onclick = "disableBtn()"><b> Enable Nearby Search</b>
						<input type = "text" name = "miles" id = "dist" style = "width: 50px; margin-left: 20px" placeholder = "10" disabled><b> miles from</b><div id = "radiobtn" style = "float: right; margin-right: 50px;">
							<input type = "radio" name = "radiogp" id = "loc" value = "h" onclick = "hereBtn()" disabled checked>Here<br>
							<input type = "radio" name = "radiogp" id = "zip" value = "z" onclick = "radioBtn()" disabled>
							<input type = "text" name = "zipc" id = "zipcode" placeholder = "zip code" required disabled>
						</div>
					</div>
					<div class = "buttons" style = "margin: 40px auto;">
						<input type = "submit" name = "subm" id = "search" value = "Search" disabled>
						<input type = "reset" name = "clr" id = "clear" onclick = "resetBtn()" value = "Clear">
					</div>
					<input type = "hidden" name = "CQ" id = "cleanQuery">
				</div>
			</form>
		</div>
		<div name = "searchRes" id = "frame1" hidden></div>
		<div name = "selectItem" id = "frame2" hidden></div>
		<div name = "singleItem" id = "frame3" hidden></div>
		<div name = "test" id = "frame4" style = "margin: 0px auto; overflow: auto; max-width: 700px; border: 2px solid lightgray;" hidden></div>
		</div>
		<form name = "singleForm" id = "SF" method = "POST">
			<input type = "text" name = "SFJval" id = "SFJV" hidden>
			<input type = "text" name = "SIval" id = "SIV" hidden>
			
			<input type = "text" name = "keyworddup" id = "keywdup" hidden>
			<input type = "text" name = "categorydup" id = "catdup" hidden>
			<div class = "labels">
				<label for = "conddup"></label>
				<input type = "checkbox" name = "conddup[]" id = "newdup" value = "n" hidden>
				<input type = "checkbox" name = "conddup[]" id = "useddup" value = "u" hidden>
				<input type = "checkbox" name = "conddup[]" id = "unddup" value = "x" hidden>
			</div>
			<div class = "labels">
				<label for = "shipdup"></label>
				<input type = "checkbox" name = "shipdup[]" id = "pickdup" value = "p" hidden>
				<input type = "checkbox" name = "shipdup[]" id = "fshipdup" value = "f" hidden>
			</div>
			<div class = "labels">
				<input type = "checkbox" id = "gpsdup" value = "g" name = "ensdup" hidden>
				<input type = "text" name = "milesdup" id = "distdup" hidden>
				<div id = "radiobtndup">
					<input type = "checkbox" name = "radiogpdupX" val = "h" id = "locdup" hidden>
					<input type = "checkbox" name = "radiogpdupY" val = "z" id = "zipdup" hidden>
					<input type = "text" name = "zipcdup" id = "zipcodedup" hidden>
				</div>
			</div>

			<input type = "submit" name = "SFJSub" id = "SFJS" hidden>
		</form>


		<script type = "text/javascript">

			function getLocation() {
				if (window.XMLHttpRequest)
					xmlhttp = new XMLHttpRequest();
				else
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				xmlhttp.open("POST", "http://ip-api.com/json", false);
				xmlhttp.send();
				window.jsonIp = JSON.parse(xmlhttp.responseText);
				if (window.jsonIp['status'] == "fail")
					return;
				window.locZip = jsonIp['zip'];
				document.getElementById("search").disabled = false;
				disableBtn();

				document.getElementById("keywdup").value = document.getElementById("key").value;
				document.getElementById("catdup").value = document.getElementById("categ").value;
				document.getElementById("newdup").checked = document.getElementById("new").checked;
				document.getElementById("useddup").checked = document.getElementById("used").checked;
				document.getElementById("unddup").checked = document.getElementById("und").checked;
				document.getElementById("pickdup").checked = document.getElementById("pick").checked;
				document.getElementById("fshipdup").checked = document.getElementById("fship").checked;
				document.getElementById("gpsdup").checked = document.getElementById("gps").checked;
				document.getElementById("distdup").value = document.getElementById("dist").value;
				if (document.getElementById("loc").checked) {
					document.getElementById("locdup").checked = document.getElementById("loc").checked;
					document.getElementById("zipdup").checked = false;
				} else {
					document.getElementById("locdup").checked = false;
					document.getElementById("zipdup").checked = document.getElementById("zip").checked;
					document.getElementById("zipcodedup").value = document.getElementById("zipcode").value;
				}
			}

			window.onload = getLocation;

			function resetBtn() {
				document.getElementById("dist").disabled = true;
				document.getElementById("loc").disabled = true;
				document.getElementById("zip").disabled = true;
				document.getElementById("loc").checked = true;
				document.getElementById("zip").checked = false;
				document.getElementById("zipcode").disabled = true;
				try {
					document.getElementById("noitem").hidden = true;
				} catch (Exception) {

				}
				document.getElementById("frame1").hidden = true;
				document.getElementById("frame2").hidden = true;
				document.getElementById("frame3").hidden = true;
				document.getElementById("frame4").hidden = true;
				document.getElementById("prodsearch").reset();
			}

			function disableBtn() {
				if (document.getElementById("gps").checked == true) {
					document.getElementById("dist").disabled = false;
					document.getElementById("loc").disabled = false;
					document.getElementById("zip").disabled = false;
					if (document.getElementById("zip").checked == true)
						document.getElementById("zipcode").disabled = false;
					else
						document.getElementById("zipcode").disabled = true;
				} else {
					document.getElementById("dist").disabled = true;
					document.getElementById("loc").disabled = true;
					document.getElementById("zip").disabled = true;
					document.getElementById("zipcode").disabled = true;
				}
			}

			function hereBtn() {
				document.getElementById("zipcode").disabled = true;
			}

			function radioBtn() {
				document.getElementById("zipcode").disabled = false;
			}

			function sendRequest() {
				var sQuery = document.getElementById("key").value;
				var cValue = document.getElementById("categ").value;
				sQuery = encodeURIComponent(sQuery);
				sQuery = sQuery.replace(/%20/g, "+");
				console.log(sQuery);
				var subQuery = "https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsAdvanced&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&paginationInput.entriesPerPage=20&keywords=" + sQuery + "&";
				if (cValue != 0)
					subQuery += "categoryId=" + cValue + "&";
				var i = 0;
				var j = 0;
				var rg1 = /^\d{5,5}$/;
				var rg2 = /^[1-9]\d*$/;
				if (document.getElementById("gps").checked) {
					if (document.getElementById("loc").checked)
						subQuery += "buyerPostalCode=" + window.locZip + "&";
					else {
						if (rg1.test(document.getElementById("zipcode").value)){
							subQuery += "buyerPostalCode=" + document.getElementById("zipcode").value + "&";
						}
					}
					var distance = document.getElementById("dist").value;
					if (rg2.test(distance))
						subQuery += "itemFilter(" + i + ").name=MaxDistance&itemFilter(" + i + ").value=" + distance + "&";
					else if (distance === '' || distance === null || distance === undefined)
						subQuery += "itemFilter(" + i + ").name=MaxDistance&itemFilter(" + i + ").value=10&";
					else {
						window.alert("Distance is invalid! Defaulting to 10...");
						document.getElementById("dist").value = 10;
						subQuery += "itemFilter(" + i + ").name=MaxDistance&itemFilter(" + i + ").value=10&";
					}
					i = i + 1;
				}
				if (document.getElementById("new").checked || document.getElementById("used").checked || document.getElementById("und").checked) {
					subQuery += "itemFilter(" + i + ").name=Condition&";
					if (document.getElementById("new").checked) {
						subQuery += "itemFilter(" + i + ").value(" + j + ")=New&";
						j = j + 1;
					}
					if (document.getElementById("used").checked) {
						subQuery += "itemFilter(" + i + ").value(" + j + ")=Used&";
						j = j + 1; 
					}
					if (document.getElementById("und").checked) {
						subQuery += "itemFilter(" + i + ").value(" + j + ")=Unspecified&";
						j = j + 1; 
					}
					i = i + 1;
				}
				if (document.getElementById("fship").checked) {
					subQuery += "itemFilter(" + i + ").name=FreeShippingOnly&itemFilter(" + i + ").value=true&";
					i = i + 1;
				}
				if (document.getElementById("pick").checked) {
					subQuery += "itemFilter(" + i + ").name=LocalPickupOnly&itemFilter(" + i + ").value=true&";
					i = i + 1;
				}
				subQuery += "itemFilter(" + i + ").name=HideDuplicateItems&itemFilter(" + i + ").value=true";
				// subQuery = subQuery.replace(/.$/, "");
				document.getElementById("cleanQuery").value = subQuery;
				console.log(subQuery);
				return true;
			}

			function putIntoForm() {
				document.getElementById("key").value = '<?php echo $phpKeyword; ?>';
				document.getElementById("categ").value = '<?php echo $phpCategory; ?>';
				var c1 = '<?php echo $phpCondition1; ?>';
				var c2 = '<?php echo $phpCondition2; ?>';
				var c3 = '<?php echo $phpCondition3; ?>';
				if (c1 == "true")
					document.getElementById("new").checked = true;
				if (c2 == "true")
					document.getElementById("used").checked = true;
				if (c3 == "true")
					document.getElementById("und").checked = true;
				var t1 = '<?php echo $phpType1; ?>'
				var t2 = '<?php echo $phpType2; ?>'	 
				if (t1 == "true")
					document.getElementById("pick").checked = true;
				if (t2 == "true")
					document.getElementById("fship").checked = true;
				var ENStest = '<?php echo $phpEnableSearch; ?>';
				if (ENStest == "true") {
					document.getElementById("gps").checked = true;
					var dMiles = '<?php echo $phpDistance; ?>';
					if (dMiles != "")
						document.getElementById("dist").value = dMiles;
					var zipBtn = '<?php echo $phpZipBtn; ?>';
					if (zipBtn == "true") {
						document.getElementById("zip").checked = true;
						var zipCodeVal = '<?php echo $phpZipCode; ?>';
						document.getElementById("zipcode").value = zipCodeVal;
					}
				}
			}

			var statusFlag = '<?php echo $phpStatus; ?>';
			if (statusFlag == "Done") {
				var test = '<?php echo $phpCorrectResponse; ?>';
				var jsonObj = "";
				var jsonACK = "";
				var resultObj = "";
				jsonObj = <?php echo json_encode($phpResponse); ?>;
				jsonObj = JSON.parse(jsonObj);
				jsonACK = jsonObj.findItemsAdvancedResponse[0].ack[0];
				if (jsonACK == "Failure") {
					if (jsonObj.findItemsAdvancedResponse[0].errorMessage[0].error[0].message[0] == "Invalid keyword." ||
						jsonObj.findItemsAdvancedResponse[0].errorMessage[0].error[0].message[0] == "Keyword is too long. The maximum length is 350. The maximum length for a single word is 98.") {
						document.getElementById("frame1").hidden = false;
						document.getElementById("frame1").innerHTML = "Keyword is invalid";
					} else {
						document.getElementById("frame1").hidden = false;
						document.getElementById("frame1").innerHTML = "Zipcode is invalid";
					}
				} else {
					resultObj = jsonObj.findItemsAdvancedResponse[0].searchResult[0];
				}
				console.log(jsonObj);
				putIntoForm(); 

				if (resultObj['@count'] == 0) {
					document.getElementById("frame1").hidden = false;
					document.getElementById("frame1").innerHTML = "No Records have been found";
				}

				var htmlData = "";
				if (test != "default") {
					if (jsonACK == "Success" && resultObj['@count'] > 0) {
						htmlData += "<table id = 'tbx'><tr><th>Index</th><th>Photo</th><th>Name</th><th>Price</th>";
						htmlData += "<th>Zip Code</th><th>Condition</th><th>Shipping Option</th></tr>";
						for (i = 0; i < resultObj['@count']; i++) {
							htmlData += "<tr><td align = 'left'>" + (i + 1) + "</td>";
							
							if (resultObj.item[i].galleryURL)
								htmlData += "<td align = 'left' width = '80px'><img src = '" + resultObj.item[i].galleryURL[0] + "' style = 'display: block;' width = '80px'></td>";
							else
								htmlData += "<td align = 'left'>N/A</td>";

							try { //if (resultObj.item[i].title && resultObj.item[i].itemId)
								htmlData += "<td align = 'left'><a onclick = 'singleItemJSON(" + resultObj.item[i].itemId[0] + ");' style = 'cursor: pointer; cursor: hand;'" + resultObj.item[i].title[0] + "'>" + resultObj.item[i].title[0] + "</a></td>";
							} catch (Exception) {
								htmlData += "<td align = 'left'>N/A</td>";
							}

							try { //(resultObj.item[i].sellingStatus[0].currentPrice[0].__value__)
								htmlData += "<td align = 'left'>$" + resultObj.item[i].sellingStatus[0].currentPrice[0].__value__ + "</td>";
							} catch (Exception) {
								htmlData += "<td align = 'left'>N/A</td>";		
							}
								
							try { //if (resultObj.item[i].postalCode)
								htmlData += "<td align = 'left'>" + resultObj.item[i].postalCode[0] + "</td>";
							} catch (Exception) {
								htmlData += "<td align = 'left'>N/A</td>";
							}

							try { //if (resultObj.item[i].condition)
								htmlData += "<td style = \"white-space: nowrap\; text-align: left\;\">" + resultObj.item[i].condition[0].conditionDisplayName[0] + "</td>";
							} catch (Exception) {
								htmlData += "<td style = \"white-space: nowrap\; text-align: left\;\" >N/A</td>";	
							}

							try { //if (resultObj.item[i].shippingInfo[0].shippingServiceCost) {
								if (resultObj.item[i].shippingInfo[0].shippingServiceCost[0].__value__ == "0.0")
									htmlData += "<td align = 'left'>Free Shipping</td>";
								else
									htmlData += "<td align = 'left'>$" + resultObj.item[i].shippingInfo[0].shippingServiceCost[0].__value__ + "</td>";
							} catch (Exception) {
								htmlData += "<td align = 'left'>N/A</td>";
							}
							htmlData += "</tr>"; 				
						}
						htmlData += "</table>";
						document.getElementById("frame2").hidden = false;
						document.getElementById("frame2").innerHTML = htmlData;
					}
				}
			}

			function singleItemJSON(itemID) {
				console.log(itemID);
				document.getElementById("frame2").hidden = true;
				var singleJSONObj = 'http://open.api.ebay.com/shopping?callname=GetSingleItem&responseencoding=JSON&appid=&siteid=0&version=967&ItemID=' + itemID + '&IncludeSelector=Description,Details,ItemSpecifics';
				var similarJSONObj = 'http://svcs.ebay.com/MerchandisingService?OPERATION-NAME=getSimilarItems&SERVICE-NAME=MerchandisingService&SERVICE-VERSION=1.1.0&CONSUMER-ID=&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&itemId=' + itemID + '&maxResults=8';
				document.getElementById("SFJV").value = singleJSONObj;
				document.getElementById("SIV").value = similarJSONObj;
				console.log(document.getElementById("SFJV").value);
				console.log(document.getElementById("SIV").value);

				console.log(document.getElementById("zipdup").checked);
				document.getElementById("SFJS").click();
			}

			if (<?php echo $phpForm2Status; ?> != 1) {
				putIntoForm();
				var jsonSIObj = <?php echo $phpForm2Response; ?>;
				jsonSIObj = JSON.parse(jsonSIObj);
				console.log(jsonSIObj);

				var sinData =  "";

				var SIObjItem = jsonSIObj.Item;

				if (jsonSIObj.Ack == "Failure") {
					document.write('<div id = "noitem" style = "margin: 15px auto; padding: 2px; width: 700px; border: 2px solid lightgray;"><b>Item Invalid!</b></div>');
					document.getElementById("frame2").hidden = true;
					document.getElementById("frame3").hidden = true;
					document.getElementById("frame4").hidden = true;
				} else {
					sinData += '<h1 style = "margin: 10px auto;">Item Details</h1>';
					sinData += '<table style = "word-wrap: break-word;">';
					if (SIObjItem.PictureURL.length != 0) {
						sinData += '<tr><th align = "left">Photo</th><td align = "left">'
						sinData += '<img src="' + SIObjItem.PictureURL[0] + '" height = "250px" style = "display: block;"></td></tr>';
					}
					if (SIObjItem.Title) {
						sinData += '<tr><th align = "left">Title</th><td align = "left">';
						sinData += SIObjItem.Title + '</td></tr>';
					}
					if (SIObjItem.SubTitle) {
						sinData += '<tr><th align = "left">SubTitle</th><td align = "left">';
						sinData += SIObjItem.SubTitle + '</td></tr>';
					}
					if (SIObjItem.CurrentPrice) {
						sinData += '<tr><th align = "left">Price</th><td align = "left">';
						sinData += SIObjItem.CurrentPrice.Value + ' ' + SIObjItem.CurrentPrice.CurrencyID + '</td></tr>';
					}
					if (SIObjItem.Location) {
						sinData += '<tr><th align = "left">Location</th><td align = "left">';
						sinData += SIObjItem.Location;
						if (SIObjItem.PostalCode)
							sinData += ', ' + SIObjItem.PostalCode + '</td>';
						else
							sinData += '</td>';
						sinData += '</tr>';
					}
					if (SIObjItem.Seller.UserID) {
						sinData += '<tr><th align = "left">Seller</th><td align = "left">';
						sinData += SIObjItem.Seller.UserID + '</td></tr>';
					}
					
					if (SIObjItem.ReturnPolicy) {
						sinData += '<tr><th align = "left">Return Policy (US)</th><td align = "left">';
						if (SIObjItem.ReturnPolicy.ReturnsAccepted) {
							sinData += SIObjItem.ReturnPolicy.ReturnsAccepted + " ";
						}
						if (SIObjItem.ReturnPolicy.ReturnsWithin) {
							sinData += SIObjItem.ReturnPolicy.ReturnsWithin;
						}
						sinData += '</td></tr>';
					}
					if (SIObjItem.ItemSpecifics) {
						var itemSpec = SIObjItem.ItemSpecifics.NameValueList;
						for(i = 0; i < itemSpec.length; i++) {
							sinData += '<tr><th align = "left">' + itemSpec[i].Name + '</th><td align = "left">' + itemSpec[i].Value[0] + '</td></tr>';
						}
					} else {
						sinData += '<tr><th align = "left">No Detail Info from Seller</th><td id = "tdx"></td></tr>';
					}

					sinData += '</table>';
					sinData += '<div id = "sellermsg"><p id = "clickMsg1" style = "margin-top: 20px; color: gray;">click to show seller message</p><img id = "arrow1" src = "http://csci571.com/hw/hw6/images/arrow_down.png" width = "50px" height = "25px" onclick = "stateChangeS()" style = "cursor: pointer;"></div>';

					var x = SIObjItem.Description;
					x = x.replace(/'/g, '&quot;');
					x = x.replace(/"/g, '&quot;');
					console.log(x);

					if (SIObjItem.Description == "") {
						sinData += '<div id = "noseller" style = "margin: 15px auto; padding: 2px; width: 700px; border: 2px solid lightgray;" hidden><b>No Seller Message Found</b></div>';
					} else {
						sinData += '<p  align = "center" id = "pgf" hidden><iframe id = "ogf" srcdoc = "' + x +  '"  style = "object-fit: cover; border: none; display: block;" width = "1100px" scrolling="no" onload="resizeFrame(this)" ></iframe></p>';
					}

					sinData += '<div id = "similarmsg"><p id = "clickMsg2" style = "margin-top: 20px; color: gray;">click to show similar items</p><img id = "arrow2" src = "http://csci571.com/hw/hw6/images/arrow_down.png" width = "50px" height = "25px" onclick = "stateChangeI()" style = "cursor: pointer;"></div>';

					document.getElementById("frame3").hidden = false;
					document.getElementById("frame3").innerHTML = sinData;

					var jsonSimilarObj = <?php echo json_encode($phpSimilarResponse); ?>;
					jsonSimilarObj = JSON.parse(jsonSimilarObj);
					console.log(jsonSimilarObj);
					var similarItemResponse = jsonSimilarObj.getSimilarItemsResponse;
					var simpleSI = similarItemResponse.itemRecommendations.item;
					var simData = "";
					simData += '<table style = "margin-top: 10px; margin-bottom: 10px; border: 0px;" id = "simTable"><tr>';
					if (simpleSI != null || simpleSI != undefined) {
						if (simpleSI.length > 0) {
							for (i = 0; i < simpleSI.length; i++)
								simData += '<td style = "width: 150px; padding-left: 15px; padding-right: 15px;"><img src = "' + simpleSI[i].imageURL + '" style = "width: 150px; height: auto;" /></td>';
							simData += '</tr><tr>';
							for (i = 0; i < simpleSI.length; i++)
								simData += '<td align = "center"><div id = "tx" onclick = "singleItemJSON(' + simpleSI[i].itemId + ');" style = "cursor: pointer">' + simpleSI[i].title + '</div></td>';
							simData += '</tr><tr>';
							for (i = 0; i < simpleSI.length; i++) {
								try {
									simData += '<td align = "center"><b>$' + simpleSI[i].currentPrice.__value__ + '</b></td>';
								} catch (Exception) {
									simData += '<td align = "center"><b>$' + simpleSI[i].buyItNowPrice.__value__ + '</b></td>';
								}
							}
						} else {
						simData = '<div id = "invItem" style = "margin: 4px; border: 2px solid lightgray" align = "center"><b>No Similar Item Found</b></div>';
						}
					} else {
						simData = '<div id = "invItem" style = "border: 2px solid lightgray" align = "center"><b>No Similar Item Found</b></div>';
					}
					simData += '</tr></table>';
					document.getElementById("frame4").innerHTML = simData;
				}
			}

			function resizeFrame(obj) {
				var htmlobj = obj.contentWindow.document.documentElement;
				var bodyobj = obj.contentWindow.document.body;
				obj.style.height = Math.max(htmlobj.scrollHeight, htmlobj.offsetHeight, htmlobj.clientHeight, bodyobj.scrollHeight, bodyobj.offsetHeight, bodyobj.clientHeight) + 5 + 'px';
			}		

			function stateChangeS() {
				var ihtml = document.getElementById("clickMsg1").innerHTML;
				if (ihtml.includes("show")) {
					document.getElementById("clickMsg1").innerHTML = "click to hide seller message";
					document.getElementById("arrow1").src = "http://csci571.com/hw/hw6/images/arrow_up.png";
					document.getElementById("clickMsg2").innerHTML = "click to show similar items";
					document.getElementById("arrow2").src = "http://csci571.com/hw/hw6/images/arrow_down.png";
					try {
						document.getElementById("pgf").hidden = false;
					} catch (Exception) {
						document.getElementById("noseller").hidden = false;
					}
					document.getElementById("frame4").hidden = true;
					resizeFrame(document.getElementById("ogf"));
				} else {
					document.getElementById("clickMsg1").innerHTML = "click to show seller message";
					document.getElementById("arrow1").src = "http://csci571.com/hw/hw6/images/arrow_down.png";
					try {
						document.getElementById("pgf").hidden = true;
					} catch (Exception) {
						document.getElementById("noseller").hidden = true;
					}
				}
			}

			function stateChangeI() {
				var ihtml = document.getElementById("clickMsg2").innerHTML;
				if (ihtml.includes("show")) {
					document.getElementById("clickMsg2").innerHTML = "click to hide similar items";
					document.getElementById("arrow2").src = "http://csci571.com/hw/hw6/images/arrow_up.png";
					document.getElementById("clickMsg1").innerHTML = "click to show seller message";
					document.getElementById("arrow1").src = "http://csci571.com/hw/hw6/images/arrow_down.png";
					document.getElementById("frame4").hidden = false;
					try {
						document.getElementById("pgf").hidden = true;
					} catch (Exception) {
						document.getElementById("noseller").hidden = true;
					}
				} else {
					document.getElementById("clickMsg2").innerHTML = "click to show similar items";
					document.getElementById("arrow2").src = "http://csci571.com/hw/hw6/images/arrow_down.png";
					document.getElementById("frame4").hidden = true;
				}
			}

		</script>

	</body>
</html>
