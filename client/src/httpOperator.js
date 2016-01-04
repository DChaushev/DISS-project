'use strict';

(function () {

    $("#sendPicture").click(function () {
        var blobFile = $('#filechooser')[0].files[0];

        var fd = new FormData();
        fd.append("fileToUpload", blobFile);

        $.ajax({
            url: "http://localhost:8080/Controller/image-segmentation",
            type: "POST",
            data: fd,
            processData: false,
            contentType: false,
            crossDomain: true,
            success: function(response) {
                console.log(response);
            },
            error: function(jqXHR, textStatus, errorMessage) {
                console.log(errorMessage); // Optional
            }
        });
    });

})();