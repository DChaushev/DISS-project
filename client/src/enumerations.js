(function () {
    SERVER_URL = 'http://localhost:8080/Controller/image-segmentation';

    LOADING_IMAGE_ID = 'loadingImage';

    ORIGINAL_IMAGE_ID = 'originalImage';
    SEGMENTED_IMAGE_ID = 'segmentedImage';

    SEGMENT_BUTTON_ID = 'segmentButton';

    SAVE_TO_DRIVE = "saveToDrive";

    FILE_CHOOSER_ID = 'fileChooser';
    NUMBER_OF_CLUSTERS_INPUT_ID = 'numberOfClusters';

    EVENTS = {
        CHANGE: 'change',
        CLICK: 'click'
    };

    CONTENT_TYPE = {
        APPLICATION_JSON: 'application/json'
    };

    HTTP = {
        POST: "POST"
    }
})();