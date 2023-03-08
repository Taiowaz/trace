/*ajax*/
$.ajax({
    url: "/listAllInfo",
    type: "GET",
    dataType: "json",
    success: function (json) {
        $.each(json, function (i, info) {
            var img = info.imgList;
            var a;
            for (i = 0; i < img.length; i++) {
                if (img[i].serialNum == 0) {
                    a = img[i];
                    break;
                }
            }
            var list = "<div class=\"col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12 mb-5\">";
            list += "<figure class=\"effect-ming tm-video-item\">";
            list += "<img src=\"" + a.imgPath + "\" alt=\"Image\" class=\"img-fluid\">";
            list += "<figcaption class=\"d-flex align-items-center justify-content-center\">";
            list += "<h2>寻迹京城</h2>";
            list += "<a href=\"infoDetail.html?id="+info.infoId+"\">View more</a>";
            list += "</figcaption></figure>";
            list += "<div class=\"d-flex justify-content-between tm-text-gray\">";
            list += "<span style='text-align: center'>" + info.infoTitle + "</span>";
            list += "</div></div>";
            $("#info-list").append(list);
        })

    }
});