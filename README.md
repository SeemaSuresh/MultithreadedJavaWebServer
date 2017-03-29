# MultithreadedJavaWebServer

To run the project clone the entire project in your local machine.

Open the project on Eclipse IDE

Go to the file WebServer.java

Click on run

Go to the web browser and give the url as http://localhost:8000/<Web Address Present in the WebPages Folder>
ex: http://localhost:8000/FaceUpDeck.html

One hitting enter you can see the thread being started from the pool and a synchornized time message is displayed on the console along with 
HTTPRequest GET MEssage and since the file is valid, We also can see the HTTPResponse Post message with 1.1 keep-alive protocol

If the url is not found for eg: http://localhost:8000/FaceUpDeck1.html, you can only see the HTTP Get Request message and not the post request as it encounters a file not found exception.

This project is an implementation of a Java Multi-Threaded Servere with Thread Pools and follows Http/1.1 keep-alive protocol with synchronized writes to the console from the threads at a pool at a given time hence showing concurrent processing of multi-threaded/multi-pool environment
