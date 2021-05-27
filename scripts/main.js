(function (global) {
    var dc = {};
    var homeHtmlUrl = "pages/home_snippet.html";
    var cityDataLink = "http://127.0.0.1:8080/web-1/app/show/city";
    var destination = "pages/destination_snippet.html";
    var place = "pages/places_snippet.html";
    var placedesc = "pages/places_desc.html";
    var contact = "pages/contact_snippet.html";
    var numero = 0;

    function showAllDestinations(){
        return 0;
    }
    function addToDropDown(elt,dests){
        var txt = "";
        var txt2 = elt.innerHTML;
        /* if length dests > 4 we can show 3 and put other that takes us to a page full of dests else we show them all */
        let tt = Math.min(dests.length,3);
        for (var dest = 0; dest < tt; dest ++){
            let city = dests[dest].cityName;
            let id = dests[dest].cityId;
            txt += "<a href=\"#"+city+"\" onclick=\"$dc.loadDestination("+id+")\">" + city + "</a>";  
        }
        if(dests.length > 3){
            txt += "<a href=\"#\" onclick=\"showAllDestinations()\"> Other.. </a>";
        }
        elt.innerHTML = txt2 + txt;
    }
    dc.showDestinations = function (elt){
        var drpdwnCntnt = elt.children[1];
        if(drpdwnCntnt.children.length === 0){
            /* getting all the desitnations from the database */
            $ajaxUtils.sendGetRequest(cityDataLink,
                (dests)=>{addToDropDown(drpdwnCntnt,dests)},
                true);
        }
    }

    var insertHtml = function (selector, html) {
        var targetElem = document.querySelector(selector);
        targetElem.innerHTML = html;
    };

    var insertProperty = function (string, propName, propValue) {
        var propToReplace = "{{" + propName + "}}";
        string = string
            .replace(new RegExp(propToReplace, "g"), propValue);
        return string;

    };

    document.addEventListener("DOMContentLoaded", function (event) {
        $ajaxUtils.sendGetRequest(
            homeHtmlUrl,
            buildAndShowHomeHTML,
            false);
    });

    dc.loadDestination = function(num) {
        numero = num;
        $ajaxUtils.sendGetRequest(
            cityDataLink+"/"+numero,
            buildAndShowDestinationHTML,
            true);
    }

    function buildAndShowHomeHTML (data) {
        $ajaxUtils.sendGetRequest(
            homeHtmlUrl,
                function (homeHtmlUrl){
                insertHtml("#main_content", homeHtmlUrl);},
            false);
    }

    dc.loadDestinationContact = function (data){
        $ajaxUtils.sendGetRequest(
            contact,
            function (contact) {
                insertHtml("#main_content", contact);},
            false);
    }

    function buildAndShowDestinationHTML (data){
        $ajaxUtils.sendGetRequest(
            destination,
            function (destination){
                $ajaxUtils.sendGetRequest(
                    place,
                    function (place) {
                        $ajaxUtils.sendGetRequest(
                            placedesc,
                            function (placedesc) {
                                var placeView = buildPlaceView (
                                    data,
                                    destination,
                                    place,
                                    placedesc
                                )
                                insertHtml("#main_content", placeView);
                            },
                            false
                        );

                    },
                    false
                );
            },
            false
        );
    }

    function buildPlaceView(data,destination,place,placedesc) {
        var finalhtml = destination;
        finalhtml = insertProperty(finalhtml,"image_name",data.img);
        finalhtml = insertProperty(finalhtml,"ville_name", data.cityName);
        finalhtml = insertProperty(finalhtml,"ville_description", data.cityDesc);
        var liste = "";
        for (var i=0 ; i< data.places.length; i++){
            liste += "<li>"+ data.places[i] + "</li>";
        }
        finalhtml = insertProperty(finalhtml,"liste", liste);
        finalhtml += "<section class=\"content\">";
        for(var i=0 ; i < data.places.length ; i++){
            var html = place;
            html = insertProperty(html,"place_id", "id"+i);
            html = insertProperty(html,"place_name", data.places[i]);
            var subhtml ="";
            for (var j=0; j<data.placesDesc[i].length; j++){
                var sub_subhtml = placedesc;
                sub_subhtml = insertProperty(sub_subhtml,"place_sub_desc",data.placesDesc[i][j]);
                sub_subhtml = insertProperty(sub_subhtml,"image_sub",data.placesImg[i][j]);
                subhtml += sub_subhtml;
            }
            html = insertProperty(html,"desc",subhtml);
            finalhtml += html;
        }

        finalhtml += "</section>"
        return finalhtml;
    }

    global.$dc = dc;
})(window);