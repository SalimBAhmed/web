(function (global) {
    var dc = {};
    var homeHtmlUrl = "./pages/home_snippet.html";
    var alldata = "server/data.json";
    var destination = "./pages/destination_snippet.html";
    var place = "pages/places_snippet.html";
    var placedesc = "pages/places_desc.html";
    var contact = "pages/contact_snippet.html";
    var numero = 0;


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
            alldata,
            buildAndShowHomeHTML,
            true);
    });

    dc.loaddesctination = function (num) {
        numero = num;
        $ajaxUtils.sendGetRequest(
            alldata,
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

    dc.loaddesctinationContact = function (data){
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
        finalhtml = insertProperty(finalhtml,"image_name",data[numero].image_name);
        finalhtml = insertProperty(finalhtml,"ville_name", data[numero].ville_name);
        finalhtml = insertProperty(finalhtml,"ville_description", data[numero].ville_description);
        var liste = "";
        for (var i=0 ; i< data[numero].places.length; i++){
            liste += "<li>"+ data[numero].places[i] + "</li>";
        }
        finalhtml = insertProperty(finalhtml,"liste", liste);
        finalhtml += "<section class=\"content\">";
        for(var i=0 ; i < data[numero].places.length ; i++){
            var html = place;
            html = insertProperty(html,"place_id", "id"+i);
            html = insertProperty(html,"place_name", data[numero].places[i]);
            var subhtml ="";
            for (var j=0; j<data[numero].place_sub_desc[i].length; j++){
                var sub_subhtml = placedesc;
                sub_subhtml = insertProperty(sub_subhtml,"place_sub_desc",data[numero].place_sub_desc[i][j]);
                sub_subhtml = insertProperty(sub_subhtml,"image_sub",data[numero].image_sub[i][j]);
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