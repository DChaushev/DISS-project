'use strict';

(function () {
    var fileChooser = document.getElementById(FILE_CHOOSER_ID);
    fileChooser.addEventListener(EVENTS.CHANGE, handleFileSelect, false);
})();

function sendRequest(file) {
    var reader = new FileReader();

    reader.onload = function () {
        var baseUrl = reader.result;
        baseUrl = baseUrl.replace(/^data:image\/(png|jpeg);base64,/, '');
        sendPOST(baseUrl);
    };

    reader.readAsDataURL(file);
}

function sendPOST(base64Image) {
    var numberOfClusters = document.getElementById(NUMBER_OF_CLUSTERS_INPUT_ID).value;

    showLoadingImage();

    $.ajax({
        url: SERVER_URL,
        contentType: CONTENT_TYPE.APPLICATION_JSON,
        data: JSON.stringify({
            clusters: numberOfClusters,
            encodedImage: base64Image
        }),
        type: HTTP.POST,
        success: function (data) {
            var encodedResult = data.encodedImage;
            hideLoadingImage();
            displayResult(encodedResult);
        },
        error: function (jqXHR, textStatus, errorMessage) {
            console.log(errorMessage);
            hideLoadingImage();
        }
    });
}

function showLoadingImage() {
    document.getElementById(LOADING_IMAGE_ID).style.display = "block";
}

function hideLoadingImage() {
    document.getElementById(LOADING_IMAGE_ID).style.display = "none";
}

function displayResult(encodedImage) {
    encodedImage = 'data:image/jpeg;base64,' + encodedImage;
    changeImageOf(SEGMENTED_IMAGE_ID, encodedImage);

    $("#buttons a").remove();

    var options = {
        success: function () {
            console.log("Success! Files saved to your Dropbox.");
        },

        error: function (errorMessage) {
            console.log(errorMessage);
        }
    };
    var button = Dropbox.createSaveButton(encodedImage, "myPhoto.jpg", options);
    document.getElementById("buttons").appendChild(button);

}

function handleFileSelect(event) {
    var files = event.target.files;

    if (files.length === 0) {
        return;
    }

    var file = files[0];

    if (file.type !== '' && !file.type.match('image.*')) {
        return;
    }

    window.URL = window.URL || window.webkitURL;

    var imageURL = window.URL.createObjectURL(file);

    changeImageOf(ORIGINAL_IMAGE_ID, imageURL);
    changeImageOf(SEGMENTED_IMAGE_ID, imageURL);

    var segmentButton = document.getElementById(SEGMENT_BUTTON_ID);
    segmentButton.addEventListener(EVENTS.CLICK, function () {
        sendRequest(file);
    }, false);
}

function changeImageOf(elementId, url) {
    document.getElementById(elementId).src = url;
}