$(function () {
    var str = window.location.search;
    var a = str.split("=");
    var id = a[1];
    $.ajax({
        url: "/getInfoById?id=" + id,
        type: "get",
        dataType: "json",
        success: function (info) {
            var title = "<h2 class=\"col-12 tm-text-primary\">";
            title += info.infoTitle;
            title += "</h2>";
            $("#info-title-container").append(title);

            var list = "";
            // list += " <div class=\"col-xl-8 col-lg-7 col-md-6 col-sm-12\"";
            // list += "style=\"text-indent:2em;font-size: large;line-height: 2em;display: flex;flex-direction: column; align-items: center;\">";
            var img = info.imgList;
            var para = info.infoParaList;
            for (i = 0; i < para.length; i++) {
                list += "<div>" + para[i].infoPara + "</div>";
                for (j = 0; j < img.length; j++) {
                    if (img[j].serialNum == para[i].infoParaId) {
                        list += "<div style=\"text-indent: 0%;\">";
                        list += " <img src=\"" + img[j].imgPath + "\"style=\"width: auto; max-width: 100%;\">";
                        list += " </div>";
                        break;
                    }
                }
            }
            $("#info-cont-container").append(list);
        }
    });
})