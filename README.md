# DISS-project
Final project for university course "Design and Integration of Software Systems"

###Image Segmentation###

Image segmentation is the process of partitioning an image into multiple segments (sets of pixels, also known as superpixels). The goal of segmentation is to simplify and/or change the representation of an image into something that is more meaningful and easier to analyze.

I am using the K-means algorithm for this partitioning. It goes like this:</br>

1. Pick K cluster centers, either randomly or based on some heuristic</br>
2. Assign each pixel in the image to the cluster that minimizes the distance between the pixel and the cluster center</br>
3. Re-compute the cluster centers by averaging all of the pixels in the cluster</br>
4. Repeat steps 2 and 3 until convergence is attained (i.e. no pixels change clusters)</br>

### This project ###

I created a webpage, where you can upload an image, choose the number of clusters and segment the image into that number of clusters.

The initial page looks like this:

![initialPage](https://github.com/DChaushev/DISS-project/blob/master/screenshots/initialPage.png)

When you choose a picture, you see it at both sides:

![pictureChosen](https://github.com/DChaushev/DISS-project/blob/master/screenshots/pictureChosen.png)

Then you enter the number(5 is default) of clusters and click "Segment":

![pictureSegmented](https://github.com/DChaushev/DISS-project/blob/master/screenshots/pictureSegmented.png)

### Installation ###

1. Clone the repo.
2. Open **gradle.build** from **/servers** <br>On the first line, there is a **webAppsFolder** variable, pointing to the webapps folder of your server (e.g. "C:\apache-tomcat-9.0.0.M1\webapps"). Change this variable to your server. The application will be deployed there.
3. Open a terminal, navigate to **/server** and run **$gradle build**</br>Now the servers should be deployed.
4. Copy the **client** folder there as well.
5. Start your server, open a browser and enter **http://\<your-server-address-here\>/client**, where \<your-server-address-here\> can be localhost:8080 for example.

That's it!
