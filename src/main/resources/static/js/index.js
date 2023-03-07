/*ajax*/
$.ajax({
    url: "/listAllInfo",
    type: "GET",
    dataType: "json",
    success: function (json) {
        var list = "<div class=\"col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12 mb-5\">";
        list += "<figure class=\"effect-ming tm-video-item\">";
        $.each(json, function (i, info) {
            var img = info.imgList;
            var a;
            for (i = 0; i < img.length; i++) {
                if (img[i].serialNum == 0) {
                    a = img[i];
                    break;
                }
            }
            list += "<img src=\"" + a.imgPath + "\" alt=\"Image\" style='width:445px;height:250px;overflow: hidden;' class=\"img-fluid\">";
            list += "<figcaption class=\"d-flex align-items-center justify-content-center\">";
            list += "<h2>寻迹京城</h2>";
            list += "<a href=\"video-detail.html\">View more</a>";
            list += "</figcaption></figure>";
            list += "<div class=\"d-flex justify-content-between tm-text-gray\">";
            list += "<span>" + info.infoTitle + "</span>";
            list += "</div></div>";
            // list += "<div id='" + info.infoId + "'class='info'><img style='width:400px;height:200px' src='.." + a.imgPath + "' alt='图片'></div>" + "<div style='padding:0 10px 10px 10px;background-color:#ABB9C5;width:400px;'>" + "<span>" + info.infoTitle + "</span></div></div>";
        })
        $("#info-list").append(list);
    }
});